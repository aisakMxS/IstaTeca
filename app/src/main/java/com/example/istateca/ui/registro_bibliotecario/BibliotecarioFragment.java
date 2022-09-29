package com.example.istateca.ui.registro_bibliotecario;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.istateca.Clases.Bibliotecario;
import com.example.istateca.Clases.Libro;
import com.example.istateca.Clases.Persona;
import com.example.istateca.R;
import com.example.istateca.RegistroActivity;
import com.example.istateca.Utils.Apis;
import com.example.istateca.Utils.BibliotecarioService;
import com.example.istateca.Utils.LibroService;
import com.example.istateca.databinding.FragmentBibliotecarioBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BibliotecarioFragment extends Fragment {

    private FragmentBibliotecarioBinding binding;
    BibliotecarioService bibliotecarioService;
    List<Bibliotecario> lista_bibliotecarios = new ArrayList<>();
    /*String chekk="";*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBibliotecarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

       binding.btnGuardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int bib=0, p=0, ro=0;
                Boolean act2;
                //Date d1,d2;/*
                String cedu=binding.txtNombre.getText().toString();
                String usua=binding.txtUsuario.getText().toString();
                String celu=binding.txtCelu.getText().toString();
                String emai=binding.txtEmail.getText().toString();
                String contr=binding.txtContra.getText().toString();
                String roll=(String)binding.comboRol.getSelectedItem();
                System.out.println(roll);

                String d1=binding.btnFecha.getText().toString();
                String d2=binding.btnFecha.getText().toString();

                if(cedu.isEmpty()){
                    Toast.makeText(getContext(),"Ingrese cedula",Toast.LENGTH_SHORT).show();
                }else if(usua.isEmpty()){
                    Toast.makeText(getContext(),"Ingrese usuario",Toast.LENGTH_SHORT).show();
                }else if(celu.isEmpty()){
                    Toast.makeText(getContext(),"Ingrese celular",Toast.LENGTH_SHORT).show();
                }else if(!valEmail(emai)){
                    Toast.makeText(getContext(),"Correo incorrecto",Toast.LENGTH_SHORT).show();
                }else if(emai.equals("")){
                    Toast.makeText(getContext(),"Ingrese correo",Toast.LENGTH_SHORT).show();
                }else if(contr.isEmpty()){
                    Toast.makeText(getContext(),"Ingrese clave",Toast.LENGTH_SHORT).show();
                }else if(roll.isEmpty()){
                    Toast.makeText(getContext(),"Seleccione rol",Toast.LENGTH_SHORT).show();
                }

                int rol2=9;
                if(roll.equalsIgnoreCase("Administrador")){
                    rol2=0;
                }else if(roll.equalsIgnoreCase("Bibliotecario")){
                    rol2=1;
                }
                System.out.println("Rol: "+rol2);

                if(binding.activo.isChecked()==true){
                    act2=true;
                }else{
                    act2=false;
                }
                String nombre="";

                Persona person = new Persona(p,cedu,usua,contr,nombre,rol2,emai,celu,act2);
                Bibliotecario bibliot= new Bibliotecario(bib,person,d1,d2,act2);

                create(bibliot);
                /*roles();*/
            /*clean();*/


            }
        });

        binding.btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());
                binding.btnFecha.setText(d);
            }
        });

        binding.radioGroupTipoLugar.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                RadioButton activoo = binding.activo;
                RadioButton inactivoo = binding.inactivo;
                    if (activoo.isChecked() == true) {
                        activoo.setVisibility(View.VISIBLE);
                        //System.out.println(ACTIVO);
                    } else if (inactivoo.isChecked() == true) {
                        inactivoo.setVisibility(View.VISIBLE);
                    } else if (activoo.isChecked() == false) {
                        activoo.setVisibility(View.GONE);
                    } else if (inactivoo.isChecked() == false) {
                        inactivoo.setVisibility(View.GONE);
                    }

                return false;
            }
        });

        binding.btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Btn listar");
            }
        });


        return root;
    }

   private void create(Bibliotecario biblio){
        Retrofit retrofit= new Retrofit.Builder().
                baseUrl(Apis.URL_001).
                addConverterFactory(GsonConverterFactory.create()).build();
        bibliotecarioService= retrofit.create(BibliotecarioService.class);
        Call<Bibliotecario> call= bibliotecarioService.addBibliotecario(biblio);
        call.enqueue(new Callback<Bibliotecario>() {
            @Override
            public void onResponse(Call<Bibliotecario> call, Response<Bibliotecario> response) {
                if (!response.isSuccessful()){
                    Log.e("Error: ",response.message());
                    return;
                }
                Bibliotecario bibliotecario = response.body();
                System.out.println(bibliotecario.getPersona().getNombres());
            }
            @Override
            public void onFailure(Call<Bibliotecario> call, Throwable t) {
                Log.e("response","fail");
            }
        });
    }

    private void listarbibliotecario(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Apis.URL_001)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        bibliotecarioService=retrofit.create(BibliotecarioService.class);
        Call<List<Bibliotecario>> call= bibliotecarioService.getBibliotecario();
        call.enqueue(new Callback<List<Bibliotecario>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Bibliotecario>> call, Response<List<Bibliotecario>> response) {
                if (!response.isSuccessful()){
                    Log.e("Response err: ",response.message());
                    return;
                }
                lista_bibliotecarios=response.body();
                System.out.println(lista_bibliotecarios.size() + " biliotecarios");
            }
            @Override
            public void onFailure(Call<List<Bibliotecario>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    public void clean(){
        binding.txtNombre.setText("");
        binding.txtUsuario.setText("");
        binding.txtContra.setText("");
        binding.txtEmail.setText("");
        binding.txtCelu.setText("");
    }

/*    public void OnOff(){
        Toast.makeText(getContext(),"Bienvenido",Toast.LENGTH_SHORT).show();
        if (activoo.isChecked() == true) {
            chekk = " Administrador";
        } else {
            chekk = "Usuario";
        }
    }*/

    private boolean valEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}