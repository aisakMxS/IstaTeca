package com.example.istateca.Clases;

public class Tipo {

    private int idTipo;
    private String nombre;

    public Tipo(int tipo, String nombre) {
        this.idTipo = tipo;
        this.nombre = nombre;
    }

    public int getId() {
        return idTipo;
    }

    public void setId(int id) {
        this.idTipo = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
