package com.example.istateca.Clases;

public class Usuario {
    private int id;
    private int calificacion;
    private String observacion;
    private Persona persona;


    public Usuario(int id, int calificacion, String observacion, Persona persona) {
        this.id = id;
        this.calificacion = calificacion;
        this.observacion = observacion;
        this.persona = persona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
