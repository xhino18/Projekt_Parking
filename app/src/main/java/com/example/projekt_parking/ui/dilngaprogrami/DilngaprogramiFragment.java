package com.example.projekt_parking.ui.dilngaprogrami;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.example.projekt_parking.MainActivity;
import com.example.projekt_parking.R;

public class DilngaprogramiFragment extends Fragment {

    private DilngaprogramiViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(DilngaprogramiViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dilngaprogrami, container, false);


            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            builder.setMessage("Jeni i sigurt qe doni te  dilni?")
                    .setCancelable(false)
                    .setPositiveButton("Po", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getActivity().finish();


                        }
                    })
                    .setNegativeButton("Jo", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        return root;

    }



}
