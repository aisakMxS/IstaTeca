package com.example.istateca.ui.devolucion_libros;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.istateca.Clases.Libro;
import com.example.istateca.Clases.Persona;
import com.example.istateca.Clases.Prestamo;
import com.example.istateca.databinding.FragmentDevolucionLBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class SlideshowFragment extends Fragment {

    private FragmentDevolucionLBinding binding;


    int a=0;
    Dialog dialogo;
    Bitmap bitmap;
    ActivityResultLauncher<Intent> activitResultLauncher;
    List<Prestamo> lista_Prestamo= new ArrayList<>();
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

        binding.txtCedulaEstudiante.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return false;
            }
        });
        binding.btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());
                binding.btnFecha.setText(d);
            }
        });


        return root;

    }
/*
    private void getPrestamo(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(url).addConverterFactory(MoshiConverterFactory.create()).build();
        prestamoService= retrofit.create(DevolucionService.class);



        Call<List<Prestamo>> call= prestamoService.getPersonas();

        call.enqueue(new Callback<List<Prestamo>>() {
            @Override
            public void onResponse(Call<List<Prestamo>> call, Response<List<Prestamo>> response) {
                if(response.isSuccessful()){
                    Log.e("Response err: ", response.message());
                    System.out.println("Estoy aquiiiiiiii en el on response");
                    return;
                }
                lista_Prestamo = response.body();
                System.out.println(lista_Prestamo.size());
            }

            @Override
            public void onFailure(Call<List<Prestamo>> call, Throwable t) {
                Log.e("Response err: ", t.getMessage());
            }
        });


    }*/


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}