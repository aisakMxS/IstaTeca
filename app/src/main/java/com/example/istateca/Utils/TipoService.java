package com.example.istateca.Utils;

import com.example.istateca.Clases.Libro;
import com.example.istateca.Clases.Tipo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TipoService {
    @GET("listartipo")
    Call<ArrayList<Tipo>> getTipo();

    @POST("crearTipo")
    Call<Tipo>addTipo(@Body Tipo tipo);

}
