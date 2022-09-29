package com.example.istateca.ui.devolucion_libros;

import static com.example.istateca.V_principal.bibliotecario_ingresado;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.example.istateca.Utils.BibliotecarioService;
import com.example.istateca.Utils.LibroService;
import com.example.istateca.Utils.PrestamoService;
import com.example.istateca.Utils.UsuarioService;
import com.example.istateca.V_principal;
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
    BibliotecarioService bibliotecarioService;
    UsuarioService usuarioService;
    List<Prestamo> lista_prestamo= new ArrayList<>();
    List<Persona> lista_persona= new ArrayList<>();
    List<Usuario> lista_usuario = new ArrayList<>();
    List<Bibliotecario> lista_bibliotecarios = new ArrayList<>();
    List<Libro> lista_libro= new ArrayList<>();
    int id_prest=0;

   // V_principal.

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDevolucionLBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(" id preswtamo " + id_prest );
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

            }
        });
        binding.comboLibro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    for (int i = 1; i <= lista_prestamo.size(); i++) {

                        if (i == id) {
                            String a = lista_prestamo.get(i - 1).getFecha_entrega();
                            String fecha_entrega = a.substring(0, 10);
                            String b = lista_prestamo.get(i - 1).getFecha_maxima();
                            String fecha_maxima = b.substring(0, 10);
                            System.out.println("posicion: " + i + " lista " + position);
                            binding.txtDocumento.setText(lista_prestamo.get(i - 1).getDocumento_habilitante());
                            binding.txtEstadoL.setText(lista_prestamo.get(i - 1).getEstado_libro());
                            binding.txtFechaEntrega.setText(fecha_entrega);
                            binding.txtFechaMaxima.setText(fecha_maxima);
                            cargarDatos(lista_prestamo.get(i - 1).getBibliotecario_entrega());
                            id_prest=lista_prestamo.get(i-1).getId_prestamo();
                            binding.txtBibliotecarioRecibe.setText(bibliotecario_ingresado.getPersona().getNombres());
                        }
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
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
                System.out.println(lista_prestamo.size() + " prestamos cedula");
                comboLibro();
            }

            @Override
            public void onFailure(Call<List<Prestamo>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }

    public void getUsuario(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Apis.URL_001)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        usuarioService=retrofit.create(UsuarioService.class);
        Call<List<Usuario>> call= usuarioService.getListarUsuarios();
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

    private void getBuscarBibliotecario(int id) {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Apis.URL_001)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        bibliotecarioService =retrofit.create(BibliotecarioService.class);
        Call<Bibliotecario> call=bibliotecarioService.getBuscarDatos(id);
        call.enqueue(new Callback<Bibliotecario>() {
            @Override
            public void onResponse(Call<Bibliotecario> call, Response<Bibliotecario> response) {
                if (!response.isSuccessful()){

                    Log.e("Response err: ",response.message());
                    return;
                }
                Bibliotecario bibliotecario= response.body();
                cargarDatos(bibliotecario);
            }

            @Override
            public void onFailure(Call<Bibliotecario> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
    public void cargarDatos(Bibliotecario bibliotecario){
        binding.txtBibliotecarioEntrega.setText(bibliotecario.getPersona().getNombres());

    }

    private void comboLibro() {
        ArrayList<String> comboTiposList = new ArrayList<String>();
        comboTiposList.add("Seleccione: ");
        for (int i=0; i< lista_prestamo.size(); i++){
            for (int y=0; y< lista_libro.size(); y++){
                    if((lista_prestamo.get(i).getLibro().getId_libro()==lista_libro.get(y).getId_libro())&&lista_prestamo.get(i).getEstado_prestamo().equalsIgnoreCase("entregado")){
                        comboTiposList.add(lista_libro.get(y).getTitulo());
                    }
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