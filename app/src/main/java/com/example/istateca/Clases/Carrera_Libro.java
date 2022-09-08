package com.example.istateca.Clases;

public class Carrera_Libro {

    private int id_carrera;
    private int id_libro;

    public Carrera_Libro(int id_carrera, int id_libro) {
        this.id_carrera = id_carrera;
        this.id_libro = id_libro;
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(int id_carrera) {
        this.id_carrera = id_carrera;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }
}
