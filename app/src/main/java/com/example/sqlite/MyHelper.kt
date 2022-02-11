package com.example.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyHelper(context: Context) : SQLiteOpenHelper(context, "ACDB", null, 1){
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE CRYPTO(_id integer PRIMARY KEY AUTOINCREMENT, NAME TEXT, SHORT_TERM TEXT)")
        db?.execSQL("INSERT INTO CRYPTO(NAME, SHORT_TERM) VALUES('BITCOIN', 'BTC')")
        db?.execSQL("INSERT INTO CRYPTO(NAME, SHORT_TERM) VALUES('ETHEREUM', 'ETH')")
        db?.execSQL("INSERT INTO CRYPTO(NAME, SHORT_TERM) VALUES('BITCOIN CASH', 'BCH')")
        db?.execSQL("INSERT INTO CRYPTO(NAME, SHORT_TERM) VALUES('DOGECOIN', 'DOGE')")
        db?.execSQL("INSERT INTO CRYPTO(NAME, SHORT_TERM) VALUES('ETHEREUM CLASSIC', 'ETC')")
        db?.execSQL("INSERT INTO CRYPTO(NAME, SHORT_TERM) VALUES('LITECOIN', 'LTC')")
        db?.execSQL("INSERT INTO CRYPTO(NAME, SHORT_TERM) VALUES('RIPPLE', 'XRP')")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}