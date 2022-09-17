package com.example.istateca.Utils;

import com.example.istateca.Clases.Libro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LibroService {

    @GET("listarlibros")
    Call<List<Libro>> getPersonas();

    @POST("crearlibro")
    Call<Libro>addlibro(@Body Libro libro);


}
