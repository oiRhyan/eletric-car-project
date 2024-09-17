package com.devrhyan.eletriccarapp.repositorie

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import com.devrhyan.eletriccarapp.models.Car
import com.devrhyan.eletriccarapp.repositorie.data.local.CarsContract
import com.devrhyan.eletriccarapp.repositorie.data.local.SQLiteHelper

class CarRepository(val context : Context) {

    fun saveOnDB(car: Car): Boolean {
        val dbHelper = SQLiteHelper(context)
        val db = dbHelper.writableDatabase

        return try {
            // Verificar se o carro já existe no banco antes de inserir
            val carExistente = findCarOnDb(car.id)
            if (carExistente != null) {
                Log.i("DB", "Carro com ID ${car.id} já existe no banco.")
                return false
            }

            // Inserir os valores no banco de dados
            val values = ContentValues().apply {
                put(CarsContract.CarEntry.COLUMN_NAME_PRICE, car.preco)
                put(CarsContract.CarEntry.COLUMN_NAME_ID, car.id)
                put(CarsContract.CarEntry.COLUMN_NAME_BATTERY, car.bateria)
                put(CarsContract.CarEntry.COLUMN_NAME_POTENCY, car.potencia)
                put(CarsContract.CarEntry.COLUMN_NAME_RECHARGE, car.recarga)
                put(CarsContract.CarEntry.COLUMN_NAME_URLPHOTO, car.urlPhoto)
            }

            val newRegister = db.insert(CarsContract.CarEntry.TABLE_NAME, null, values)
            db.close() // Fechar o banco de dados após a operação
            newRegister != -1L // Retorna true se a inserção foi bem-sucedida
        } catch (e: Exception) {
            Log.e("Error DB", "Erro ao inserir dados no repositório DB: ${e.message}")
            db.close() // Fechar o banco de dados em caso de exceção
            false
        }
    }

    fun findCarOnDb(id : Int) : Car? {
        val onDbHelper = SQLiteHelper(context)
        val db = onDbHelper.writableDatabase

        val collumns = arrayOf(
            BaseColumns._ID,
            CarsContract.CarEntry.COLUMN_NAME_ID,
            CarsContract.CarEntry.COLUMN_NAME_PRICE,
            CarsContract.CarEntry.COLUMN_NAME_BATTERY,
            CarsContract.CarEntry.COLUMN_NAME_POTENCY,
            CarsContract.CarEntry.COLUMN_NAME_RECHARGE,
            CarsContract.CarEntry.COLUMN_NAME_URLPHOTO
        )

        val filter = "${CarsContract.CarEntry.COLUMN_NAME_ID} = ?"
        val filterValues = arrayOf(id.toString())

        val cursor = db.query(
            CarsContract.CarEntry.TABLE_NAME,
            collumns,
            filter,
            filterValues,
            null,
            null,
            null
        )

        var itemCar : Car? = null;

        with(cursor) {
            while (moveToNext()) {

                itemCar = Car(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_ID)),
                    preco = cursor.getString(cursor.getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_PRICE)),
                    bateria = cursor.getString(cursor.getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_BATTERY)),
                    potencia = cursor.getString(cursor.getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_POTENCY)),
                    recarga = cursor.getString(cursor.getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_RECHARGE)),
                    urlPhoto = cursor.getString(cursor.getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_URLPHOTO)),
                    true
                )

            }

        }

        cursor.close()

        return itemCar;
    }

    fun saveIfNotExists(car: Car) {
        val carOnSaved = findCarOnDb(car.id)
        if (carOnSaved == null) {
            saveOnDB(car)
        } else {
            Log.i("DB", "Carro já existe no banco de dados com ID: ${car.id}")
        }
    }

    fun getAllCars() : List<Car> {
        val onDbHelper = SQLiteHelper(context)
        val db = onDbHelper.writableDatabase

        val collumns = arrayOf(
            BaseColumns._ID,
            CarsContract.CarEntry.COLUMN_NAME_ID,
            CarsContract.CarEntry.COLUMN_NAME_PRICE,
            CarsContract.CarEntry.COLUMN_NAME_BATTERY,
            CarsContract.CarEntry.COLUMN_NAME_POTENCY,
            CarsContract.CarEntry.COLUMN_NAME_RECHARGE,
            CarsContract.CarEntry.COLUMN_NAME_URLPHOTO
        )

        val cursor = db.query(
            CarsContract.CarEntry.TABLE_NAME,
            collumns,
            null,
            null,
            null,
            null,
            null
        )

        val car = mutableListOf<Car>()

        with(cursor) {
            while (moveToNext()) {
                val itemCar = Car(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_ID)),
                    preco = cursor.getString(cursor.getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_PRICE)),
                    bateria = cursor.getString(cursor.getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_BATTERY)),
                    potencia = cursor.getString(cursor.getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_POTENCY)),
                    recarga = cursor.getString(cursor.getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_RECHARGE)),
                    urlPhoto = cursor.getString(cursor.getColumnIndexOrThrow(CarsContract.CarEntry.COLUMN_NAME_URLPHOTO)),
                    true
                )
                car.add(itemCar)
            }
        }
        cursor.close()
        return car
    }


    fun deleteCar(car: Car) {
        val dbHelper = SQLiteHelper(context)
        val db = dbHelper.writableDatabase

        try {
            val selection = "${CarsContract.CarEntry.COLUMN_NAME_ID} = ?"
            val selectionArgs = arrayOf(car.id.toString())

            db.delete(CarsContract.CarEntry.TABLE_NAME, selection, selectionArgs)
        } catch (e: Exception) {
            Log.e("DB", "Erro ao deletar carro com ID: ${car.id}", e)
        } finally {
            db.close()
        }
    }
}