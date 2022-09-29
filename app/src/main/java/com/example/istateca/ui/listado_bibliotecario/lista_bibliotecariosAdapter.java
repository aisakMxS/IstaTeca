package com.example.istateca.ui.listado_bibliotecario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.istateca.Clases.Bibliotecario;
import com.example.istateca.R;

import java.util.List;

public class lista_bibliotecariosAdapter extends BaseAdapter {
    List<Bibliotecario> bibliotecarios;
    Context context;
    TextView txtced,txtusu,txtema;

    public lista_bibliotecariosAdapter(@NonNull List<Bibliotecario> bibliotecarios, Context context) {
        this.bibliotecarios = bibliotecarios;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bibliotecarios.size();
    }

    @Override
    public Object getItem(int i) {
        return bibliotecarios.get(i);
    }

    @Override
    public long getItemId(int i) {
        return bibliotecarios.get(i).getId();
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.lista_item_bibliotecario,viewGroup,false);
        }
        txtced=view.findViewById(R.id.txtcedula);
        txtusu=view.findViewById(R.id.txtusuario);
        txtema=view.findViewById(R.id.txtemail);

        txtced.setText(bibliotecarios.get(position).getPersona().getCedula());
        txtusu.setText(bibliotecarios.get(position).getPersona().getUsuario());
        txtema.setText(bibliotecarios.get(position).getPersona().getCorreo());

        return view;
    }
}
