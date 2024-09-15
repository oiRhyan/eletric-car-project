package com.devrhyan.eletriccarapp.repositorie.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context : Context) : SQLiteOpenHelper(
    context, CarsContract.DATABASE_NAME, null, 1
) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CarsContract.SQL_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(CarsContract.SQL_DELETE_ENTRYS)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

}