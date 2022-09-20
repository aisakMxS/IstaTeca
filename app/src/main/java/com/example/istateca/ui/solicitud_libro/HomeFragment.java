package com.example.istateca.ui.solicitud_libro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.istateca.R;
import com.example.istateca.databinding.FragmentSolicitudLBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeFragment extends Fragment {

    private FragmentSolicitudLBinding binding;
    private Spinner Cb_documentoHabilitante_solicitud;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSolicitudLBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.BtnFechaEntregaSolicitud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date());
                binding.BtnFechaEntregaSolicitud.setText(d);
            }
        });

        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}