package com.example.istateca.Utils;

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

    @GET("listarprestamoxestado/{estado}")
    Call<List<Prestamo>>getBuscarestado(@Path("estado") String estado);

    @POST("editarprestamo/{id}")
    Call<Prestamo>updatePersona(@Path("id") int id);

}
