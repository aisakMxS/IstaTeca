package com.example.istateca.Utils;

import com.example.istateca.Clases.Bibliotecario;
import com.example.istateca.Clases.Persona;
import com.example.istateca.Clases.Usuario;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PersonaService {

/*    @GET("fenix_alumno")
    Call<Persona> getUsuario(@Query("ced")String ced);*/

    @GET("validarPersona")
    Call<Persona> validarPersona(@Query("usuario")String usuario,@Query("clave")String clave);

    @GET("tipocliente_usu")
    Call<Usuario> tipoUsuario(@Query("id_persona")int id_persona, @Query("rol")int rol);

    @GET("tipocliente_bib")
    Call<Bibliotecario> tipoBibliotecario(@Query("id_persona")int id_persona, @Query("rol")int rol);

}