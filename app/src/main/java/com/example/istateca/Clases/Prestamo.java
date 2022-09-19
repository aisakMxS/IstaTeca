package com.example.istateca.Clases;

import java.sql.Timestamp;
import java.util.Date;

public class Prestamo {
    private int id_prestamo;
    private Usuario id_usuario;
    private Libro id_libro;
    private String estado_libro;
    private String estado_prestamo;
    private String fecha_entrega;
    private Bibliotecario id_bibliotecario_entrega;
    private String documento_habilitante;
    private String fecha_recibo;
    private Bibliotecario id_bibliotecario_recibido;
    private String fecha_maxima;
    private boolean activo;
    private byte escaneo_matriz;

    public Prestamo() {
    }

    public Prestamo(int id_prestamo, Usuario id_usuario, Libro id_libro, String estado_libro, String estado_prestamo, String fecha_entrega, Bibliotecario id_bibliotecario_entrega, String documento_habilitante, String fecha_recibo, Bibliotecario id_bibliotecario_recibido, String fecha_maxima, boolean activo, byte escaneo_matriz) {
        this.id_prestamo = id_prestamo;
        this.id_usuario = id_usuario;
        this.id_libro = id_libro;
        this.estado_libro = estado_libro;
        this.estado_prestamo = estado_prestamo;
        this.fecha_entrega = fecha_entrega;
        this.id_bibliotecario_entrega = id_bibliotecario_entrega;
        this.documento_habilitante = documento_habilitante;
        this.fecha_recibo = fecha_recibo;
        this.id_bibliotecario_recibido = id_bibliotecario_recibido;
        this.fecha_maxima = fecha_maxima;
        this.activo = activo;
        this.escaneo_matriz = escaneo_matriz;
    }

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Libro getId_libro() {
        return id_libro;
    }

    public void setId_libro(Libro id_libro) {
        this.id_libro = id_libro;
    }

    public String getEstado_libro() {
        return estado_libro;
    }

    public void setEstado_libro(String estado_libro) {
        this.estado_libro = estado_libro;
    }

    public String getEstado_prestamo() {
        return estado_prestamo;
    }

    public void setEstado_prestamo(String estado_prestamo) {
        this.estado_prestamo = estado_prestamo;
    }

    public String getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(String fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public Bibliotecario getId_bibliotecario_entrega() {
        return id_bibliotecario_entrega;
    }

    public void setId_bibliotecario_entrega(Bibliotecario id_bibliotecario_entrega) {
        this.id_bibliotecario_entrega = id_bibliotecario_entrega;
    }

    public String getDocumento_habilitante() {
        return documento_habilitante;
    }

    public void setDocumento_habilitante(String documento_habilitante) {
        this.documento_habilitante = documento_habilitante;
    }

    public String getFecha_recibo() {
        return fecha_recibo;
    }

    public void setFecha_recibo(String fecha_recibo) {
        this.fecha_recibo = fecha_recibo;
    }

    public Bibliotecario getId_bibliotecario_recibido() {
        return id_bibliotecario_recibido;
    }

    public void setId_bibliotecario_recibido(Bibliotecario id_bibliotecario_recibido) {
        this.id_bibliotecario_recibido = id_bibliotecario_recibido;
    }

    public String getFecha_maxima() {
        return fecha_maxima;
    }

    public void setFecha_maxima(String fecha_maxima) {
        this.fecha_maxima = fecha_maxima;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public byte getEscaneo_matriz() {
        return escaneo_matriz;
    }

    public void setEscaneo_matriz(byte escaneo_matriz) {
        this.escaneo_matriz = escaneo_matriz;
    }
}
