package com.example.istateca.Clases;

public class AutorLibro {

    private int id_autorlibro;
    private Libro libro;
    private Autor autor;

    public AutorLibro(int id_autorlibro, Libro libro, Autor autor) {
        this.id_autorlibro = id_autorlibro;
        this.libro = libro;
        this.autor = autor;
    }

    public int getId_autorlibro() {
        return id_autorlibro;
    }

    public void setId_autorlibro(int id_autorlibro) {
        this.id_autorlibro = id_autorlibro;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
