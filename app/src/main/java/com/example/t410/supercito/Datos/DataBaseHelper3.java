package com.example.t410.supercito.Datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by T410 on 13/10/2017.
 */

public class DataBaseHelper3 extends SQLiteOpenHelper{

    private static final String DB_NOMBRE ="historial.db";
    private static final int DB_VERSION = 1;

    public static final String CREATE_HISTORIAL_TABLE ="CREATE TABLE "
            + HistorialContract.Entrada.NOMBRE_TABLA + "("
            + HistorialContract.Entrada.COLUMNA_ID_COMPRA  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + HistorialContract.Entrada.COLUMNA_FECHA  + " NUMERIC NOT NULL, "
            + HistorialContract.Entrada.COLUMNA_CANTIDAD  + " INTEGER NOT NULL, "
            + HistorialContract.Entrada.COLUMNA_TOTAL + " INTEGER NOT NULL" +") ";

    private static final String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS " + HistorialContract.Entrada.NOMBRE_TABLA;

    public DataBaseHelper3(Context context) {
        super(context, DB_NOMBRE, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_HISTORIAL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
    }
}
