package com.example.istateca.ui.lista_libros;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.istateca.Clases.Libro;
import com.example.istateca.R;
import com.example.istateca.Utils.Apis;
import com.example.istateca.Utils.LibroService;
import com.example.istateca.databinding.FragmentLLibrosBinding;
import com.example.istateca.ui.registro_libros.registro_librosFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class listar_librosFragment extends Fragment {
    LibroService libroService;
    public static List<Libro> libros;
    ListView recyclerView ;
    public static int id=0;
    private FragmentLLibrosBinding binding;
    Dialog dialogo;
    ImageView editar;
    TextView textitulo,textcodigodewey,textdescripcion,tipo,editor,ciudad,area,codigoisbn,estadolibro, urldigital,idioma,donante,num_paginas,anio_publicacion,indice1,indice2,indice3,dimesiones;
    public static int validar=0;
    public static int idlibro=0;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentLLibrosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView= binding.listaLibros;
        listarLibros();
        dialogo=new Dialog(getActivity());
        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                dialogo.setContentView(R.layout.dialogo_detalle_libro);
                editar=(ImageView) dialogo.findViewById(R.id.img_editar);
                editar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        idlibro=libros.get(i).getId_libro();
                        abrirEditar();
                    }
                });
                datos(dialogo,i);
                dialogo.show();
            }
        });
        return root;
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
        tipo.setText(String.valueOf(libros.get(i).getTipo()));
        editor.setText(libros.get(i).getEditor());
        ciudad.setText(libros.get(i).getEditor());
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
}