package com.example.istateca.Clases;

import java.sql.Timestamp;

public class Bibliotecario {
    private int id;
    private Persona persona;
    private int rol;
    private Timestamp fecha_inicio;
    private Timestamp fecha_fin;
    private boolean activo_bibliotecario;

    public Bibliotecario(int id, Persona persona, int rol, Timestamp fecha_inicio, Timestamp fecha_fin, boolean activo_bibliotecario) {
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

    public Timestamp getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Timestamp fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Timestamp getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Timestamp fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public boolean isActivo_bibliotecario() {
        return activo_bibliotecario;
    }

    public void setActivo_bibliotecario(boolean activo_bibliotecario) {
        this.activo_bibliotecario = activo_bibliotecario;
    }
}
