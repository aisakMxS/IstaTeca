package com.example.istateca.Clases;

import android.os.Parcel;
import android.os.Parcelable;

public class Persona implements Parcelable {
    private int id_persona;
    private String cedula;
    private String usuario;
    private String clave;
    private String nombres;
    private int rol;
    private String correo;
    private String celular;
    private boolean activo;

    public Persona(int id, String cedula, String usuario, String clave, String nombres, int rol, String correo, String celular, boolean activo) {
        this.id_persona = id;
        this.cedula = cedula;
        this.usuario = usuario;
        this.clave = clave;
        this.nombres = nombres;
        this.rol = rol;
        this.correo = correo;
        this.celular = celular;
        this.activo = activo;
    }

    protected Persona(Parcel in) {
        id_persona = in.readInt();
        cedula = in.readString();
        usuario = in.readString();
        clave = in.readString();
        nombres = in.readString();
        rol = in.readInt();
        correo = in.readString();
        celular = in.readString();
        activo = in.readByte() != 0;
    }

    public static final Creator<Persona> CREATOR = new Creator<Persona>() {
        @Override
        public Persona createFromParcel(Parcel in) {
            return new Persona(in);
        }

        @Override
        public Persona[] newArray(int size) {
            return new Persona[size];
        }
    };

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getId() {
        return id_persona;
    }

    public void setId(int id) {
        this.id_persona = id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id_persona);
        parcel.writeString(cedula);
        parcel.writeString(usuario);
        parcel.writeString(clave);
        parcel.writeString(nombres);
        parcel.writeInt(rol);
        parcel.writeString(correo);
        parcel.writeString(celular);
        parcel.writeByte((byte) (activo ? 1 : 0));
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id_persona=" + id_persona +
                ", cedula='" + cedula + '\'' +
                ", usuario='" + usuario + '\'' +
                ", clave='" + clave + '\'' +
                ", nombres='" + nombres + '\'' +
                ", rol=" + rol +
                ", correo='" + correo + '\'' +
                ", celular='" + celular + '\'' +
                ", activo=" + activo +
                '}';
    }
}
