package com.example.istateca.Clases;

import java.sql.Timestamp;
import java.util.Date;

public class Prestamo {
    private int id;
    private int id_usuario;
    private int id_libro;
    private String estado_libro;
    private String estado_prestamo;
    private Timestamp fecha_entrega;
    private int id_bibliotecario_entrega;
    private String documento_habilitante;
    private Timestamp fecha_recibo;
    private int id_bibliotecario_recibido;
    private Date fecha_maxima;
    private boolean activo;
    private byte escaneo_matriz;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
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

    public Timestamp getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(Timestamp fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public int getId_bibliotecario_entrega() {
        return id_bibliotecario_entrega;
    }

    public void setId_bibliotecario_entrega(int id_bibliotecario_entrega) {
        this.id_bibliotecario_entrega = id_bibliotecario_entrega;
    }

    public String getDocumento_habilitante() {
        return documento_habilitante;
    }

    public void setDocumento_habilitante(String documento_habilitante) {
        this.documento_habilitante = documento_habilitante;
    }

    public Timestamp getFecha_recibo() {
        return fecha_recibo;
    }

    public void setFecha_recibo(Timestamp fecha_recibo) {
        this.fecha_recibo = fecha_recibo;
    }

    public int getId_bibliotecario_recibido() {
        return id_bibliotecario_recibido;
    }

    public void setId_bibliotecario_recibido(int id_bibliotecario_recibido) {
        this.id_bibliotecario_recibido = id_bibliotecario_recibido;
    }

    public Date getFecha_maxima() {
        return fecha_maxima;
    }

    public void setFecha_maxima(Date fecha_maxima) {
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
