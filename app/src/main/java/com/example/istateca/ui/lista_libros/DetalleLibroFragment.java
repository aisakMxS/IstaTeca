package com.example.istateca.ui.lista_libros;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.istateca.Clases.Libro;
import com.example.istateca.Utils.Apis;
import com.example.istateca.Utils.LibroService;
import com.example.istateca.databinding.FragmentDetalleLibroBinding;
import com.example.istateca.databinding.FragmentRegistroLibrosBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class DetalleLibroFragment extends Fragment {
    LibroService libroService;
    TextView textitulo,textcodigodewey,textdescripcion,tipo,editor,ciudad,area,codigoisbn,estadolibro,
            urldigital,idioma,donante,num_paginas
            ,anio_publicacion,indice1,indice2,indice3,dimesiones;

    private com.example.istateca.databinding.FragmentDetalleLibroBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDetalleLibroBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        System.out.println("entroooooooooooooooooooooooooooooooo "+ listar_librosFragment.id);

        textitulo=binding.txtTituloLibro;
        textcodigodewey=binding.txtCodigodewey;
        textdescripcion=binding.txtDescocion;
        tipo=binding.txtTipo;
        editor=binding.txtEditorlibro;
        ciudad=binding.txtCiudadlibro;
        area=binding.txtArealibro;
        codigoisbn=binding.txtCodigoIsbnLibro;
        estadolibro=binding.txtEstadoLibroL;
        urldigital=binding.txtUrlDigital;
        idioma=binding.txtIdiomaLibro;
        donante=binding.txtNombreDonanteLibro;
        num_paginas=binding.txtNumPaginas;
        anio_publicacion=binding.txtFechaPublicacion;
        indice1=binding.txtIndice1Libro;
        indice2=binding.txtIndice2Libro;
        indice3=binding.txtIndice3Libro;
        dimesiones=binding.txtDiimensiones;

        getBuscarDatos(listar_librosFragment.id);

        return root;

    }

    private void getBuscarDatos(int id) {
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Apis.URL_001)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        libroService =retrofit.create(LibroService.class);
        Call<Libro> call=libroService.getBuscarDatos(id);
        call.enqueue(new Callback<Libro>() {
            @Override
            public void onResponse(Call<Libro> call, Response<Libro> response) {
                if (!response.isSuccessful()){

                    Log.e("Response err: ",response.message());
                    return;
                }
                Libro libros=response.body();
                System.out.println("datos cargados");
                cargarDatos(libros);
            }

            @Override
            public void onFailure(Call<Libro> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
    public void cargarDatos( Libro libro){
        textitulo.setText(libro.getTitulo());
        textcodigodewey.setText(libro.getCodigo_dewey());
        textdescripcion.setText(libro.getDescripcion());
        tipo.setText(String.valueOf(libro.getTipo()));
        editor.setText(libro.getEditor());
        ciudad.setText(libro.getEditor());
        area.setText(libro.getArea());
        codigoisbn.setText(libro.getCod_ISBN());
        estadolibro.setText(libro.getEstadoLibro());
        urldigital.setText(libro.getUrl_digital());
        idioma.setText(libro.getIdioma());
        donante.setText(libro.getNombre_donante());
        num_paginas.setText(libro.getNum_paginas());
        anio_publicacion.setText(libro.getAnio_publicacion());
        indice1.setText(libro.getIndice_uno());
        indice2.setText(libro.getIndice_dos());
        indice3.setText(libro.getIndice_tres());
        dimesiones.setText(libro.getDimensiones());

    }
}