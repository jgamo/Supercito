package com.example.t410.supercito.Datos;

/**
 * Created by T410 on 13/10/2017.
 */

public class Producto {
    private String id;
    private String nombre;
    private String precio;
    private String url;

    public Producto(String id, String nombre, String precio, String url) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Producto(String nombre, String precio, String url) {
        this.nombre = nombre;
        this.precio = precio;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", precio='" + precio + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
