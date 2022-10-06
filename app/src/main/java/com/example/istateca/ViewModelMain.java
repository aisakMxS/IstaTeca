package com.example.istateca;


import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.istateca.Clases.Bibliotecario;
import com.example.istateca.Clases.Persona;
import com.example.istateca.Clases.Usuario;
import com.example.istateca.Utils.Apis;
import com.example.istateca.Utils.BibliotecarioService;
import com.example.istateca.Utils.PersonaService;
import com.example.istateca.Utils.UsuarioService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewModelMain extends ViewModel {

    private static MutableLiveData<Usuario> usuario_ingresado = new MutableLiveData<>();

    public LiveData<Usuario> getUsuario_Ingrtesado() {
        return usuario_ingresado;
    }

    private static MutableLiveData<Bibliotecario> bibliotecario_ingresado = new MutableLiveData<>();

    public LiveData<Bibliotecario> getBibliotecario_Ingrtesado() {
        return bibliotecario_ingresado;
    }

    private static MutableLiveData<Persona> persona_p = new MutableLiveData<>();

    public LiveData<Persona> getPersona() {
        return persona_p;
    }

    public int reg;


    public void validarpersona(String usu, String clave) {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(Apis.URL_002).
                addConverterFactory(GsonConverterFactory.create()).build();
        PersonaService personaser = retrofit.create(PersonaService.class);
        Call<Persona> call = personaser.validarPersona(usu, clave);
        call.enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(Call<Persona> call, Response<Persona> response) {
                persona_p.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Persona> call, Throwable t) {
                Log.e("response", "fail");
            }
        });
    }

    public void iniciousuario() {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(Apis.URL_002).
                addConverterFactory(GsonConverterFactory.create()).build();
        PersonaService personaser = retrofit.create(PersonaService.class);
        if (persona_p.getValue().getRol() == 2) {
            Call<Usuario> call = personaser.tipoUsuario(persona_p.getValue().getId(), persona_p.getValue().getRol());
            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (!response.isSuccessful()) {
                        Log.e("Error: ", response.message());
                        return;
                    }
                    usuario_ingresado.setValue(response.body());
                    bibliotecario_ingresado.setValue(null);
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Log.e("response", "fail");
                }
            });
        } else {
            Call<Bibliotecario> call = personaser.tipoBibliotecario(persona_p.getValue().getId(), persona_p.getValue().getRol());
            call.enqueue(new Callback<Bibliotecario>() {
                @Override
                public void onResponse(Call<Bibliotecario> call, Response<Bibliotecario> response) {
                    if (!response.isSuccessful()) {
                        Log.e("Error: ", response.message());
                        return;
                    } else {
                        bibliotecario_ingresado.setValue(response.body());
                        usuario_ingresado.setValue(null);
                    }
                }

                @Override
                public void onFailure(Call<Bibliotecario> call, Throwable t) {
                    Log.e("response", "fail");
                }
            });
        }
    }

    public void modificar(boolean control){
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(Apis.URL_002).
                addConverterFactory(GsonConverterFactory.create()).build();
        if (control){
            UsuarioService ususer= retrofit.create(UsuarioService.class);
            //Call<Usuario> call = ususer.modificar(V_principal.usuario_ingresado,V_principal.usuario_ingresado.getId());
            Call<Usuario> call = ususer.addUsuario(V_principal.usuario_ingresado);
            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (!response.isSuccessful()) {
                        Log.e("Error: ", response.message());
                        return;
                    }
                    usuario_ingresado.setValue(response.body());
                    V_principal.usuario_ingresado=usuario_ingresado.getValue();
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Log.e("response", "fail");
                }
            });
        }else{
            BibliotecarioService bibser=retrofit.create(BibliotecarioService.class);
            System.out.println("IDENTRAAAAAA "+V_principal.bibliotecario_ingresado.toString());
            //Call<Bibliotecario> call = bibser.geteditBliotecario(V_principal.bibliotecario_ingresado,V_principal.bibliotecario_ingresado.getId());
            Call<Bibliotecario> call = bibser.addBibliotecario(V_principal.bibliotecario_ingresado);
            call.enqueue(new Callback<Bibliotecario>() {
                @Override
                public void onResponse(Call<Bibliotecario> call, Response<Bibliotecario> response) {
                    if (!response.isSuccessful()) {
                        Log.e("Error: ", response.message());
                        return;
                    } else {
                        bibliotecario_ingresado.setValue(response.body());
                        V_principal.bibliotecario_ingresado=bibliotecario_ingresado.getValue();
                        System.out.println("AQUIIIIIIIIIIIII"+V_principal.bibliotecario_ingresado.getPersona().getUsuario());
                    }
                }

                @Override
                public void onFailure(Call<Bibliotecario> call, Throwable t) {
                    Log.e("response", "fail");
                }
            });
        }
    }
}
