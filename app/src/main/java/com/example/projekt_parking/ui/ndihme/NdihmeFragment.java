package com.example.projekt_parking.ui.ndihme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.projekt_parking.R;

public class NdihmeFragment extends Fragment {

    private NdihmeViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(NdihmeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ndihme, container, false);

        return root;
    }
}
