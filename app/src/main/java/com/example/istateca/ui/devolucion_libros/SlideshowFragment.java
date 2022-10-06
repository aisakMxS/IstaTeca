package com.example.istateca.ui.devolucion_libros;

import static com.example.istateca.V_principal.bibliotecario_ingresado;
import static com.example.istateca.ui.lista_libros.listar_librosFragment.idlibro;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.istateca.Clases.Bibliotecario;
import com.example.istateca.Clases.Libro;
import com.example.istateca.Clases.Persona;
import com.example.istateca.Clases.Prestamo;
import com.example.istateca.Clases.Usuario;
import com.example.istateca.MainActivity;
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
    UsuarioService usuarioService;
    PrestamoService prestamoService;
    LibroService libroService;
    List<Prestamo> lista_prestamo= new ArrayList<>();
    List<Libro> lista_libro= new ArrayList<>();
    ArrayList<Integer> combo_getID = new ArrayList<Integer>();
    int b=0;

    public Prestamo prestamo_actualizado;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDevolucionLBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Boolean rad = null;
                if(binding.radioSi.isChecked()==true){
                    rad=true;
                }else if(binding.radioNo.isChecked()==true){
                    rad=false;
                }
                byte[] matriz= null;
                String ced= binding.txtCedulaEstudiante.getText().toString();
                String rol=(String)binding.comboLibro.getSelectedItem();
                String estadoprestamo=(String)binding.comboEstado.getSelectedItem();
                String estadolibro=binding.txtEstadoL.getText().toString();
                String observacion = binding.txtObservacciones.getText().toString();
                String fecha= binding.btnFecha.getText().toString();
                String documento=binding.txtDocumento.getText().toString();

                System.out.println(fecha + " fecha");
                if(ced.isEmpty()) {
                    Toast.makeText(getActivity(),  " Ingrese la cedula", Toast.LENGTH_LONG).show();
                }else if (binding.comboLibro.getSelectedItem().toString().equals("Seleccione: ") ){
                    Toast.makeText(getActivity(),  " Seleccione el combo", Toast.LENGTH_LONG).show();
                }else if (b==0){
                    Toast.makeText(getActivity(),  " Seleccione la fecha", Toast.LENGTH_LONG).show();
                }else if(rad==null){
                    Toast.makeText(getActivity(),  " Seleccione si tiene penalizacion", Toast.LENGTH_LONG).show();
                }else if(binding.comboEstado.getSelectedItem().toString().equals("Seleccione")){
                    Toast.makeText(getActivity(),  " Seleccione el estado", Toast.LENGTH_LONG).show();
                }else {

                   if(!observacion.isEmpty()){
                       Usuario u =prestamo_actualizado.getUsuario();
                       u.setObservacion(observacion);
                       if(rad=true){
                           u.setCalificacion(u.getCalificacion()-1);
                           createUsuario(u);
                       }
                   }
                    prestamo_actualizado.setEstado_prestamo(estadoprestamo);
                    prestamo_actualizado.setEstado_libro(estadolibro);
                    prestamo_actualizado.setFecha_recibo(fecha);
                    prestamo_actualizado.setBibliotecario_recibido(bibliotecario_ingresado);
                    create(prestamo_actualizado);
                    limpiar();


                    Libro l= prestamo_actualizado.getLibro();
                    l.setDisponibilidad(true);
                    l.setEstado_libro(estadolibro);
                    createLibro(l);

                }
            }
        });



        binding.btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());
                binding.btnFecha.setText(d);
                b=1;
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
                    for (int x=0; x<combo_getID.size();x++){
                        if (x == id){
                            int idC =combo_getID.get(x);
                            for (int i = 0; i < lista_prestamo.size(); i++) {
                                if (idC == lista_prestamo.get(i).getId_prestamo()) {
                                    System.out.println("ID PRESTAMO: " + lista_prestamo.get(i).getId_prestamo() + " ID COMbo " + idC);
                                    String a = lista_prestamo.get(i).getFecha_entrega();
                                    String fecha_entrega = a.substring(0, 10);
                                    String b = lista_prestamo.get(i).getFecha_maxima();
                                    String fecha_maxima = b.substring(0, 10);
                                    binding.txtDocumento.setText(lista_prestamo.get(i).getDocumento_habilitante());
                                    binding.txtEstadoL.setText(lista_prestamo.get(i).getEstado_libro());
                                    binding.txtFechaEntrega.setText(fecha_entrega);
                                    binding.txtFechaMaxima.setText(fecha_maxima);
                                    binding.txtBibliotecarioEntrega.setText(lista_prestamo.get(i).getBibliotecario_entrega().getPersona().getNombres());
                                    binding.txtBibliotecarioRecibe.setText(bibliotecario_ingresado.getPersona().getNombres());

                                    prestamo_actualizado=lista_prestamo.get(i);
                                }
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
    public void limpiar(){
        binding.txtCedulaEstudiante.setText("");
        binding.txtBibliotecarioEntrega.setText("");
        binding.txtBibliotecarioRecibe.setText("");
        binding.txtEstadoL.setText("");
        binding.txtDocumento.setText("");
        binding.txtFechaMaxima.setText("");
        binding.btnFecha.setText("Cargar");
        binding.txtFechaEntrega.setText("");
        binding.txtObservacciones.setText("");
        binding.comboLibro.setAdapter(null);
        binding.comboEstado.setSelection(0);
        binding.radioNo.setChecked(false);
        binding.radioSi.setChecked(false);
    }
    private void createUsuario(Usuario usu){
        Retrofit retrofit= new Retrofit.Builder().
                baseUrl(Apis.URL_001).
                addConverterFactory(GsonConverterFactory.create()).build();
        usuarioService= retrofit.create(UsuarioService.class);
        Call<Usuario> call= usuarioService.addUsuario(usu);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if (!response.isSuccessful()){
                    Log.e("Error: ",response.message());
                    return;
                }
                Usuario usuario = response.body();
                /*Toast.makeText(RegistroActivity.this, "Create", Toast.LENGTH_SHORT).show();*/
                System.out.println(usuario.getPersona().getNombres());
            }
            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.e("response","fail");
            }
        });
    }
    private void createLibro(Libro l){
        Retrofit retrofit= new Retrofit.Builder().baseUrl(Apis.URL_001).addConverterFactory(GsonConverterFactory.create()).build();
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
            }
            @Override
            public void onFailure(Call<Libro> call, Throwable t) {
                Log.e("Error:",t.getMessage());
                System.out.println("error");
            }
        });
    }

    private void create(Prestamo pres){
        Retrofit retrofit= new Retrofit.Builder().baseUrl(Apis.URL_001).addConverterFactory(GsonConverterFactory.create()).build();
        prestamoService= retrofit.create(PrestamoService.class);
        Call<Prestamo> call= prestamoService.addPrestamo(pres);
        call.enqueue(new Callback<Prestamo>() {
            @Override
            public void onResponse(Call<Prestamo> call, Response<Prestamo> response) {
                if(!response.isSuccessful()){
                    //Toast.makeText("Se agrego con exito", Toast.LENGTH_LONG).show();
                    Log.e("Response erra", response.message());
                    return;
                }
                Prestamo pres=response.body();
                Toast.makeText(getActivity()," Modificado", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Prestamo> call, Throwable t) {
                Log.e("Error:",t.getMessage());
                System.out.println("error");
            }
        });
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

    private void comboLibro() {
        combo_getID.add(0);
        ArrayList<String> comboTiposList = new ArrayList<String>();
        comboTiposList.add("Seleccione: ");
        for (int i=0; i< lista_prestamo.size(); i++){
            for (int y=0; y< lista_libro.size(); y++){
                    if((lista_prestamo.get(i).getLibro().getId_libro()==lista_libro.get(y).getId_libro())&&lista_prestamo.get(i).getEstado_prestamo().equalsIgnoreCase("Entregado")){
                        comboTiposList.add(lista_libro.get(y).getTitulo());
                        combo_getID.add(lista_prestamo.get(i).getId_prestamo());
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