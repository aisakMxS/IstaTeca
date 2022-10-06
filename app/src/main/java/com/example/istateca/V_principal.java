package com.example.istateca;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.example.istateca.Clases.Bibliotecario;
import com.example.istateca.Clases.Usuario;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.istateca.databinding.ActivityVprincipalBinding;

public class V_principal extends AppCompatActivity {

    public static  Usuario usuario_ingresado=new Usuario();
    public static  Bibliotecario bibliotecario_ingresado=new Bibliotecario();

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityVprincipalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVprincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();
        usuario_ingresado=MainActivity.viewModel.getUsuario_Ingrtesado().getValue();
        bibliotecario_ingresado=MainActivity.viewModel.getBibliotecario_Ingrtesado().getValue();

        //System.out.println(usuario_ingresado.getPersona().getNombres());

        setSupportActionBar(binding.appBarVprincipal.toolbar);


        binding.appBarVprincipal.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        if (usuario_ingresado== null){
            if (bibliotecario_ingresado.getPersona().getRol()==0){
                //administrador acceso
            }else{
                //
            }
        }
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_lista_l, R.id.nav_solicitud_l, R.id.nav_devolucion_l,R.id.nav_registro_b,R.id.nav_listado_p,R.id.nav_listado_b,R.id.nav_listado_l,
                R.id.nav_listas,R.id.nav_registro_l,R.id.nav_solicitud_l_pen,R.id.nav_editar_usuario)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_vprincipal);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.v_principal, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_vprincipal);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}