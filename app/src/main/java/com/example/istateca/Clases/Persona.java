package com.example.istateca.Clases;

public class Persona {
    private int id;
    private String cedula;
    private String usuario;
    private String clave;
    private String nombres;
    private int rol;
    private String correo;
    private String celular;
    private boolean activo;

    public Persona(int id, String cedula, String usuario, String clave, String nombres, int rol, String correo, String celular, boolean activo) {
        this.id = id;
        this.cedula = cedula;
        this.usuario = usuario;
        this.clave = clave;
        this.nombres = nombres;
        this.rol = rol;
        this.correo = correo;
        this.celular = celular;
        this.activo = activo;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
