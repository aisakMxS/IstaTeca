package com.example.istateca.ui.listado_prestamos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.istateca.Clases.Prestamo;
import com.example.istateca.R;

import java.util.List;


public class lista_prestamoAdapter extends BaseAdapter {
    List<Prestamo> prestamo;
    Context context;
    TextView txtnombre, txtestado,txttitulo, txtcedula;
    public lista_prestamoAdapter(@NonNull List<Prestamo> prestamo, Context context) {
        this.prestamo = prestamo;
        this.context = context;
    }

    @Override
    public int getCount() {
        return prestamo.size();
    }

    @Override
    public Object getItem(int i) {
        return prestamo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return prestamo.get(i).getId_prestamo();
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.lista_item_prestamos,viewGroup,false);
        }
        txtnombre=view.findViewById(R.id.txt_nombreEs);
        txttitulo=view.findViewById(R.id.txt_titulo_libro_solicitud);
        txtestado=view.findViewById(R.id.txt_estado_libro);
        txtcedula=view.findViewById(R.id.txt_cedulaEs);


        txtnombre.setText(prestamo.get(position).getUsuario().getPersona().getNombres());
        txtestado.setText(prestamo.get(position).getEstado_prestamo());
        txttitulo.setText(prestamo.get(position).getLibro().getTitulo());
        txtcedula.setText(prestamo.get(position).getUsuario().getPersona().getCedula());

        return view;
    }
}