package com.example.istateca.Utils;

import com.example.istateca.Clases.Autor;
import com.example.istateca.Clases.Tipo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AutorService {
    @GET("listarautor")
    Call<List<Autor>> getAutor();

    @POST("crearautor")
    Call<Autor>addAutor(@Body Autor autor);
}
