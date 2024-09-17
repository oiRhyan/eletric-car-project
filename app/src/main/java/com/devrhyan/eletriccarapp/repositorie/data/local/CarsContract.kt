package com.devrhyan.eletriccarapp.repositorie.data.local

import android.provider.BaseColumns

object CarsContract {

    const val DATABASE_NAME = "DbCar.db"

    object CarEntry : BaseColumns {
        const val TABLE_NAME = "car";
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_PRICE = "price";
        const val COLUMN_NAME_BATTERY = "battery";
        const val COLUMN_NAME_KM = "km";
        const val COLUMN_NAME_POTENCY = "potency";
        const val COLUMN_NAME_RECHARGE = "recharge";
        const val COLUMN_NAME_URLPHOTO = "urlPhoto";
    }

    const val SQL_TABLE = "CREATE TABLE IF NOT EXISTS ${CarEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${CarEntry.COLUMN_NAME_ID} INTEGER," +
            "${CarEntry.COLUMN_NAME_PRICE} TEXT," +
            "${CarEntry.COLUMN_NAME_BATTERY} TEXT," +
            "${CarEntry.COLUMN_NAME_KM} TEXT," +
            "${CarEntry.COLUMN_NAME_POTENCY} TEXT," +
            "${CarEntry.COLUMN_NAME_RECHARGE} TEXT," +
            "${CarEntry.COLUMN_NAME_URLPHOTO} TEXT)"


    const val SQL_DELETE_ENTRYS = "DROP TABLE IF EXISTS ${CarEntry.TABLE_NAME}"
}