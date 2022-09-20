package com.example.istateca.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsuarioApi {

    public static Retrofit getUsu(String url){
        Retrofit retrofit= new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        return  retrofit;
    }
}
