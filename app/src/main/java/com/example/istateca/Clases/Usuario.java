package com.example.istateca.Clases;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {
    private int id_usuario;
    private int calificacion;
    private String observaciones;
    private Persona persona;

    public Usuario() {
    }

    public Usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Usuario(int id, int calificacion, String observaciones, Persona persona) {
        this.id_usuario = id;
        this.calificacion = calificacion;
        this.observaciones = observaciones;
        this.persona = persona;
    }


    protected Usuario(Parcel in) {
        id_usuario = in.readInt();
        calificacion = in.readInt();
        observaciones = in.readString();
        persona = in.readParcelable(Persona.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_usuario);
        dest.writeInt(calificacion);
        dest.writeString(observaciones);
        dest.writeParcelable(persona, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public int getId() {
        return id_usuario;
    }

    public void setId(int id) {
        this.id_usuario = id;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getObservacion() {
        return observaciones;
    }

    public void setObservacion(String observacion) {
        this.observaciones = observacion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }




}
