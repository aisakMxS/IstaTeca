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
    PrestamoService prestamoService;
    LibroService libroService;
    BibliotecarioService bibliotecarioService;
    UsuarioService usuarioService;
    List<Prestamo> lista_prestamo= new ArrayList<>();
    List<Persona> lista_persona= new ArrayList<>();
    List<Usuario> lista_usuario = new ArrayList<>();
    List<Bibliotecario> lista_bibliotecarios = new ArrayList<>();
    List<Libro> lista_libro= new ArrayList<>();
    public static int id_prest=0;
    public static int id_recibe=0;
    public static int id_usuario=0;
    public static int id_libro=0;
    public static int id_entrega=0;
    String fecha_entrega, fecha_maxima,fecha_entre, fecha_max;
    int a=0;
    int pre=0;
    int b=0;
   // V_principal.

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
                }else if(observacion.isEmpty()){
                    Toast.makeText(getActivity(),  " Flata completar la observacion", Toast.LENGTH_LONG).show();
                }else{
                    //Prestamo p =new Prestamo(estadolibro,fecha,b_recibe,rad,matriz);
                    /*p.setBibliotecario_recibido(b_recibe);
                    p.setEstado_libro(estadolibro);
                    p.setEstado_prestamo(estadoprestamo);
                    p.setFecha_recibo(fecha);
                    p.setActivo(rad);*/
                    Libro li= new Libro(id_libro);
                    Bibliotecario b_recibe=new Bibliotecario(id_recibe);
                    Bibliotecario b_entrega=new Bibliotecario(id_entrega);
                    Usuario usu= new Usuario(id_usuario);
                    System.out.println(" id preswtamo " + id_prest );
                    System.out.println(" Usuario " + usu.getId());
                    System.out.println(" Libro " + li.id_libro);
                    System.out.println("estado libro " + estadolibro);
                    System.out.println("estado prestamo " + estadoprestamo);
                    System.out.println("fecha entrega " + fecha_entre);
                    System.out.println("bibiliotecario entrega " + b_entrega.getId());
                    System.out.println("documento " + documento);
                    System.out.println("fecha " + fecha);
                    System.out.println("Bibliotecario recibe " + b_recibe.getId());
                    System.out.println("fecha maxima " + fecha_max);
                    System.out.println(" penalizacion " + rad);
                    System.out.println("matriz  " + matriz);

                    System.out.println("id bibliotecario " + id_recibe);
                    System.out.println(" id libro " +id_libro);

                    System.out.println("observacion " + observacion);
                    //actualizar(p,id_prest);
                    Prestamo pres = new Prestamo(id_prest,usu,li,estadolibro,estadoprestamo,fecha_entre,b_entrega,documento,
                            fecha,b_recibe,fecha_max,rad,matriz);
                    create(pres,1);
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
                            binding.txtBibliotecarioEntrega.setText(lista_prestamo.get(i-1).getBibliotecario_entrega().getPersona().getNombres());
                            fecha_entre=lista_prestamo.get(i-1).getFecha_entrega();
                            fecha_max=lista_prestamo.get(i-1).getFecha_maxima();
                            binding.txtBibliotecarioRecibe.setText(bibliotecario_ingresado.getPersona().getNombres());

                            id_entrega=lista_prestamo.get(i-1).getBibliotecario_entrega().getId();
                            id_libro=lista_prestamo.get(i-1).getLibro().getId_libro();
                            id_prest=lista_prestamo.get(i-1).getId_prestamo();
                            id_recibe= bibliotecario_ingresado.getId();
                            id_usuario=lista_prestamo.get(i-1).getUsuario().getId();
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



    private void create(Prestamo pres, int v){
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
                // Toast.makeText(registro_librosFragment.this,l.getCodigoDewey()+" created!", Toast.LENGTH_LONG).show();
                if(v==0) {
                    Toast.makeText(getActivity(), " Creado", Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(getActivity()," Modificado", Toast.LENGTH_LONG).show();
                }
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
                a=1;
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

    public void actualizar(Prestamo pres,int id){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Apis.URL_001)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        prestamoService=retrofit.create(PrestamoService.class);
        Call<Prestamo> call= prestamoService.updatePersona(pres,id);
        System.out.println("id " + id);
        System.out.println(pres.getEstado_libro() + " recordando java");

        call.enqueue(new Callback<Prestamo>() {
            @Override
            public void onResponse(Call<Prestamo> call, Response<Prestamo> response) {
                if (!response.isSuccessful()){
                    Log.e("Response err: ",response.message());
                    return;
                }
                Toast.makeText(getActivity(),  "Actualizado", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Prestamo> call, Throwable t) {
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
        if (a==0){
            Toast.makeText(getActivity(), "Esta cedula no tiene prestamos ", Toast.LENGTH_SHORT).show();
            System.out.println("Esta cedula no tiene prestamo");
            binding.txtCedulaEstudiante.setText("");
        }else{
            binding.txtCedulaEstudiante.getText().toString();
        }
        ArrayList<String> comboTiposList = new ArrayList<String>();
        comboTiposList.add("Seleccione: ");
        for (int i=0; i< lista_prestamo.size(); i++){
            for (int y=0; y< lista_libro.size(); y++){
                    if((lista_prestamo.get(i).getLibro().getId_libro()==lista_libro.get(y).getId_libro())&&lista_prestamo.get(i).getEstado_prestamo().equalsIgnoreCase("Solicitado")){
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