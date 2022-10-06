
package com.example.istateca.ui.edituser;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.istateca.Clases.Persona;
import com.example.istateca.MainActivity;
import com.example.istateca.R;
import com.example.istateca.V_principal;
import com.example.istateca.ViewModelMain;
import com.example.istateca.databinding.FragmentEdituserBinding;

public class edituser extends Fragment {

    private FragmentEdituserBinding binding;
    private EdituserViewModel mViewModel;
    private Persona p;

    public static edituser newInstance() {
        return new edituser();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentEdituserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        selec_persona();
        binding.txtCedula.setEnabled(false);
        binding.txtNombres.setEnabled(false);

        binding.btnRegistrar.setOnClickListener(v -> {
            if (V_principal.bibliotecario_ingresado==null){
                V_principal.usuario_ingresado.getPersona().setUsuario(binding.txtUsuario.getText().toString());
                V_principal.usuario_ingresado.getPersona().setCelular(binding.txtCelular.getText().toString());
                V_principal.usuario_ingresado.getPersona().setCorreo(binding.txtCorreo.getText().toString());
                V_principal.usuario_ingresado.getPersona().setClave(binding.txtClave.getText().toString());
            }else{
                V_principal.bibliotecario_ingresado.getPersona().setUsuario(binding.txtUsuario.getText().toString());
                V_principal.bibliotecario_ingresado.getPersona().setCelular(binding.txtCelular.getText().toString());
                V_principal.bibliotecario_ingresado.getPersona().setCorreo(binding.txtCorreo.getText().toString());
                V_principal.bibliotecario_ingresado.getPersona().setClave(binding.txtClave.getText().toString());
            }
            MainActivity.viewModel.modificar(V_principal.bibliotecario_ingresado==null);
            getActivity().onBackPressed();
            Toast.makeText(getActivity(), "Perfil Modificado", Toast.LENGTH_SHORT).show();
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EdituserViewModel.class);
    }

    public void selec_persona(){
        if(V_principal.bibliotecario_ingresado==null){
            //binding.setPersona(V_principal.usuario_ingresado.getPersona());
            binding.txtCedula.setText(V_principal.usuario_ingresado.getPersona().getCedula());
            binding.txtNombres.setText(V_principal.usuario_ingresado.getPersona().getNombres());
            binding.txtUsuario.setText(V_principal.usuario_ingresado.getPersona().getUsuario());
            binding.txtCelular.setText(V_principal.usuario_ingresado.getPersona().getCelular());
            binding.txtCorreo.setText(V_principal.usuario_ingresado.getPersona().getCorreo());
            binding.txtClave.setText(V_principal.usuario_ingresado.getPersona().getClave());
        }
        else{
            //binding.setPersona(V_principal.bibliotecario_ingresado.getPersona());
            binding.txtCedula.setText(V_principal.bibliotecario_ingresado.getPersona().getCedula());
            binding.txtNombres.setText(V_principal.bibliotecario_ingresado.getPersona().getNombres());
            binding.txtUsuario.setText(V_principal.bibliotecario_ingresado.getPersona().getUsuario());
            binding.txtCelular.setText(V_principal.bibliotecario_ingresado.getPersona().getCelular());
            binding.txtCorreo.setText(V_principal.bibliotecario_ingresado.getPersona().getCorreo());
            binding.txtClave.setText(V_principal.bibliotecario_ingresado.getPersona().getClave());
        }
    }

}