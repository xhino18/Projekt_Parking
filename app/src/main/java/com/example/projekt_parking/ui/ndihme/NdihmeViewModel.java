package com.example.projekt_parking.ui.ndihme;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NdihmeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NdihmeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}