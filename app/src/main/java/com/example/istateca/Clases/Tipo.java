package com.example.istateca.Clases;

public class Tipo {

    private int id_tipo;
    private String nombre;

    public Tipo(int tipo, String nombre) {
        this.id_tipo = tipo;
        this.nombre = nombre;
    }

    public int getId() {
        return id_tipo;
    }

    public void setId(int id) {
        this.id_tipo = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
