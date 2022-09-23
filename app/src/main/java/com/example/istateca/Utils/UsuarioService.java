package com.example.istateca.Utils;

import com.example.istateca.Clases.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UsuarioService {

/*    @GET("fenix_alumno")
    Call<Persona> getUsuario(@Query("ced")String ced);*/

    @GET("listarusuario")
    Call<List<Usuario>> getListarUsuarios();

    @POST("crearusuario")
    Call<Usuario>addUsuario(@Body Usuario usuario);

    @GET("usuario/{id}")
    Call<Usuario> getBuscarDatos(@Path("id_usuario") int id);
}
