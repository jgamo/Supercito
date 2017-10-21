package com.example.t410.supercito.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by T410 on 13/10/2017.
 */

public class ComprasCRUD {

    private DataBaseHelper2 helper;

    // TODO: 10.- Creamos el constructor pidiendo de parámetro el contexto
    public ComprasCRUD(Context context) {
        helper = new DataBaseHelper2(context);
    }

    public long newCompra(Compra item){
        // TODO: 11.- Solicitamos la base de datos en modo escritura
        // Obtiene la DB en modo de escritura
        SQLiteDatabase db = helper.getWritableDatabase();

        // TODO: 12.- Mapeamos columnas con valores
        // Crea un nuevo mapa de valores, de tipo clave-valor, donde clave es nombre de columna
        ContentValues values = new ContentValues();
        values.put(ComprasContract.Entrada.COLUMNA_ID_COMPRA, item.getIdCompra());
        values.put(ComprasContract.Entrada.COLUMNA_ID_PRODUCTO, item.getIdProducto());
        values.put(ComprasContract.Entrada.COLUMNA_NOMBRE, item.getName());
        values.put(ComprasContract.Entrada.COLUMNA_PRECIO, item.getPrecio());
        values.put(ComprasContract.Entrada.COLUMNA_CANTIDAD, item.getCantidad());
        values.put(ComprasContract.Entrada.COLUMNA_TOTAL, item.getTotal());
        // TODO: 13.- Insertamos fila
        // Inserta la nueva fila, regresando el valor de la primary key
        long newRowId = db.insert(ComprasContract.Entrada.NOMBRE_TABLA, null, values);

        // cierra conexión
        db.close();
        return newRowId;
    }

    public  ArrayList<Compra> getCompras(){
        // TODO: 14.- Crear una lista para almacenar elementos, llamamos Db y definimos columnas
        ArrayList<Compra> items = new ArrayList<Compra>();

        SQLiteDatabase db = helper.getReadableDatabase();
        // Especificamos las columnas a usar
        String[] columnas = {
                ComprasContract.Entrada.COLUMNA_ID_COMPRA,
                ComprasContract.Entrada.COLUMNA_ID_PRODUCTO,
                ComprasContract.Entrada.COLUMNA_NOMBRE,
                ComprasContract.Entrada.COLUMNA_PRECIO,
                ComprasContract.Entrada.COLUMNA_CANTIDAD,
                ComprasContract.Entrada.COLUMNA_TOTAL,
        };

        // TODO: 15.- Se crea un cursor para hacer recorrido de resultados y se crea una estructura de query
        Cursor c = db.query(
                ComprasContract.Entrada.NOMBRE_TABLA, // nombre tabla
                columnas, // columnas
                null, //texto para filtrar
                null, // arreglo de parametros a filtrar
                null, // agrupar
                null, // contiene
                null); //limite

        // TODO: 16.- Se recorren los resultados y se añaden a la lista
        while (c.moveToNext()){
            items.add(new Compra(
                    c.getInt(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_ID_COMPRA)),
                    c.getInt(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_ID_PRODUCTO)),
                    c.getString(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_NOMBRE)),
                    c.getInt(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_PRECIO)),
                    c.getInt(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_CANTIDAD)),
                    c.getInt(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_TOTAL))
            ));
        }
        // TODO: 17.- Cerramos conexión y regresamos elementos
        c.close();
        return items;
    }

    public Compra getCompra(String id){
        Compra item = null;

        SQLiteDatabase db = helper.getReadableDatabase();
        // Especificamos las columnas a usar
        String[] columnas = {
                ComprasContract.Entrada.COLUMNA_ID_COMPRA,
                ComprasContract.Entrada.COLUMNA_ID_PRODUCTO,
                ComprasContract.Entrada.COLUMNA_NOMBRE,
                ComprasContract.Entrada.COLUMNA_PRECIO,
                ComprasContract.Entrada.COLUMNA_CANTIDAD,
                ComprasContract.Entrada.COLUMNA_TOTAL,
        };

        // TODO: 18.- usamos los parámetros para obtener una sentencia "WHERE"
        Cursor c = db.query(
                ComprasContract.Entrada.NOMBRE_TABLA, // nombre tabla
                columnas, // columnas
                " id = ?", //texto para filtrar
                new String[]{String.valueOf(id)}, // arreglo de parametros a filtrar
                null, // agrupar
                null, // contiene
                null); //limite

        while (c.moveToNext()){
            item = new Compra(
                    c.getInt(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_ID_COMPRA)),
                    c.getInt(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_ID_PRODUCTO)),
                    c.getString(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_NOMBRE)),
                    c.getInt(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_PRECIO)),
                    c.getInt(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_CANTIDAD)),
                    c.getInt(c.getColumnIndexOrThrow(ComprasContract.Entrada.COLUMNA_TOTAL))
            );
        }
        c.close();
        return item;
    }
}
