package com.example.istateca.ui.registro_bibliotecario;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.istateca.R;
import com.example.istateca.databinding.FragmentBibliotecarioBinding;

public class BibliotecarioFragment extends Fragment {

    private FragmentBibliotecarioBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBibliotecarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}