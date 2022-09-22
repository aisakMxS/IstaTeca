package com.example.istateca.ui.devolucion_libros;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.istateca.Clases.Bibliotecario;
import com.example.istateca.Clases.Libro;
import com.example.istateca.Clases.Persona;
import com.example.istateca.Clases.Prestamo;
import com.example.istateca.Clases.Usuario;
import com.example.istateca.Utils.Apis;
import com.example.istateca.Utils.LibroService;
import com.example.istateca.Utils.PrestamoService;
import com.example.istateca.Utils.UsuarioService;
import com.example.istateca.databinding.FragmentDevolucionLBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SlideshowFragment extends Fragment {

    private FragmentDevolucionLBinding binding;
    PrestamoService prestamoService;
    LibroService libroService;
    UsuarioService usuarioService;

    List<Prestamo> lista_prestamo= new ArrayList<>();
    List<Persona> lista_persona= new ArrayList<>();
    List<Usuario> lista_usuario = new ArrayList<>();
    List<Bibliotecario> lista_bibliBibliotecarios = new ArrayList<>();
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
                System.out.println("entrp al boton listar");

            }
        });
        binding.btnCedula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cedula=binding.txtCedulaEstudiante.getText().toString();
                //listar(cedula);
                System.out.println(cedula);

                comboLibro();
            }
        });

        getPrestamo();
        listarLibros();
        return root;

    }

    private void listar(String cedula){
        for (int i = 0; i < lista_prestamo.size(); i++) {

            if (lista_persona.get(i).getCedula().equals(cedula)){
                if (lista_prestamo.get(i).getEstado_libro().equals("entregado")) {
                    /*if (lista_prestamo.get(i).getId_libro() == lista_libro.get(i).getId_libro()) {
                        comboLibroList.add(lista_libro.get(i).getTitulo());
                    }*/
                    System.out.println("entre al metodo");
                }
            }
        }
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
                    lista_prestamo = response.body();

                    System.out.println(lista_prestamo.size());
                    //comboLibro();
                    return;
                }

            }

            @Override
            public void onFailure(Call<List<Prestamo>> call, Throwable t) {
                System.out.println("Error");
                Log.e("Error:",t.getMessage());
            }
        });

    }
    private void listarLibros(){
        System.out.println("entro ");
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
                System.out.println("te amo");
            }

            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }






    private void comboLibro() {

        ArrayList<String> comboTiposList = new ArrayList<String>();

        comboTiposList.add("Seleccione: ");


        for (int i=0; i< lista_prestamo.size(); i++){
            int x= lista_prestamo.get(i).getLibro().getId_libro();
            System.out.println("Lista prestamo" + x );
            for (int y=0; y< lista_libro.size(); y++){
                int z= lista_libro.get(y).getId_libro();
                System.out.println("libro " + z);
               if(lista_prestamo.get(i).getLibro().getId_libro()==lista_libro.get(y).getId_libro()){

                    comboTiposList.add(lista_libro.get(y).getTitulo());

                }
            }

            //comboTiposList.add(lista_prestamo.get(i).getDocumento_habilitante());
        }
        binding.comboLibro.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, comboTiposList));


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}