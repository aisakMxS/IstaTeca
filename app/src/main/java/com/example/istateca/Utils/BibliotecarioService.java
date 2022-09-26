package com.example.istateca.Utils;

import com.example.istateca.Clases.Bibliotecario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BibliotecarioService {
    @POST("crearbibliotecario")
    Call<Bibliotecario> addBibliotecario(@Body Bibliotecario bibliotecario);
}
