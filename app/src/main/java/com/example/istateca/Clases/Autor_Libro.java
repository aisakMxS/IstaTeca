package com.example.istateca.Clases;

public class Autor_Libro {

    private int id_libro;
    private int id_autor;

    public Autor_Libro(int id_libro, int id_autor) {
        this.id_libro = id_libro;
        this.id_autor = id_autor;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }
}
