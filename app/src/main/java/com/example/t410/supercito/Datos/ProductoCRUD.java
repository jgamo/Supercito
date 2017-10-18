package com.example.t410.supercito.Datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by T410 on 13/10/2017.
 */

public class ProductoCRUD {

    private DataBaseHelper helper;

    // TODO: 10.- Creamos el constructor pidiendo de parámetro el contexto
    public ProductoCRUD(Context context) {
        helper = new DataBaseHelper(context);
    }

    public long newProduct(Producto item){
        // TODO: 11.- Solicitamos la base de datos en modo escritura
        // Obtiene la DB en modo de escritura
        SQLiteDatabase db = helper.getWritableDatabase();

        // TODO: 12.- Mapeamos columnas con valores
        // Crea un nuevo mapa de valores, de tipo clave-valor, donde clave es nombre de columna
        ContentValues values = new ContentValues();
        values.put(ProductosContract.Entrada.COLUMNA_NOMBRE, item.getNombre());
        values.put(ProductosContract.Entrada.COLUMNA_PRECIO, item.getPrecio());
        values.put(ProductosContract.Entrada.COLUMNA_URL, item.getUrl());
        // TODO: 13.- Insertamos fila
        // Inserta la nueva fila, regresando el valor de la primary key
        long newRowId = db.insert(ProductosContract.Entrada.NOMBRE_TABLA, null, values);

        // cierra conexión
        db.close();
        return newRowId;
    }

    public  ArrayList<Producto> getProductos(){
        // TODO: 14.- Crear una lista para almacenar elementos, llamamos Db y definimos columnas
        ArrayList<Producto> items = new ArrayList<Producto>();

        SQLiteDatabase db = helper.getReadableDatabase();
        // Especificamos las columnas a usar
        String[] columnas = {
                ProductosContract.Entrada.COLUMNA_ID,
                ProductosContract.Entrada.COLUMNA_NOMBRE,
                ProductosContract.Entrada.COLUMNA_PRECIO,
                ProductosContract.Entrada.COLUMNA_URL,
        };

        // TODO: 15.- Se crea un cursor para hacer recorrido de resultados y se crea una estructura de query
        Cursor c = db.query(
                ProductosContract.Entrada.NOMBRE_TABLA, // nombre tabla
                columnas, // columnas
                null, //texto para filtrar
                null, // arreglo de parametros a filtrar
                null, // agrupar
                null, // contiene
                null); //limite

        // TODO: 16.- Se recorren los resultados y se añaden a la lista
        while (c.moveToNext()){
            items.add(new Producto(
                    c.getString(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_NOMBRE)),
                    c.getString(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_PRECIO)),
                    c.getString(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_URL))
            ));
        }
        // TODO: 17.- Cerramos conexión y regresamos elementos
        c.close();
        return items;
    }

    public Producto getProducto(String id){
        Producto item = null;

        SQLiteDatabase db = helper.getReadableDatabase();
        // Especificamos las columnas a usar
        String[] columnas = {
                ProductosContract.Entrada.COLUMNA_ID,
                ProductosContract.Entrada.COLUMNA_NOMBRE,
                ProductosContract.Entrada.COLUMNA_PRECIO,
                ProductosContract.Entrada.COLUMNA_URL,
        };

        // TODO: 18.- usamos los parámetros para obtener una sentencia "WHERE"
        Cursor c = db.query(
                ProductosContract.Entrada.NOMBRE_TABLA, // nombre tabla
                columnas, // columnas
                " id = ?", //texto para filtrar
                new String[]{String.valueOf(id)}, // arreglo de parametros a filtrar
                null, // agrupar
                null, // contiene
                null); //limite

        while (c.moveToNext()){
            item = new Producto(
                    c.getString(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_ID)),
                    c.getString(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_NOMBRE)),
                    c.getString(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_PRECIO)),
                    c.getString(c.getColumnIndexOrThrow(ProductosContract.Entrada.COLUMNA_URL))
            );
        }

        c.close();
        return item;
    }

    public void updateProducto(Producto item){
        // Obtiene la DB en modo de escritura
        SQLiteDatabase db = helper.getWritableDatabase();

        // Crea un nuevo mapa de valores, de tipo clave-valor, donde clave es nombre de columna
        ContentValues values = new ContentValues();
        values.put(ProductosContract.Entrada.COLUMNA_NOMBRE, item.getNombre());
        values.put(ProductosContract.Entrada.COLUMNA_PRECIO, item.getPrecio());
        values.put(ProductosContract.Entrada.COLUMNA_URL, item.getUrl());
        // TODO: 19.- Actualizamos fila
        // Inserta la nueva fila, regresando el valor de la primary key
        db.update(
                ProductosContract.Entrada.NOMBRE_TABLA,
                values,
                "id = ?",
                new String[]{String.valueOf(item.getId())}
        );
        // cierra conexión
        db.close();
    }

    public void deleteProducto(Producto item){
        // Obtiene la DB en modo de escritura
        SQLiteDatabase db = helper.getWritableDatabase();

        // TODO: 20.- Eliminamos fila
        // Inserta la nueva fila, regresando el valor de la primary key
        db.delete(
                ProductosContract.Entrada.NOMBRE_TABLA,
                "id = ?",
                new String[]{item.getId()}
        );
        // cierra conexión
       db.close();
        //return item.getId();
    }
}
