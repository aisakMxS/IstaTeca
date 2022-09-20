package com.example.istateca.ui.solicitud_libro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.istateca.databinding.FragmentSolicitudLBinding;

public class HomeFragment extends Fragment {

    private FragmentSolicitudLBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentSolicitudLBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.TxtCedulaEstudanteSolicitud;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}