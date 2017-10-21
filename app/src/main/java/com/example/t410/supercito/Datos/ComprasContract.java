package com.example.t410.supercito.Datos;

import android.provider.BaseColumns;

/**
 * Created by T410 on 13/10/2017.
 */

public final class ComprasContract {
    private ComprasContract(){

    }

    public static class Entrada implements BaseColumns{
        public static final String NOMBRE_TABLA = "compras";
        public static final String COLUMNA_ID_COMPRA = "id_compra";
        public static final String COLUMNA_ID_PRODUCTO = "id_prodcuto";
        public static final String COLUMNA_NOMBRE = "nombre";
        public static final String COLUMNA_PRECIO = "precio";
        public static final String COLUMNA_CANTIDAD = "cantidad";
        public static final String COLUMNA_TOTAL = "total";
    }
}
