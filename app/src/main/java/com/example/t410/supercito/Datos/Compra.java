package com.example.t410.supercito.Datos;

/**
 * Created by T410 on 18/10/2017.
 */

public class Compra {
    private long id_compra;
    private int id_producto;
    private String name;
    private int precio;
    private int cantidad;
    private int total;

    public Compra(int id, String name, int precio, int cantidad, int total) {
        this.id_producto = id;
        this.name = name;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
    }

    public Compra(int id_compra, int id_producto, String name, int precio, int cantidad, int total) {
        this.id_compra = id_compra;
        this.id_producto = id_producto;
        this.name = name;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
    }

    public int getIdProducto() {
        return id_producto;
    }

    public void setIdProducto(int id) {
        this.id_producto = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
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

    public long getIdCompra() {
        return id_compra;
    }

    public void setIdCompra(long id_compra) {
        this.id_compra = id_compra;
    }
}
