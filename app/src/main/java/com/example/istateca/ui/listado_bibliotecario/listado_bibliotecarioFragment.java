package com.example.istateca.ui.listado_bibliotecario;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.istateca.R;

public class listado_bibliotecarioFragment extends Fragment {

    private ListadoBibliotecarioViewModel mViewModel;

    public static listado_bibliotecarioFragment newInstance() {
        return new listado_bibliotecarioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listado_bibliotecario, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListadoBibliotecarioViewModel.class);
        // TODO: Use the ViewModel
    }

}