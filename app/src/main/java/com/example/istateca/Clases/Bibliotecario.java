package com.example.istateca.Clases;

import java.sql.Timestamp;

public class Bibliotecario {
    private int id;
    private Persona persona;
    private int rol;
    private String fecha_inicio;
    private String fecha_fin;
    private boolean activo_bibliotecario;

    public Bibliotecario(int id, Persona persona, int rol, String fecha_inicio, String fecha_fin, boolean activo_bibliotecario) {
        this.id = id;
        this.persona = persona;
        this.rol = rol;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.activo_bibliotecario = activo_bibliotecario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public boolean isActivo_bibliotecario() {
        return activo_bibliotecario;
    }

    public void setActivo_bibliotecario(boolean activo_bibliotecario) {
        this.activo_bibliotecario = activo_bibliotecario;
    }
}
