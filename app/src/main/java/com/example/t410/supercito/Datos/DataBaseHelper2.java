package com.example.t410.supercito.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by T410 on 13/10/2017.
 */

public class DataBaseHelper2 extends SQLiteOpenHelper{

    private static final String DB_NOMBRE ="compras.db";
    private static final int DB_VERSION = 1;

    public static final String CREATE_COMPRAS_TABLE ="CREATE TABLE "
            + ComprasContract.Entrada.NOMBRE_TABLA + "("
            + ComprasContract.Entrada.COLUMNA_ID_COMPRA  + " INTEGER NOT NULL, "
            + ComprasContract.Entrada.COLUMNA_ID_PRODUCTO  + " INTEGER NOT NULL, "
            + ComprasContract.Entrada.COLUMNA_NOMBRE  + " TEXT NOT NULL, "
            + ComprasContract.Entrada.COLUMNA_PRECIO  + " INTEGER NOT NULL, "
            + ComprasContract.Entrada.COLUMNA_CANTIDAD  + " INTEGER NOT NULL, "
            + ComprasContract.Entrada.COLUMNA_TOTAL + " INTEGER NOT NULL" +") ";

    private static final String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS " + ComprasContract.Entrada.NOMBRE_TABLA;

    public DataBaseHelper2(Context context) {
        super(context, DB_NOMBRE, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COMPRAS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
    }
}
