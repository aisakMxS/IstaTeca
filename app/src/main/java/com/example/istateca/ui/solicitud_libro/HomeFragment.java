package com.example.istateca.ui.solicitud_libro;

import static com.example.istateca.V_principal.bibliotecario_ingresado;
import static java.sql.Types.NULL;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.istateca.Clases.Libro;
import com.example.istateca.Clases.Prestamo;
import com.example.istateca.Clases.Tipo;
import com.example.istateca.R;
import com.example.istateca.Utils.Apis;
import com.example.istateca.Utils.LibroService;
import com.example.istateca.Utils.PrestamoService;
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
    PrestamoService prestamoService;
    List<Libro> lista_libro= new ArrayList<>();
    List<Prestamo> lista_prestamo = new ArrayList<>();
    int id_libro = 1;
    int a =0;
    int id_entrega=0;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSolicitudLBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.TxtBibliotecarioEntregaSolicitud.setText(bibliotecario_ingresado.getPersona().getNombres());
        id_entrega=bibliotecario_ingresado.getId();
        System.out.println(id_entrega + "ID BIBLIOTECARIO");
        binding.btnGuardarSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codigoDewey=binding.TxtCodigoDEWEYSolicitud.getText().toString();
                String fecha_entrega = binding.BtnFechaEntregaSolicitud.getText().toString();
                String fecha_maxima = binding.TxtFechaMaximaDevSolicitud.getText().toString();
                String cedula = binding.TxtCedulaEstudanteSolicitud.getText().toString();
                String bibliotecario = binding.TxtBibliotecarioEntregaSolicitud.getText().toString();
                String estadolibro=binding.TxtEstadoSolicitud.getText().toString();
                Boolean activo=true;
                String estado="Entregado";

                String documentos= (String) binding.CbDocumentoHabilitanteSolicitud.getSelectedItem();
                System.out.println(documentos);


                //Prestamo p = new Prestamo(a,cedula,li,estadolibro,estado,fecha_entrega,bi_entrega,documentos,null,null,fecha_maxima,activo,NULL);

                //create(p);
            }
        });

        binding.BtnFechaEntregaSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());
                binding.BtnFechaEntregaSolicitud.setText(d);
            }
        });



        listarLibros();

        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    Libro li= new Libro(id_libro);



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
                cargar_datos(id_libro);
                System.out.println(lista_libro.size() + " libros");
            }

            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
    private void listarprestamo(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Apis.URL_001)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        prestamoService=retrofit.create(PrestamoService.class);
        Call<List<Prestamo>> call= prestamoService.getListarPrestamo();
        call.enqueue(new Callback<List<Prestamo>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Prestamo>> call, Response<List<Prestamo>> response) {
                if (!response.isSuccessful()){
                    Log.e("Response err: ",response.message());
                    return;
                }
                lista_prestamo=response.body();
                System.out.println(lista_prestamo.size() + " libros");
            }

            @Override
            public void onFailure(Call<List<Prestamo>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void datos(){
        for (int i=0; i< lista_prestamo.size(); i++){
            if (lista_prestamo.get(i).getEstado_prestamo().equalsIgnoreCase("solicitado")){

            }
        }
    }


    public void cargar_datos(int id){
        for (int i=0; i< lista_libro.size(); i++){
                if((lista_libro.get(i).getId_libro()==id)){
                    binding.TxtCodigoDEWEYSolicitud.setText(lista_libro.get(i).getCodigo_dewey());
                    binding.TxtTituloSolicitud.setText(lista_libro.get(i).getTitulo());
                    binding.TxtEstadoSolicitud.setText(lista_libro.get(i).getEstadoLibro());
                }

        }
    }
    private void create(Prestamo l){
        Retrofit retrofit= new Retrofit.Builder().baseUrl(Apis.URL_001).addConverterFactory(GsonConverterFactory.create()).build();
        prestamoService= retrofit.create(PrestamoService.class);
        Call<Prestamo> call= prestamoService.addPrestamo(l);
        call.enqueue(new Callback<Prestamo>() {
            @Override
            public void onResponse(Call<Prestamo> call, Response<Prestamo> response) {
                if(!response.isSuccessful()){
                    //Toast.makeText("Se agrego con exito", Toast.LENGTH_LONG).show();
                    Log.e("Response erra", response.message());
                    return;
                }
                Prestamo l=response.body();
                // Toast.makeText(registro_librosFragment.this,l.getCodigoDewey()+" created!", Toast.LENGTH_LONG).show();
                System.out.println("CREADO CON EXITO");
            }

            @Override
            public void onFailure(Call<Prestamo> call, Throwable t) {
                Log.e("Error:",t.getMessage());
                System.out.println("error");
            }
        });
    }


}