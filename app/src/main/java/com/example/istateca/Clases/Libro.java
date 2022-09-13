package com.example.istateca.Clases;

import java.sql.Timestamp;

public class Libro {
    private int id;
    private String codigoDewey;
    private String titulo;
    private int tipo;
    private String adquisicion;
    private int anioPublicacion;
    private String editor;
    private String ciudad;
    private int numPaginas;
    private String area;
    private String codIsbn;
    private String idioma;
    private String descripcion;
    private String indiceUno;
    private String indiceDos;
    private String indiceTres;
    private String dimensiones;
    private String estadoLibro;
    private boolean activo;
    private byte [] imagen;
    private String urlDigital;
    private int idbibliotecario;
    private Timestamp fechaCreacion;
    private boolean disponibilidad;
    private String nombreDonante;
    private byte[] documentoDonacion;

    public Libro() {

    }

    public Libro(int id, String codigoDewey, String titulo, int idTipo, String adquisicion, int anioPublicacion, String editor, String ciudad, int numPaginas, String area, String codIsbn, String idioma, String descripcion, String indiceUno, String indiceDos, String indiceTres, String dimensiones, String estadoLibro, boolean activo, byte[] imagen, String urlDigital, int idbibliotecario, Timestamp fechaCreacion, boolean disponibilidad, String nombreDonante, byte[] documentoDonacion) {
        this.id = id;
        this.codigoDewey = codigoDewey;
        this.titulo = titulo;
        this.tipo = idTipo;
        this.adquisicion = adquisicion;
        this.anioPublicacion = anioPublicacion;
        this.editor = editor;
        this.ciudad = ciudad;
        this.numPaginas = numPaginas;
        this.area = area;
        this.codIsbn = codIsbn;
        this.idioma = idioma;
        this.descripcion = descripcion;
        this.indiceUno = indiceUno;
        this.indiceDos = indiceDos;
        this.indiceTres = indiceTres;
        this.dimensiones = dimensiones;
        this.estadoLibro = estadoLibro;
        this.activo = activo;
        this.imagen = imagen;
        this.urlDigital = urlDigital;
        this.idbibliotecario = idbibliotecario;
        this.fechaCreacion = fechaCreacion;
        this.disponibilidad = disponibilidad;
        this.nombreDonante = nombreDonante;
        this.documentoDonacion = documentoDonacion;
    }

    public int getIdbibliotecario() {
        return idbibliotecario;
    }

    public void setIdbibliotecario(int idbibliotecario) {
        this.idbibliotecario = idbibliotecario;
    }

    public String getNombreDonante() {
        return nombreDonante;
    }

    public void setNombreDonante(String nombreDonante) {
        this.nombreDonante = nombreDonante;
    }

    public byte[] getDocumentoDonacion() {
        return documentoDonacion;
    }

    public void setDocumentoDonacion(byte[] documentoDonacion) {
        this.documentoDonacion = documentoDonacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoDewey() {
        return codigoDewey;
    }

    public void setCodigoDewey(String codigoDewey) {
        this.codigoDewey = codigoDewey;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIdTipo() {
        return tipo;
    }

    public void setIdTipo(int idTipo) {
        this.tipo = idTipo;
    }

    public String getAdquisicion() {
        return adquisicion;
    }

    public void setAdquisicion(String adquisicion) {
        this.adquisicion = adquisicion;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
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

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCodIsbn() {
        return codIsbn;
    }

    public void setCodIsbn(String codIsbn) {
        this.codIsbn = codIsbn;
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

    public String getIndiceUno() {
        return indiceUno;
    }

    public void setIndiceUno(String indiceUno) {
        this.indiceUno = indiceUno;
    }

    public String getIndiceDos() {
        return indiceDos;
    }

    public void setIndiceDos(String indiceDos) {
        this.indiceDos = indiceDos;
    }

    public String getIndiceTres() {
        return indiceTres;
    }

    public void setIndiceTres(String indiceTres) {
        this.indiceTres = indiceTres;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getEstadoLibro() {
        return estadoLibro;
    }

    public void setEstadoLibro(String estadoLibro) {
        this.estadoLibro = estadoLibro;
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

    public String getUrlDigital() {
        return urlDigital;
    }

    public void setUrlDigital(String urlDigital) {
        this.urlDigital = urlDigital;
    }

    public int getBibliotecario() {
        return idbibliotecario;
    }

    public void setBibliotecario(int bibliotecario) {
        this.idbibliotecario = bibliotecario;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}

