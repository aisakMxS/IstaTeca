package com.example.istateca.Clases;

public class Autor {

    private int id_autor;
    private String nombre;

    public Autor(int id, String nombre) {
        this.id_autor = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id_autor;
    }

    public void setId(int id) {
        this.id_autor = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
