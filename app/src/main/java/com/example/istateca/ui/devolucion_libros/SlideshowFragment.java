package com.example.istateca.ui.devolucion_libros;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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
    ArrayList<Integer> lista_datos = new ArrayList<Integer>();
    ArrayList<Integer> lista_da = new ArrayList<Integer>();
    ArrayList<Integer> lista_d = new ArrayList<Integer>();




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
                System.out.println(cedula);
                getBuscarCedula(cedula);
                comboLibro(cedula);
            }
        });
        binding.comboLibro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int d=0;
                System.out.println("posicion " + position + "es " + id );
                for (int i=0; i< lista_datos.size(); i++){

                    if (lista_datos.get(i)==position){
                        System.out.println("datos " + lista_datos.get(i) + " posicioon " + position);

                        for (int y=0;y< lista_prestamo.size(); y++){
                            for (int z=0;z< lista_da.size(); z++){
                                System.out.println("comparacion");

                                if (lista_prestamo.get(y).getId_prestamo()==lista_da.get(z)){
                                    System.out.println(lista_prestamo.get(y).getId_prestamo() + "prestmao");
                                    System.out.println(lista_prestamo.get(y).getId_prestamo() + " lista prestmao");
                                    binding.txtBibliotecarioRecibe.setText(lista_prestamo.get(y).getBibliotecario_recibido()+"");
                                    binding.txtBibliotecarioEntrega.setText(lista_prestamo.get(y).getDocumento_habilitante());
                                    binding.txtFechaEntrega.setText(lista_prestamo.get(y).getFecha_entrega());
                                    binding.txtEstadoL.setText(lista_prestamo.get(y).getEstado_prestamo());
                                    break;
                                }
                            }

                        }

                    }
                }



            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });



        //getUsuario();
        listarLibros();
        return root;

    }
    private void getBuscarCedula(String id) {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Apis.URL_001)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        prestamoService =retrofit.create(PrestamoService.class);
        Call<List<Prestamo>> call=prestamoService.getBuscarCedula(id);
        call.enqueue(new Callback<List<Prestamo>>() {
            @Override
            public void onResponse(Call<List<Prestamo>> call, Response <List<Prestamo>> response) {
                if (!response.isSuccessful()){

                    Log.e("Response err: ",response.message());
                    return;

                }
                lista_prestamo =response.body();
                //Prestamo prestamo=response.body();

                System.out.println(lista_prestamo.size() + " prestamos cedula");
               // Libro libros=response.body();
                //cargarDatos(libros);
            }

            @Override
            public void onFailure(Call<List<Prestamo>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

   /* public void getUsu(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Apis.URL_001)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        usuarioService= retrofit.create(UsuarioService.class);
        Call<Persona> call= usuarioService.getUsuario(binding.txtCedulaEstudiante.getText().toString());
        call.enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(Call<Persona> call, Response<Persona> response) {
                if (response.isSuccessful()){
                    Log.e("Response err: ", response.message());
                    System.out.println("Estoy aquiiiiiiii en el on response");
                    lista_persona = (List<Persona>) response.body();
                    System.out.println(lista_persona.size());
                    //comboLibro();
                    return;
                }
            }
            @Override
            public void onFailure(Call<Persona> call, Throwable t) {
                System.out.println("Error");
                Log.e("Error:",t.getMessage());
            }
        });
    }
    public void getUsuario(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Apis.URL_001)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        usuarioService=retrofit.create(UsuarioService.class);
        Call<List<Usuario>> call= usuarioService.getListarUsuario();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (!response.isSuccessful()){
                    Log.e("Response err: ",response.message());
                    return;
                }
                lista_usuario=response.body();
                System.out.println(lista_usuario.size() + " usuarios");
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }*/
    private int comboL(String nombre){
        int t=0;
        for(int i=0; i<lista_libro.size(); i++){
            if(lista_libro.get(i).getTitulo().equals(nombre)){
                System.out.println(lista_libro.get(i).getTitulo());
                t= lista_libro.get(i).getId_libro();
            }
        }
        return t;
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

    /*private void getPrestamo(){
        prestamoService= Apis.getPrestamoService();
        Call<List<Prestamo>>call=prestamoService.getListarPrestamo();
        call.enqueue(new Callback<List<Prestamo>>() {
            @Override
            public void onResponse(Call<List<Prestamo>> call, Response<List<Prestamo>> response) {
                if(response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    lista_prestamo = response.body();
                    System.out.println(lista_prestamo.size() + " prestamos");
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

    }*/

    private void comboLibro(String cedula) {

        ArrayList<String> comboTiposList = new ArrayList<String>();

        comboTiposList.add("Seleccione: ");


        for (int i=0; i< lista_prestamo.size(); i++){
            System.out.println("libro prestamo" + lista_prestamo.get(i).getLibro().getId_libro());
            for (int y=0; y< lista_libro.size(); y++){
                System.out.println("libro lista" + lista_libro.get(i).getId_libro());
                //if (lista_prestamo.get(i).getEstado_libro().equalsIgnoreCase("entregado")){
                    if((lista_prestamo.get(i).getLibro().getId_libro()==lista_libro.get(y).getId_libro())){
                        comboTiposList.add(lista_libro.get(y).getTitulo());
                    }
                //}

            }
        }
        binding.comboLibro.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, comboTiposList));
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}