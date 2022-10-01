package com.example.istateca.Utils;

import com.example.istateca.Clases.Autor;
import com.example.istateca.Clases.Bibliotecario;
import com.example.istateca.Clases.Libro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BibliotecarioService {
    @POST("crearbibliotecario")
    Call<Bibliotecario> addBibliotecario(@Body Bibliotecario bibliotecario);
    @GET("bibliotecario/{id}")
    Call<Bibliotecario> getBuscarDatos(@Path("id_bibliotecario") int id);
    @GET("listarbibliotecario")
    Call<List<Bibliotecario>> getBibliotecario();
    @GET("bibliotecario_x_cedula")
    Call<List<Bibliotecario>> getBuscarBliotecario(@Query("ced") String ced);
}
