package com.example.istateca.Utils;

import com.example.istateca.Clases.Bibliotecario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BibliotecarioService {
    @POST("crearbibliotecario")
    Call<Bibliotecario> addBibliotecario(@Body Bibliotecario bibliotecario);
    @GET("bibliotecario/{id}")
    Call<Bibliotecario> getBuscarDatos(@Path("id_bibliotecario") int id);
}
