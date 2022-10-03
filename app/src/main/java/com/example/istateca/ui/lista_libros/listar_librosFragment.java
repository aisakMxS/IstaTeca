package com.example.istateca.ui.lista_libros;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.istateca.Clases.Autor;
import com.example.istateca.Clases.Bibliotecario;
import com.example.istateca.Clases.Libro;
import com.example.istateca.Clases.Persona;
import com.example.istateca.Clases.Prestamo;
import com.example.istateca.Clases.Tipo;
import com.example.istateca.Clases.Usuario;
import com.example.istateca.R;
import com.example.istateca.Utils.Apis;
import com.example.istateca.Utils.LibroService;
import com.example.istateca.Utils.PrestamoService;
import com.example.istateca.databinding.FragmentLLibrosBinding;
import com.example.istateca.ui.registro_libros.registro_librosFragment;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.example.istateca.V_principal.usuario_ingresado;
import static com.example.istateca.V_principal.bibliotecario_ingresado;

public class listar_librosFragment extends Fragment implements SearchView.OnQueryTextListener {
    LibroService libroService;
    PrestamoService prestamoService;
    public static List<Libro> libros;
    ListView recyclerView ;
    public static int id=0;
    private FragmentLLibrosBinding binding;
    Dialog dialogo, dialogoSolicitud;
    ImageView editar,img_qr;Button btn_solicitar,btn_aceptar;
    TextView textitulo,txt_titulo_soli,textcodigodewey,textdescripcion,tipo,editor,ciudad,area,codigoisbn,estadolibro, urldigital,idioma,donante,num_paginas,anio_publicacion,indice1,indice2,indice3,dimesiones;
    public static int validar=0;
    public static int idlibro=0;
    List<Libro> lista_librosbuscar= new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLLibrosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView= binding.listaLibros;
        listarLibros();
        //buscar libro
        binding.txtbuscar.setOnQueryTextListener(this);

        //Dialogo del detalle del libro

        dialogo=new Dialog(getActivity());
        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialogo.setContentView(R.layout.dialogo_detalle_libro);
                editar=(ImageView) dialogo.findViewById(R.id.img_editar);
                btn_solicitar=(Button) dialogo.findViewById(R.id.btn_solicitar);
                editar.setVisibility(View.GONE);
                btn_solicitar.setVisibility(View.GONE);
                //Validar bibliotecario-Usuario
                if(bibliotecario_ingresado==null){
                    if(libros.get(i).getDisponibilidad()==true){
                        btn_solicitar.setVisibility(View.VISIBLE);
                    }
                    System.out.println("USUARIO");
                }else {
                    editar.setVisibility(View.VISIBLE);
                    System.out.println("BIBLIOTECARIO");
                }
                //Solicitar

                editarLibro(i);
                datos(dialogo,i);
                btn_solicitar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        solicitar_libro(i);
                    }
                });
                TextView txtcerrar=(TextView) dialogo.findViewById(R.id.txt_cerrar_detalle);
                txtcerrar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogo.dismiss();
                    }
                });
                dialogo.show();
            }
        });
        return root;


    }
    public void generarQR(Dialog dialog, int id){
        img_qr=dialog.findViewById(R.id.img_codigo_qr);
        try {
            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
            Bitmap bitmap=barcodeEncoder.encodeBitmap(
                    id+"",
                    BarcodeFormat.QR_CODE,
                    350,
                    350
            );
            img_qr.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void editarLibro(int i){
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idlibro=libros.get(i).getId_libro();
                abrirEditar();
            }
        });
    }
    public void solicitar_libro(int i){
        dialogoSolicitud= new Dialog(getActivity());
        dialogoSolicitud.setContentView(R.layout.dialogo_solicitud_libro);
        txt_titulo_soli=(TextView) dialogoSolicitud.findViewById(R.id.txt_titulo_libro_solicitud);
        btn_aceptar=(Button) dialogoSolicitud.findViewById(R.id.btn_ace_solicitud);
        txt_titulo_soli.setText(libros.get(i).getTitulo());
        dialogoSolicitud.show();

        Libro l = new Libro(libros.get(i).getId_libro(),libros.get(i).getCodigo_dewey(),libros.get(i).getTitulo(), libros.get(i).getTipo()
                , libros.get(i).getAdquisicion(), libros.get(i).getAnio_publicacion(),libros.get(i).getEditor(),libros.get(i).getCiudad(),libros.get(i).getNum_paginas(),
                libros.get(i).getArea(), libros.get(i).getCod_ISBN(), libros.get(i).getIdioma(),libros.get(i).getDescripcion(), libros.get(i).getIndice_uno(), libros.get(i).getIndice_dos(),libros.get(i).getIndice_tres(),
                libros.get(i).getDimensiones(),libros.get(i).getEstadoLibro(),true,null,libros.get(i).getUrl_digital(),libros.get(i).getIdbibliotecario(),libros.get(i).getFecha_creacion(),false,libros.get(i).getNombre_donante(),null);
        System.out.println("Solicitar libro "+ libros.get(i).getTitulo());
        Prestamo prestamo=new Prestamo(0,usuario_ingresado,l,
                null,"Solicitado",null,null,
                null,null,null,null,
                true,null);
        crearprestamo(prestamo);

        create(l,1);
        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogoSolicitud.dismiss();
                dialogo.dismiss();
                listarLibros();
            }
        });
    }



    private void create(Libro l, int v){
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
                // Toast.makeText(registro_librosFragment.this,l.getCodigoDewey()+" created!", Toast.LENGTH_LONG).show();
                if(v==0) {
                    Toast.makeText(getActivity(), l.getTitulo() + " Creado", Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(getActivity(), l.getTitulo() + " Modificado", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Libro> call, Throwable t) {
                Log.e("Error:",t.getMessage());
                System.out.println("error");
            }
        });
    }
    private void crearprestamo(Prestamo l){
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
                generarQR(dialogoSolicitud,l.getId_prestamo());

            }

            @Override
            public void onFailure(Call<Prestamo> call, Throwable t) {
                Log.e("Error:",t.getMessage());
                System.out.println("error");
            }
        });
    }

    public void abrirEditar(){
        registro_librosFragment homeFragment = new registro_librosFragment();
        FragmentTransaction fragmentTransaction= getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.listarlibroos,homeFragment);
        fragmentTransaction.commit();
        dialogo.dismiss();
        validar =1;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void listarLibros(){
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
                libros=response.body();
                lista_librosAdapter lista_librosAdapter= new lista_librosAdapter(libros,getActivity());
                recyclerView.setAdapter(lista_librosAdapter);
                libros.forEach(p-> System.out.println(libros.toString()));
            }

            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
    public void datos(Dialog dialog,int i){
        textitulo=dialog.findViewById(R.id.text_tituloLibro);
        textcodigodewey=dialog.findViewById(R.id.text_codigodewey);
        textdescripcion=dialog.findViewById(R.id.text_descripcion);
        tipo=dialog.findViewById(R.id.text_tipo);
        editor=dialog.findViewById(R.id.text_editorlibro);
        ciudad=dialog.findViewById(R.id.text_ciudadlibro);
        area=dialog.findViewById(R.id.text_arealibro);
        codigoisbn=dialog.findViewById(R.id.text_codigo_isbn_libro);
        estadolibro=dialog.findViewById(R.id.text_estado_libro_l);
        urldigital=dialog.findViewById(R.id.text_url_digital);
        idioma=dialog.findViewById(R.id.text_idiomaLibro);
        donante=dialog.findViewById(R.id.text_nombreDonanteLibro);
        num_paginas=dialog.findViewById(R.id.text_numPaginas);
        anio_publicacion=dialog.findViewById(R.id.text_fecha_publicacion);
        indice1=dialog.findViewById(R.id.text_indice1Libro);
        indice2=dialog.findViewById(R.id.text_indice2Libro);
        indice3=dialog.findViewById(R.id.text_indice3Libro);
        dimesiones=dialog.findViewById(R.id.text_diimensiones);

        textitulo.setText(libros.get(i).getTitulo());
        textcodigodewey.setText(libros.get(i).getCodigo_dewey());
        textdescripcion.setText(libros.get(i).getDescripcion());
        tipo.setText(libros.get(i).getTipo().getNombre());
        editor.setText(libros.get(i).getEditor());
        ciudad.setText(libros.get(i).getCiudad());
        area.setText(libros.get(i).getArea());
        codigoisbn.setText(libros.get(i).getCod_ISBN());
        estadolibro.setText(libros.get(i).getEstadoLibro());
        urldigital.setText(libros.get(i).getUrl_digital());
        idioma.setText(libros.get(i).getIdioma());
        donante.setText(libros.get(i).getNombre_donante());
        num_paginas.setText(String.valueOf(libros.get(i).getNum_paginas()));
        anio_publicacion.setText(String.valueOf(libros.get(i).getAnio_publicacion()));
        indice1.setText(libros.get(i).getIndice_uno());
        indice2.setText(libros.get(i).getIndice_dos());
        indice3.setText(libros.get(i).getIndice_tres());
        dimesiones.setText(libros.get(i).getDimensiones());

    }

    public void buscarLibroxnombre(String titulo) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Apis.URL_001)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        libroService = retrofit.create(LibroService.class);
        Call<List<Libro>> call = libroService.getBuscarLibrosNombre(titulo);
        call.enqueue(new Callback<List<Libro>>() {
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {
                if (!response.isSuccessful()) {
                    Log.e("Response err: ", response.message());
                    return;
                }
                lista_librosbuscar= response.body();
                lista_librosAdapter lista_librosAdapter= new lista_librosAdapter(lista_librosbuscar,getActivity());
                recyclerView.setAdapter(lista_librosAdapter);
                System.out.println("Actualizando Autores" + lista_librosbuscar.size());
            }

            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {
                Log.e("Error:",t.getMessage());
                System.out.println("error");
            }
        });

    }

    //Eventos del teclado(Buscar libro-filtro)
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if(binding.txtbuscar.getQuery().toString().length()==0){
            listarLibros();
        }else{
            buscarLibroxnombre(binding.txtbuscar.getQuery().toString());
        }
        return false;
    }
}