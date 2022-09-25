package com.example.istateca.Utils;

import com.example.istateca.Clases.Bibliotecario;
import com.example.istateca.Clases.Libro;
import com.example.istateca.Clases.Prestamo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PrestamoService {
    @GET("listarprestamo")
    Call<List<Prestamo>> getListarPrestamo();

    @POST("crearPrestamo")
    Call<Prestamo>addPrestamo(@Body Prestamo prestamo);

    @GET("listarprestamoxcedula/{cedula}")
    Call<List<Prestamo>>getBuscarCedula(@Path("cedula") String cedula);

    @POST("editarprestamo/{id}")
    Call<Prestamo>updatePersona(@Body Prestamo prestamo,@Path("id") int id);

   /* @GET("bibliotecario/{id}")
    Call<Bibliotecario> getBuscarDatos(@Path("id_bibliotecario") int id);*/
}
