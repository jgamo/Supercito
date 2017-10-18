package com.example.t410.supercito.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by T410 on 13/10/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper{

    private static final String DB_NOMBRE ="productos.db";
    private static final int DB_VERSION = 1;

    public static final String CREATE_PRODUCTOS_TABLE ="CREATE TABLE "
            + ProductosContract.Entrada.NOMBRE_TABLA + "("
            + ProductosContract.Entrada.COLUMNA_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ProductosContract.Entrada.COLUMNA_NOMBRE  + " TEXT NOT NULL, "
            + ProductosContract.Entrada.COLUMNA_PRECIO  + " TEXT NOT NULL, "
            + ProductosContract.Entrada.COLUMNA_URL + " TEXT NOT NULL" +") ";

    private static final String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS " + ProductosContract.Entrada.NOMBRE_TABLA;

    public DataBaseHelper(Context context) {
        super(context, DB_NOMBRE, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PRODUCTOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
    }
}
