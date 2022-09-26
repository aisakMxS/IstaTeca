package com.example.istateca.ui.registro_bibliotecario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BibliotecarioViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    public BibliotecarioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }
    public LiveData<String> getText() {
        return mText;
    }
}