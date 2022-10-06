package com.example.istateca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Bundle;

import com.example.istateca.Clases.Bibliotecario;
import com.example.istateca.Clases.Usuario;
import com.example.istateca.databinding.ActivityMainBinding;



public class MainActivity extends AppCompatActivity {

    public static ViewModelMain viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel=new ViewModelProvider(this).get(ViewModelMain.class);
        //persona
        viewModel.getPersona().observe(this,persona -> {
            if (viewModel.getPersona()!=null){
                V_principal.revalidar_ingreso();
                viewModel.iniciousuario();
            }
        });
        //usuario
        viewModel.getUsuario_Ingrtesado().observe(this,usuario -> {
            if (viewModel.getUsuario_Ingrtesado()!=null){
                V_principal.revalidar_ingreso();
                System.out.println(viewModel.getUsuario_Ingrtesado().getValue().getPersona().getNombres());
                openActivity();
            }
        });
        //bibliotecario
        viewModel.getBibliotecario_Ingrtesado().observe(this,bibliotecario -> {
            if (viewModel.getBibliotecario_Ingrtesado()!=null){
                System.out.println(viewModel.getBibliotecario_Ingrtesado().getValue().getId()+"");
                openActivity();
            }
        });


        binding.button.setOnClickListener(v -> {
            viewModel.validarpersona(binding.textNombre3.getText().toString(),binding.textClave.getText().toString());
        });
        binding.txtUsuario.setOnClickListener(v -> {
            openRegistro();
        });
    }

    private void openActivity(){
        Intent intent = new Intent(this,V_principal.class);
        Usuario u=viewModel.getUsuario_Ingrtesado().getValue();
        startActivity(intent);
    }
    private void openRegistro(){
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }

}