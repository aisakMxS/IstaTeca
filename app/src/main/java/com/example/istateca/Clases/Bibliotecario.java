package com.example.istateca.Clases;

import android.os.Parcel;
import android.os.Parcelable;


public class Bibliotecario implements Parcelable {
    private int id_bibliotecario;
    private Persona persona;
    private String fecha_inicio;
    private String fecha_fin;
    private boolean activo_bibliotecario;

    public Bibliotecario(int id, Persona persona, String fecha_inicio, String fecha_fin, boolean activo_bibliotecario) {
        this.id_bibliotecario = id;
        this.persona = persona;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.activo_bibliotecario = activo_bibliotecario;
    }

    protected Bibliotecario(Parcel in) {
        id_bibliotecario = in.readInt();
        persona = in.readParcelable(Persona.class.getClassLoader());
        fecha_inicio = in.readString();
        fecha_fin = in.readString();
        activo_bibliotecario = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_bibliotecario);
        dest.writeParcelable(persona, flags);
        dest.writeString(fecha_inicio);
        dest.writeString(fecha_fin);
        dest.writeByte((byte) (activo_bibliotecario ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Bibliotecario> CREATOR = new Creator<Bibliotecario>() {
        @Override
        public Bibliotecario createFromParcel(Parcel in) {
            return new Bibliotecario(in);
        }

        @Override
        public Bibliotecario[] newArray(int size) {
            return new Bibliotecario[size];
        }
    };

    public int getId() {
        return id_bibliotecario;
    }

    public void setId(int id_bibliotecario) {
        this.id_bibliotecario = id_bibliotecario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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
