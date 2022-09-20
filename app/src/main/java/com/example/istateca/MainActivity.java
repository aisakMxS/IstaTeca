package com.example.istateca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.istateca.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(v -> {
            openActivity();
        });
        binding.txtUsuario.setOnClickListener(v -> {
            openRegistro();
        });
    }

    private  void openActivity(){
        Intent intent = new Intent(this,V_principal.class);
        startActivity(intent);
    }
    private void openRegistro(){
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }
}