package com.example.istateca.ui.lista_libros;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.istateca.Clases.Libro;
import com.example.istateca.R;
import com.example.istateca.databinding.FragmentLLibrosBinding;
import com.example.istateca.ui.solicitud_libro.HomeFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class lista_librosAdapter extends BaseAdapter {
    List<Libro> libros;
    Context context;
    TextView txttitulo, txtnumpaginas,txtfechadecreacion;
    public lista_librosAdapter(@NonNull List<Libro> libros, Context context) {
        this.libros = libros;
        this.context = context;
    }

    @Override
    public int getCount() {
        return libros.size();
    }

    @Override
    public Object getItem(int i) {
        return libros.get(i);
    }

    @Override
    public long getItemId(int i) {
        return libros.get(i).getId_libro();
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.lista_item_libros,viewGroup,false);
        }
        txttitulo=view.findViewById(R.id.txt_titulo);
        txtfechadecreacion=view.findViewById(R.id.txt_fecha_creacion);
        txtnumpaginas=view.findViewById(R.id.txt_num_paginas);

        final String OLD_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
        final String NEW_FORMAT = "yyyy-MM-dd";
        String oldDateString = libros.get(position).getFecha_creacion();
        String newDateString;

        final DateFormat formatter = new SimpleDateFormat(OLD_FORMAT);
        Date d= null;
        try {
            d = formatter.parse(oldDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ((SimpleDateFormat) formatter).applyPattern(NEW_FORMAT);
        newDateString = formatter.format(d);

        txttitulo.setText(libros.get(position).getTitulo());
        txtfechadecreacion.setText(newDateString);
        txtnumpaginas.setText(String.valueOf(libros.get(position).getNum_paginas()));

        return view;
    }
}