package com.example.istateca.Utils;

public class Apis {
    public static final String URL_001="http://10.0.2.2:8080/api/";

    public static  LibroService getLibroService(){
        return ClienteApi.getClient(URL_001).create(LibroService.class);
    }
}
