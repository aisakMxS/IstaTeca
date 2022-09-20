package com.example.istateca.Utils;

import com.example.istateca.Clases.AutorLibro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Autor_LibroService {
    @GET("listarautorlibro")
    Call<List<AutorLibro>> getAutor();

    @POST("crearautorlibro")
    Call<AutorLibro>addAutor(@Body AutorLibro autor);
}
