package com.example.t410.supercito.Datos;

import android.provider.BaseColumns;

/**
 * Created by T410 on 13/10/2017.
 */

public final class ProductosContract {
    private ProductosContract(){

    }

    public static class Entrada implements BaseColumns{
        public static final String NOMBRE_TABLA = "productos";
        public static final String COLUMNA_ID = "id";
        public static final String COLUMNA_NOMBRE = "nombre";
        public static final String COLUMNA_PRECIO = "precio";
        public static final String COLUMNA_URL = "url";
    }
}
