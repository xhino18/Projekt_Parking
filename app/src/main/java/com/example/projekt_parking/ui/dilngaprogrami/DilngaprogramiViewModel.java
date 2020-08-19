package com.example.projekt_parking.ui.dilngaprogrami;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DilngaprogramiViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DilngaprogramiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}