package com.example.istateca.ui.registro_libros;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.istateca.Clases.Libro;
import com.example.istateca.R;
import com.example.istateca.Utils.Apis;
import com.example.istateca.Utils.LibroService;
import com.example.istateca.databinding.FragmentRegistroLibrosBinding;

import java.sql.Timestamp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class registro_librosFragment extends Fragment {
    LibroService libroService;
    private FragmentRegistroLibrosBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentRegistroLibrosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.imgGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputString = "Hello World!";
                byte[] byteArrray = inputString.getBytes();



                Libro l = new Libro(1,"Dewey","El chema",1,"adquisicionqwe",1980,"Editort","Cuenca", 90, "Area", "Isbn123"
                        , "Español", "Descripcion aasfa", "IUno", "IDos","Itres","Dimensiones", "Estado", false,byteArrray,"asfasdURL",1,null,true,"Christian",byteArrray);

                create(l);

            }
        });


        return root;
    }

    private void create(Libro l){
        Retrofit retrofit= new Retrofit.Builder().baseUrl("http://192.168.68.110:8080/api/").addConverterFactory(GsonConverterFactory.create()).build();
        libroService= retrofit.create(LibroService.class);
        Call<Libro> call= libroService.addlibro(l);
        call.enqueue(new Callback<Libro>() {
            @Override
            public void onResponse(Call<Libro> call, Response<Libro> response) {
                if(!response.isSuccessful()){
                    //Toast.makeText("Se agrego con exito", Toast.LENGTH_LONG).show();
                    Log.e("Response erra", response.message());
                    return;
                }
                Libro l=response.body();
               // Toast.makeText(registro_librosFragment.this,l.getCodigoDewey()+" created!", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Libro> call, Throwable t) {
                Log.e("Error:",t.getMessage());
                System.out.println("error");
            }
        });
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}