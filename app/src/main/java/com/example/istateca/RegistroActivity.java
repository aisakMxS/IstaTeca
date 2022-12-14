package com.example.istateca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.istateca.Clases.Persona;
import com.example.istateca.Clases.Usuario;
import com.example.istateca.Utils.Apis;
import com.example.istateca.Utils.UsuarioService;
import com.example.istateca.databinding.ActivityRegistroBinding;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroActivity extends AppCompatActivity {

    UsuarioService usuarioService;
    private ActivityRegistroBinding binding;
    int us=0;
    int p=0;
    int cali = 5;
    String obs="";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

/*        btnSearch=findViewById(R.id.btnBuscar);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUsu();
            }
        });*/

        binding.btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*usuarioService = Apis.getUsuarioService();*/
                String cedu=binding.txtCedula.getText().toString();
                String nomb=binding.txtNombres.getText().toString();
                String usua=binding.txtUsuario.getText().toString();
                String clav=binding.txtClave.getText().toString();
                String corr=binding.txtCorreo.getText().toString();
                String celu=binding.txtCelular.getText().toString();
                Boolean act=true;

                if(cedu.isEmpty()){
                    Toast.makeText(RegistroActivity.this,"Ingrese cedula",Toast.LENGTH_SHORT).show();
                }else if(nomb.isEmpty()){
                    Toast.makeText(RegistroActivity.this,"Ingrese nombre",Toast.LENGTH_SHORT).show();
                }else if(usua.isEmpty()){
                    Toast.makeText(RegistroActivity.this,"Ingrese usuario",Toast.LENGTH_SHORT).show();
                }else if(celu.isEmpty()){
                    Toast.makeText(RegistroActivity.this,"Ingrese celular",Toast.LENGTH_SHORT).show();
                }else if(clav.isEmpty()){
                    Toast.makeText(RegistroActivity.this,"Ingrese clave",Toast.LENGTH_SHORT).show();
                }else if(!valEmail(corr)){
                    Toast.makeText(RegistroActivity.this,"Correo incorrecto",Toast.LENGTH_SHORT).show();
                }else if(corr.equals("")){
                    Toast.makeText(RegistroActivity.this,"Ingrese correo",Toast.LENGTH_SHORT).show();
                }
                int rol=2;

                Persona person = new Persona(p,cedu,usua,clav,nomb,rol,corr,celu,act);
                Usuario u = new Usuario(us,cali,obs,person);

                /*Toast.makeText(context, "access", Toast.LENGTH_SHORT).show();*/
                create(u);
                clean();

            }
        });
        /*return;*/
    }


    private void create(Usuario usu){
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

    public void clean(){
        binding.txtCedula.setText("");
        binding.txtNombres.setText("");
        binding.txtUsuario.setText("");
        binding.txtClave.setText("");
        binding.txtCorreo.setText("");
        binding.txtCelular.setText("");
    }

    private boolean valEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }


}