package com.example.istateca.ui.solicitud_libro;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.istateca.Clases.Libro;
import com.example.istateca.R;
import com.example.istateca.Utils.Apis;
import com.example.istateca.Utils.LibroService;
import com.example.istateca.databinding.FragmentSolicitudLBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private FragmentSolicitudLBinding binding;

    LibroService libroService;
    List<Libro> lista_libro= new ArrayList<>();
    int id_libro = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSolicitudLBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnGuardarSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        binding.BtnFechaEntregaSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());
                binding.BtnFechaEntregaSolicitud.setText(d);
            }
        });

        binding.btnListaSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("entrp al boton listar");

            }
        });


        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void listarLibros(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Apis.URL_001)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        libroService=retrofit.create(LibroService.class);
        Call<List<Libro>> call= libroService.getListarLibros();
        call.enqueue(new Callback<List<Libro>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {
                if (!response.isSuccessful()){
                    Log.e("Response err: ",response.message());
                    return;
                }
                lista_libro=response.body();
                System.out.println(lista_libro.size() + " libros");
            }

            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}