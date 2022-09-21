package com.example.istateca.Utils;

import com.example.istateca.Clases.Persona;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsuarioService {
/*    @GET("fenix_alumno")
    Call <Persona> getUsuarios();*/

    @GET("fenix_alumno")
    Call<Persona> getUsuario(@Query("ced")String ced);
}
