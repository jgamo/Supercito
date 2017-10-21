package com.example.t410.supercito.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by T410 on 13/10/2017.
 */

public class HistorialCRUD {

    private DataBaseHelper3 helper;

    // TODO: 10.- Creamos el constructor pidiendo de parámetro el contexto
    public HistorialCRUD(Context context) {
        helper = new DataBaseHelper3(context);
    }

    public long newHistorial(Historial item){
        // TODO: 11.- Solicitamos la base de datos en modo escritura
        // Obtiene la DB en modo de escritura
        SQLiteDatabase db = helper.getWritableDatabase();

        // TODO: 12.- Mapeamos columnas con valores
        // Crea un nuevo mapa de valores, de tipo clave-valor, donde clave es nombre de columna
        ContentValues values = new ContentValues();
        values.put(HistorialContract.Entrada.COLUMNA_FECHA, item.getFecha());
        values.put(HistorialContract.Entrada.COLUMNA_CANTIDAD, item.getCantidad());
        values.put(HistorialContract.Entrada.COLUMNA_TOTAL, item.getTotal());
        // TODO: 13.- Insertamos fila
        // Inserta la nueva fila, regresando el valor de la primary key
        long newRowId = db.insert(HistorialContract.Entrada.NOMBRE_TABLA, null, values);

        // cierra conexión
        db.close();
        return newRowId;
    }

    public  ArrayList<Historial> getHistorial(){
        // TODO: 14.- Crear una lista para almacenar elementos, llamamos Db y definimos columnas
        ArrayList<Historial> items = new ArrayList<Historial>();

        SQLiteDatabase db = helper.getReadableDatabase();
        // Especificamos las columnas a usar
        String[] columnas = {
                HistorialContract.Entrada.COLUMNA_ID_COMPRA,
                HistorialContract.Entrada.COLUMNA_FECHA,
                HistorialContract.Entrada.COLUMNA_CANTIDAD,
                HistorialContract.Entrada.COLUMNA_TOTAL,
        };

        // TODO: 15.- Se crea un cursor para hacer recorrido de resultados y se crea una estructura de query
        Cursor c = db.query(
                HistorialContract.Entrada.NOMBRE_TABLA, // nombre tabla
                columnas, // columnas
                null, //texto para filtrar
                null, // arreglo de parametros a filtrar
                null, // agrupar
                null, // contiene
                null); //limite

        // TODO: 16.- Se recorren los resultados y se añaden a la lista
        while (c.moveToNext()){
            items.add(new Historial(
                    c.getInt(c.getColumnIndexOrThrow(HistorialContract.Entrada.COLUMNA_ID_COMPRA)),
                    c.getString(c.getColumnIndexOrThrow(HistorialContract.Entrada.COLUMNA_FECHA)),
                    c.getInt(c.getColumnIndexOrThrow(HistorialContract.Entrada.COLUMNA_CANTIDAD)),
                    c.getInt(c.getColumnIndexOrThrow(HistorialContract.Entrada.COLUMNA_TOTAL))
            ));
        }
        // TODO: 17.- Cerramos conexión y regresamos elementos
        c.close();
        return items;
    }

    public Historial getCurrentHist(String id){
        Historial item = null;

        SQLiteDatabase db = helper.getReadableDatabase();
        // Especificamos las columnas a usar
        String[] columnas = {
                HistorialContract.Entrada.COLUMNA_ID_COMPRA,
                HistorialContract.Entrada.COLUMNA_FECHA,
                HistorialContract.Entrada.COLUMNA_CANTIDAD,
                HistorialContract.Entrada.COLUMNA_TOTAL,
        };

        // TODO: 18.- usamos los parámetros para obtener una sentencia "WHERE"
        Cursor c = db.query(
                HistorialContract.Entrada.NOMBRE_TABLA, // nombre tabla
                columnas, // columnas
                " id = ?", //texto para filtrar
                new String[]{String.valueOf(id)}, // arreglo de parametros a filtrar
                null, // agrupar
                null, // contiene
                null); //limite

        while (c.moveToNext()){
            item = new Historial(
                    c.getInt(c.getColumnIndexOrThrow(HistorialContract.Entrada.COLUMNA_ID_COMPRA)),
                    c.getString(c.getColumnIndexOrThrow(HistorialContract.Entrada.COLUMNA_FECHA)),
                    c.getInt(c.getColumnIndexOrThrow(HistorialContract.Entrada.COLUMNA_CANTIDAD)),
                    c.getInt(c.getColumnIndexOrThrow(HistorialContract.Entrada.COLUMNA_TOTAL))
            );
        }
        c.close();
        return item;
    }
}
