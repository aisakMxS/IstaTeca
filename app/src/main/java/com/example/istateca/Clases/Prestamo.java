package com.example.istateca.Clases;

import java.sql.Timestamp;
import java.util.Date;

public class Prestamo {
    private int id;
    private int id_usuario;
    private int id_libro;
    private String estado_libro;
    private String estado_prestamo;
    private Timestamp fecha_entrega;
    private int id_bibliotecario_entrega;
    private String documento_habilitante;
    private Timestamp fecha_recibo;
    private int id_bibliotecario_recibido;
    private Date fecha_maxima;
    private boolean activo;
    private byte escaneo_matriz;


}
