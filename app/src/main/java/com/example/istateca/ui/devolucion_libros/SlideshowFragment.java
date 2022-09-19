package com.example.istateca.ui.devolucion_libros;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.istateca.Clases.Libro;
import com.example.istateca.Clases.Persona;
import com.example.istateca.Clases.Prestamo;
import com.example.istateca.Utils.Apis;
import com.example.istateca.Utils.PrestamoService;
import com.example.istateca.databinding.FragmentDevolucionLBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlideshowFragment extends Fragment {

    private FragmentDevolucionLBinding binding;
    PrestamoService prestamoService;

    int a=0;
    Dialog dialogo;
    Bitmap bitmap;
    ActivityResultLauncher<Intent> activitResultLauncher;
    String url="http://192.168.68.110:8080/api/";
    List<Prestamo> lista_prestamo= new ArrayList<>();
    List<Persona> lista_persona= new ArrayList<>();
    List<Libro> lista_libro= new ArrayList<>();
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDevolucionLBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        binding.btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());
                binding.btnFecha.setText(d);
            }
        });
        binding.btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPrestamo();
                comboLibro(String.valueOf(binding.txtCedulaEstudiante.getText()));
                System.out.println("hola" + binding.txtCedulaEstudiante);

            }
        });


        return root;

    }


    private void getPrestamo(){

        prestamoService= Apis.getPrestamoService();
        Call<List<Prestamo>>call=prestamoService.getListarPrestamo();

        call.enqueue(new Callback<List<Prestamo>>() {
            @Override
            public void onResponse(Call<List<Prestamo>> call, Response<List<Prestamo>> response) {
                System.out.println("Entro al REpose");
                if(response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    System.out.println("Estoy aquiiiiiiii en el on response");
                    return;
                }
                lista_prestamo = response.body();
               // System.out.println(lista_prestamo.size());
            }

            @Override
            public void onFailure(Call<List<Prestamo>> call, Throwable t) {
                System.out.println("Error");
                Log.e("Error:",t.getMessage());
            }
        });

    }


    private void comboLibro( String cedula) {
        ArrayList<String> comboLibroList = new ArrayList<String>();
        comboLibroList.add("Seleccione: ");
        for (int i = 0; i < lista_prestamo.size(); i++) {
            if (lista_persona.get(i).getCedula().equals(cedula)) {
                if (lista_prestamo.get(i).getEstado_libro().equals("Prestado")) {
                    /*if (lista_prestamo.get(i).getId_libro() == lista_libro.get(i).getId_libro()) {
                        comboLibroList.add(lista_libro.get(i).getTitulo());
                    }*/
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}