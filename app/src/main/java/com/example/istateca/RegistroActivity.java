package com.example.istateca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.istateca.Clases.Persona;
import com.example.istateca.Utils.Apis;
import com.example.istateca.Utils.UsuarioService;
import com.example.istateca.databinding.ActivityRegistroBinding;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroActivity extends AppCompatActivity  implements View.OnTouchListener
{

    UsuarioService usuarioService;
    String url="http://10.0.2.2:8080/api/";
    ActivityRegistroBinding binding;
    List<Persona>list;

    EditText txtcedula;
    EditText txtnombres;
    EditText txtusuario;
    EditText txtcelular;
    EditText txtcorreo;
    EditText txtclave;
    Button btnregister;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        usuarioService = Apis.getUsuarioService();
        //getUsu();
        //eventcedula();
        //getCed();
        initComponents();

    }


    public void eventcedula(){

        txtcedula.setOnKeyListener(new View.OnKeyListener(){

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(txtcedula.getText().length()==10) getUsu();
                 return false;
            }
        });
    }
    public void getUsu(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(url).addConverterFactory(GsonConverterFactory
                        .create())
                .build();

        usuarioService= retrofit.create(UsuarioService.class);
        Call<Persona> call= usuarioService.getUsuarioByCedula(txtcedula.getText().toString());
        call.enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(Call<Persona> call, Response<Persona> response) {
                if (!response.isSuccessful()){
                    Log.e("Error: ",response.message());
                    return;
                }
                String tcedula = response.body().getCedula();
                String tnombres = response.body().getNombres();
                String tusuarios = response.body().getUsuario();
                String tcelular = response.body().getCedular();
                String tcorreo = response.body().getCorreo();
                String tclave = response.body().getClave();

/*              Persona person = response.body();
                System.out.println(person);*/
            }
            @Override
            public void onFailure(Call<Persona> call, Throwable t) {
                Log.e("response","fail");
            }
        });
    }

    public void getCed(String c){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(url).addConverterFactory(GsonConverterFactory
                        .create())
                .build();
        System.out.println(c);

        usuarioService= retrofit.create(UsuarioService.class);
        Call<Persona> call= usuarioService.getUsuarioByCedula(c);


        call.enqueue(new Callback<Persona>() {

            @Override
            public void onResponse(Call<Persona> call, Response<Persona> response) {
                if (!response.isSuccessful()){
                    Log.e("Error: ",response.message());
                    return;
                }
                Persona c1 = response.body();
            }

            @Override
            public void onFailure(Call<Persona> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }

    public void initComponents(){
        txtcedula = (EditText) findViewById(R.id.txt_cedula);
        txtnombres= (EditText) findViewById(R.id.txt_nombres);
        txtusuario= (EditText) findViewById(R.id.txt_usuario);
        txtcelular= (EditText) findViewById(R.id.txt_celular);
        txtcorreo= (EditText) findViewById(R.id.txt_correo);
        txtclave= (EditText) findViewById(R.id.txt_clave);

        /*btnregister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                requestRegister();
            }
        });*/


    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            txtnombres.setText("kevin Landivar");

        }
        if (event.getAction()==MotionEvent.ACTION_MOVE){
            txtnombres.setText("hola");
        }
        if (event.getAction()==MotionEvent.ACTION_UP){
            txtnombres.setText("vamos");
        }
        return false;
    }

/*    public void requestRegister(){
        usuarioService.getUsuarioByCedula(txtcedula.getText().toString())
                .enqueue(new Callback<Persona>() {
                    @Override
                    public void onResponse(Call<Persona> call, Response<Persona> response) {
                        if(response.isSuccessful()){
                            Log.i("debug","onResponse: Error");
                            return;
                            JSONObject json= new JSONObject(response.body().toString());
                            try {
                                json.put("name",txtcedula);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Persona> call, Throwable t) {

                    }
                });


    }*/




}