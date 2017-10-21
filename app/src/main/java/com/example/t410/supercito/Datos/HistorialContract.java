package com.example.t410.supercito.Datos;

import android.provider.BaseColumns;

/**
 * Created by T410 on 13/10/2017.
 */

public final class HistorialContract {
    private HistorialContract(){
    }

    public static class Entrada implements BaseColumns{
        public static final String NOMBRE_TABLA = "historial";
        public static final String COLUMNA_ID_COMPRA = "id_compra";
        public static final String COLUMNA_FECHA = "fecha";
        public static final String COLUMNA_CANTIDAD = "cantidad";
        public static final String COLUMNA_TOTAL = "total";
    }
}
