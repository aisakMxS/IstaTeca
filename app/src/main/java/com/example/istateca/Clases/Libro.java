package com.example.istateca.Clases;

import java.sql.Timestamp;
import java.util.Date;

public class Libro {
    public int id_libro;
    private String codigo_dewey;
    private String titulo;
    private Tipo tipo;
    private String adquisicion;
    private int anio_publicacion;
    private String editor;
    private String ciudad;
    private int num_paginas;
    private String area;
    private String cod_ISBN;
    private String idioma;
    private String descripcion;
    private String indice_uno;
    private String indice_dos;
    private String indice_tres;
    private String dimensiones;
    private String estado_libro;
    private boolean activo;
    private byte [] imagen;
    private String url_digital;
    private int idbibliotecario;
    private String fecha_creacion;
    private boolean disponibilidad;
    private String nombre_donante;
    private byte[] documento_donacion;

    public Libro(int id_libro) {
        this.id_libro=id_libro;
    }

    public Libro(int id_libro, String codigo_dewey, String titulo, Tipo tipo, String adquisicion, int anio_publicacion, String editor, String ciudad, int num_paginas, String area, String cod_ISBN, String idioma, String descripcion, String indice_uno, String indice_dos, String indice_tres, String dimensiones, String estadoLibro, boolean activo, byte[] imagen, String url_digital, int idbibliotecario, String fecha_creacion, boolean disponibilidad, String nombre_donante, byte[] documento_donacion) {
        this.id_libro = id_libro;
        this.codigo_dewey = codigo_dewey;
        this.titulo = titulo;
        this.tipo = tipo;
        this.adquisicion = adquisicion;
        this.anio_publicacion = anio_publicacion;
        this.editor = editor;
        this.ciudad = ciudad;
        this.num_paginas = num_paginas;
        this.area = area;
        this.cod_ISBN = cod_ISBN;
        this.idioma = idioma;
        this.descripcion = descripcion;
        this.indice_uno = indice_uno;
        this.indice_dos = indice_dos;
        this.indice_tres = indice_tres;
        this.dimensiones = dimensiones;
        this.estado_libro = estadoLibro;
        this.activo = activo;
        this.imagen = imagen;
        this.url_digital = url_digital;
        this.idbibliotecario = idbibliotecario;
        this.fecha_creacion = fecha_creacion;
        this.disponibilidad = disponibilidad;
        this.nombre_donante = nombre_donante;
        this.documento_donacion = documento_donacion;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getCodigo_dewey() {
        return codigo_dewey;
    }

    public void setCodigo_dewey(String codigo_dewey) {
        this.codigo_dewey = codigo_dewey;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getAdquisicion() {
        return adquisicion;
    }

    public void setAdquisicion(String adquisicion) {
        this.adquisicion = adquisicion;
    }

    public int getAnio_publicacion() {
        return anio_publicacion;
    }

    public void setAnio_publicacion(int anio_publicacion) {
        this.anio_publicacion = anio_publicacion;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getNum_paginas() {
        return num_paginas;
    }

    public void setNum_paginas(int num_paginas) {
        this.num_paginas = num_paginas;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCod_ISBN() {
        return cod_ISBN;
    }

    public void setCod_ISBN(String cod_ISBN) {
        this.cod_ISBN = cod_ISBN;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIndice_uno() {
        return indice_uno;
    }

    public void setIndice_uno(String indice_uno) {
        this.indice_uno = indice_uno;
    }

    public String getIndice_dos() {
        return indice_dos;
    }

    public void setIndice_dos(String indice_dos) {
        this.indice_dos = indice_dos;
    }

    public String getIndice_tres() {
        return indice_tres;
    }

    public void setIndice_tres(String indice_tres) {
        this.indice_tres = indice_tres;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getEstadoLibro() {
        return estado_libro;
    }

    public void setEstadoLibro(String estadoLibro) {
        this.estado_libro = estadoLibro;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getUrl_digital() {
        return url_digital;
    }

    public void setUrl_digital(String url_digital) {
        this.url_digital = url_digital;
    }

    public int getIdbibliotecario() {
        return idbibliotecario;
    }

    public void setIdbibliotecario(int idbibliotecario) {
        this.idbibliotecario = idbibliotecario;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getNombre_donante() {
        return nombre_donante;
    }

    public void setNombre_donante(String nombre_donante) {
        this.nombre_donante = nombre_donante;
    }

    public byte[] getDocumento_donacion() {
        return documento_donacion;
    }

    public void setDocumento_donacion(byte[] documento_donacion) {
        this.documento_donacion = documento_donacion;
    }
}

