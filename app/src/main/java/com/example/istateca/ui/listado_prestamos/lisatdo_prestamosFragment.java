package com.example.istateca.ui.listado_prestamos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.istateca.R;

public class lisatdo_prestamosFragment extends Fragment {

    private LisatdoPrestamosViewModel mViewModel;

    public static lisatdo_prestamosFragment newInstance() {
        return new lisatdo_prestamosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lisatdo_prestamos, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LisatdoPrestamosViewModel.class);
        // TODO: Use the ViewModel
    }

}