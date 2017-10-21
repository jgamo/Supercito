package com.example.t410.supercito.Datos;

/**
 * Created by T410 on 19/10/2017.
 */

public class Historial {
    private int id_compra;
    private String fecha;
    private int cantidad;
    private int total;

    public Historial(String fecha, int cantidad, int total) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.total = total;
    }

    public Historial(int id_compra, String fecha, int cantidad, int total) {
        this.id_compra = id_compra;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.total = total;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
