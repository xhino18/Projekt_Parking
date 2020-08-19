package com.example.projekt_parking.ui.parko;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.projekt_parking.DatabaseParko;
import com.example.projekt_parking.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class ParkoFragment extends Fragment {
    private Location location;
    private TextView locationTv;
    private GoogleApiClient googleApiClient;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private LocationRequest locationRequest;
    private static final long UPDATE_INTERVAL = 5000, FASTEST_INTERVAL = 5000; // = 5 seconds
    // lists for permissions
    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();
    // integer for permissions results request
    private static final int ALL_PERMISSIONS_RESULT = 1011;


    String str_lat,str_lon;
    TextView lat,lon;
    DatabaseParko DB_Parko;
    Calendar calendar;
    int PERMISSION_ID = 44;
    FusedLocationProviderClient mFusedLocationClient;

    private ParkoViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //View viewA= inflater.inflate(R.layout.fragment_parko,container,false);
        //View viewB= inflater.inflate(R.layout.fragment_parko,container,false);
        homeViewModel =
                ViewModelProviders.of(this).get(ParkoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_parko, container, false);
        lat=root.findViewById(R.id.lat);
        lon=root.findViewById(R.id.lon);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());


        IntentFilter filter = new IntentFilter("STRING_ID_FOR_BRODCAST");
        getActivity().registerReceiver(receiverUpdateDownload, filter);




        DB_Parko=new DatabaseParko(getActivity());
        calendar=Calendar.getInstance();
        String getcurrentDate= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        final TextView text_date =root.findViewById(R.id.text_date);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("EEEE,dd-MMM-yyyy hh:mm a");
        String dateTime=simpleDateFormat.format(calendar.getTime());
        text_date.setText(dateTime);
        final int  year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        final int hour=calendar.get(Calendar.HOUR);


        final TextView textTarga=root.findViewById(R.id.textTarga);
        final Button buttonTotal= root.findViewById(R.id.buttonTotal);
        final Spinner spinnerZona = root.findViewById(R.id.spinnerZona);
        final Spinner spinnerOra = root.findViewById(R.id.spinnerOra);
        final TextView totaliFinal = root.findViewById(R.id.totaliFinal);
        final Button buttonView=root.findViewById(R.id.buttonView);
        final Button parko=root.findViewById(R.id.parko);


        parko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String targa=textTarga.getText().toString().trim();
                String zona= spinnerZona.getSelectedItem().toString();
                String ora=spinnerOra.getSelectedItem().toString();
                String data=text_date.getText().toString().trim();
                String latitude=lat.getText().toString().trim();
                String longitude=lon.getText().toString().trim();


                if (zona.startsWith("A")){
                    DB_Parko.bookParkingSpot(targa,zona,ora,data,latitude,longitude);
                    Toast.makeText(getActivity(),"Parkimi ne zonen A u rezervua!",Toast.LENGTH_LONG).show();
                }else if (zona.startsWith("B")){
                    DB_Parko.bookParkingSpot(targa,zona,ora,data,latitude,longitude);
                    Toast.makeText(getActivity(),"Parkimi ne Zonen B u rezervua!",Toast.LENGTH_LONG).show();
                }else {
                    DB_Parko.bookParkingSpot(targa,zona,ora,data,latitude,longitude);
                    Toast.makeText(getActivity(),"Parkimi ne Zonen C u rezervua!",Toast.LENGTH_LONG).show();
                }

            }

        });

        buttonTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String zona= spinnerZona.getSelectedItem().toString();
                String ora=spinnerOra.getSelectedItem().toString();

                if (zona.startsWith("A")) {
                    BigInteger num1 = new BigInteger("100");
                    BigInteger num2 = new BigInteger(ora);
                    BigInteger totali = num2.multiply(num1);
                    totaliFinal.setText(totali + " Leke");
                } else if (zona.startsWith("B")) {
                    BigInteger num1 = new BigInteger("50");
                    BigInteger num2 = new BigInteger(ora);
                    BigInteger totali = num2.multiply(num1);
                    totaliFinal.setText(totali + " Leke");
                } else {
                    BigInteger num1 = new BigInteger("20");
                    BigInteger num2 = new BigInteger(ora);
                    BigInteger totali = num2.multiply(num1);
                    totaliFinal.setText(totali + " Leke");
                }

            }
        });
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result= DB_Parko.viewAll();
                if (result.getCount()==0){
                    showMessage("Error","No data found");
                    return;
                }
                StringBuffer buffer1= new StringBuffer();
                while(result.moveToNext()){
                    buffer1.append("ID: "+result.getString(0)+"\n");
                    buffer1.append("Targa: "+result.getString(1)+"\n");
                    buffer1.append("Zona: "+result.getString(2)+"\n");
                    buffer1.append("Ore parkim: "+result.getString(3)+"\n");
                    buffer1.append("Data : "+result.getString(4)+"\n");
                    buffer1.append("Koordinatat Gjeresi : "+result.getString(5)+"\n");
                    buffer1.append("Koordinatat Gjatesi : "+result.getString(6)+"\n\n");


                }
                showMessage("Parkimet",buffer1.toString());
            }
        });



        return root;
    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this.getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
    BroadcastReceiver receiverUpdateDownload = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle b = intent.getExtras();

            str_lat =  b.getString("lat1");
            str_lon =  b.getString("lon1");

        }
    };

    @Override
    public void onStop() {
        super.onStop();
        if (receiverUpdateDownload != null) {
            try {
                getActivity().unregisterReceiver(receiverUpdateDownload);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

