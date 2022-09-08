package com.example.istateca.Clases;

import java.util.Date;

public class Registro_Donacion {

    private int id;
    private int id_libro;
    private String nombre_donante;
    private Date fecha;
    private byte documento;

    public Registro_Donacion(int id, int id_libro, String nombre_donante, Date fecha, byte documento) {
        this.id = id;
        this.id_libro = id_libro;
        this.nombre_donante = nombre_donante;
        this.fecha = fecha;
        this.documento = documento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getNombre_donante() {
        return nombre_donante;
    }

    public void setNombre_donante(String nombre_donante) {
        this.nombre_donante = nombre_donante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public byte getDocumento() {
        return documento;
    }

    public void setDocumento(byte documento) {
        this.documento = documento;
    }
}
