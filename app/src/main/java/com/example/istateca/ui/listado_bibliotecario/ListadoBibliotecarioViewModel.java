package com.example.istateca.ui.listado_bibliotecario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListadoBibliotecarioViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public ListadoBibliotecarioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }
    public LiveData<String> getText() {
        return mText;
    }
}