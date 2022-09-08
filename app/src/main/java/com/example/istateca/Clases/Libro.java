package com.example.istateca.Clases;

import java.sql.Timestamp;

public class Libro {
    private int id;
    private String codigo_DEWEY;
    private String titulo;
    private int id_tipo;
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
    private byte imagen;
    private String url_digital;
    private int id_bibliotecario;
    private Timestamp fecha_creacion;
    private boolean disponibilidad;

    public Libro(int id, String codigo_DEWEY, String titulo, int id_tipo, String adquisicion, int anio_publicacion, String editor, String ciudad, int num_paginas, String area, String cod_ISBN, String idioma, String descripcion, String indice_uno, String indice_dos, String indice_tres, String dimensiones, String estado_libro, boolean activo, byte imagen, String url_digital, int id_bibliotecario, Timestamp fecha_creacion, boolean disponibilidad) {
        this.id = id;
        this.codigo_DEWEY = codigo_DEWEY;
        this.titulo = titulo;
        this.id_tipo = id_tipo;
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
        this.estado_libro = estado_libro;
        this.activo = activo;
        this.imagen = imagen;
        this.url_digital = url_digital;
        this.id_bibliotecario = id_bibliotecario;
        this.fecha_creacion = fecha_creacion;
        this.disponibilidad = disponibilidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo_DEWEY() {
        return codigo_DEWEY;
    }

    public void setCodigo_DEWEY(String codigo_DEWEY) {
        this.codigo_DEWEY = codigo_DEWEY;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
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

    public String getEstado_libro() {
        return estado_libro;
    }

    public void setEstado_libro(String estado_libro) {
        this.estado_libro = estado_libro;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public byte getImagen() {
        return imagen;
    }

    public void setImagen(byte imagen) {
        this.imagen = imagen;
    }

    public String getUrl_digital() {
        return url_digital;
    }

    public void setUrl_digital(String url_digital) {
        this.url_digital = url_digital;
    }

    public int getId_bibliotecario() {
        return id_bibliotecario;
    }

    public void setId_bibliotecario(int id_bibliotecario) {
        this.id_bibliotecario = id_bibliotecario;
    }

    public Timestamp getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Timestamp fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}

