package com.example.istateca.ui.registro_libros;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.istateca.Clases.Autor;
import com.example.istateca.Clases.Libro;
import com.example.istateca.Utils.Apis;
import com.example.istateca.Utils.AutorService;
import com.example.istateca.Utils.LibroService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroLibrosViewModel extends ViewModel {

    private static MutableLiveData<Autor> autor_espera = new MutableLiveData<>();
    public LiveData<Autor> getAutor_Espera() {
        return autor_espera;
    }

    private static MutableLiveData<Libro> libro_espera = new MutableLiveData<>();
    public LiveData<Libro> getLibro_Espera() {
        return libro_espera;
    }

    private static MutableLiveData<List<Autor>> lista_autores = new MutableLiveData<>();
    public LiveData<List<Autor>> getLista_Autores() {
        return lista_autores;
    }

    private int comparador=0;
    public int getComparador() {
        return comparador;
    }
    public void setComparador(int comparador) {
        this.comparador = comparador;
    }

    public void CrearAutores(List<String> comboAutorList, Context context){
        for(int i=0; i<comboAutorList.size(); i++){
            int con=0;
            Autor au= new Autor(lista_autores.getValue().size()+1,comboAutorList.get(i));
            for(int j=0; j<lista_autores.getValue().size(); j++){
                if(lista_autores.getValue().get(j).getNombre().equalsIgnoreCase(au.getNombre())){
                    System.out.println("Autor Existente");
                    con=1;
                }
            }
            if(con!=1){
                CrearAutor(au,context);
                comparador++;
            }
        }
    }

    public void CrearAutor(Autor au, Context context) {
        AutorService autorService;
        Retrofit retrofit= new Retrofit.Builder().baseUrl(Apis.URL_001).addConverterFactory(GsonConverterFactory.create()).build();
        autorService= retrofit.create(AutorService.class);
        Call<Autor> call= autorService.addAutor(au);
        call.enqueue(new Callback<Autor>() {
            @Override
            public void onResponse(Call<Autor> call, Response<Autor> response) {
                if(!response.isSuccessful()){
                    Log.e("Autor Creado", response.message());
                    return;
                }
                autor_espera.setValue(response.body());
                Toast.makeText(context, autor_espera.getValue().getNombre()+" Autor creado correctamente", Toast.LENGTH_LONG).show();
                comparador++;
            }
            @Override
            public void onFailure(Call<Autor> call, Throwable t) {
                Log.e("Error:",t.getMessage());
                System.out.println("error");
            }
        });
    }

    public void getAutor(){
        AutorService autorService;
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Apis.URL_001).addConverterFactory(GsonConverterFactory.create()).build();
        autorService= retrofit.create(AutorService.class);
        Call<List<Autor>> call= autorService.getAutor();
        call.enqueue(new Callback<List<Autor>>() {
            @Override
            public void onResponse(Call<List<Autor>> call, Response<List<Autor>> response) {
                if(!response.isSuccessful()){
                    Log.e("Response err: ", response.message());
                    return;
                }
                lista_autores.setValue(response.body());
                //combotipo();
            }

            @Override
            public void onFailure(Call<List<Autor>> call, Throwable t) {
                Log.e("Response err: ", t.getMessage());
            }
        });


    }

    public void crear_libro(Libro l, int v,Context context){
        LibroService libroService;
        Retrofit retrofit= new Retrofit.Builder().baseUrl(Apis.URL_001).addConverterFactory(GsonConverterFactory.create()).build();
        libroService= retrofit.create(LibroService.class);
        Call<Libro> call= libroService.addlibro(l);
        call.enqueue(new Callback<Libro>() {
            @Override
            public void onResponse(Call<Libro> call, Response<Libro> response) {
                if(!response.isSuccessful()){
                    Log.e("Response erra", response.message());
                    return;
                }
                libro_espera.setValue(response.body());
                if(v==0) {
                    Toast.makeText(context, l.getTitulo() + " Creado", Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(context, l.getTitulo() + " Modificado", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Libro> call, Throwable t) {
                Log.e("Error:",t.getMessage());
                System.out.println("error");
            }
        });
    }
}
