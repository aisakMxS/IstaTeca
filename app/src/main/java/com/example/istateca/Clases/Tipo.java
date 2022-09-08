package com.example.istateca.Clases;

public class Tipo {

    private int id;
    private String nombre;

    public Tipo(int tipo, String nombre) {
        this.id = tipo;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
