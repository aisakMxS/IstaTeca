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
import android.widget.Toast;

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

public class RegistroActivity extends AppCompatActivity {

    private UsuarioService usuarioService;
    /*private static final String url="http://192.168.18.14:4200/api/";*/
    /*String Pced = txtcedula.getText().toString().trim();*/
    private static final String url="http://192.168.18.14:4200/api/fenix_alumno?ced";
    ActivityRegistroBinding binding;

    private static EditText txtcedula;
    private static EditText txtnombres;
    private static EditText txtusuario;
    private static EditText txtcelular;
    private static EditText txtcorreo;
    private static EditText txtclave;
    private static Button btnregister, btnSearch;
    Context context;
/*  StringRequest stringRequest;
    RequestQueue requestQueue;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        usuarioService = Apis.getUsuarioService();
        /*requestQueue = Volley.newRequestQueue(this);*/

        btnSearch=findViewById(R.id.btnBuscar);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUsu();
            }
        });

/*        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//*usuarioService = Apis.getUsuarioService();*//*
         *//* openMain();*//*
                Toast.makeText(context, "access", Toast.LENGTH_SHORT).show();
            }
        });*/


    }

/*    public void eventcedula(){

        txtcedula.setOnKeyListener(new View.OnKeyListener(){

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(txtcedula.getText().length()==10) getUsu();
                 return false;
            }
        });
    }*/


/*    private  void openMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }*/

    /*public void getCed(String c){
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

    }*/

/*    @Override
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
    }*/


/*    public void VieDate( View view){
        String Pced = txtcedula.getText().toString().trim();
        String url="http://192.168.18.14:4200/api/fenix_alumno?ced="+Pced;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new com.android.volley.Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String txtnom, txtcel, txtcor;
                        try {
                            txtnom = response.getString("nombres");
                            txtcel = response.getString("celular");
                            txtcor = response.getString("correo");

                            txtnombres.setText(txtnom);
                            txtcelular.setText(txtcel);
                            txtcorreo.setText(txtcor);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtnombres.setText("");
                        txtcelular.setText("");
                        txtcorreo.setText("");
                        Toast.makeText(RegistroActivity.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }*/

    public void getUsu(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(url).addConverterFactory(GsonConverterFactory
                        .create())
                .build();
        usuarioService= retrofit.create(UsuarioService.class);
        Call<Persona> call= usuarioService.getUsuario(txtcedula.getText().toString());
        call.enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(Call<Persona> call, Response<Persona> response) {
                if (!response.isSuccessful()){
                    Log.e("Error: ",response.message());
                    return;
                }
                String txtnom, txtcel, txtcor;
                try {
                    txtnom = response.body().getNombres();
                    txtcel = response.body().getCedular();
                    txtcor = response.body().getCorreo();

                    txtnombres.setText(txtnom);
                    txtcelular.setText(txtcel);
                    txtcorreo.setText(txtcor);

                } catch (Exception e) {
                    e.printStackTrace();
                }
/*                String cedu = txtcedula.getText().toString().trim();
                String nomb = txtnombres.getText().toString().trim();
                String usua = txtusuario.getText().toString().trim();
                String celu = txtcelular.getText().toString().trim();
                String corr = txtcorreo.getText().toString().trim();
                String clav = txtclave.getText().toString().trim();

                if(cedu.isEmpty()||nomb.isEmpty()||usua.isEmpty()||
                        celu.isEmpty()||corr.isEmpty()||clav.isEmpty()){
                    Toast.makeText(context, "Required", Toast.LENGTH_SHORT).show();
                    return;

                }else{
                    cedu = response.body().getCedula();
                    nomb = response.body().getNombres();
                    usua = response.body().getUsuario();
                    celu = response.body().getCedular();
                    corr = response.body().getCorreo();
                    clav = response.body().getClave();
                }*/

/*             Persona person = response.body();
                System.out.println(person);*/
            }
            @Override
            public void onFailure(Call<Persona> call, Throwable t) {
                txtnombres.setText("");
                txtcelular.setText("");
                txtcorreo.setText("");
                Log.e("response","fail");
            }
        });
    }


}