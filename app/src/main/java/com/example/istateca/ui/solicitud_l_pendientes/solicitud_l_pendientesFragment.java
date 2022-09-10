package com.example.istateca.ui.solicitud_l_pendientes;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.istateca.R;

public class solicitud_l_pendientesFragment extends Fragment {

    private SolicitudLPendientesViewModel mViewModel;

    public static solicitud_l_pendientesFragment newInstance() {
        return new solicitud_l_pendientesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_solicitud_l_pendientes, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SolicitudLPendientesViewModel.class);
        // TODO: Use the ViewModel
    }

}