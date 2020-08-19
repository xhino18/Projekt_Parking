package com.example.projekt_parking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class registerActivity extends AppCompatActivity {
    EditText textUsername, textPassword, textCnfPassword;
    Button buttonRegister;
    TextView textLogin;
    DatabaseLogin LoginDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        LoginDB = new DatabaseLogin(this);

        textUsername = (EditText) findViewById(R.id.textUsername);
        textPassword = (EditText) findViewById(R.id.textPassword);
        textCnfPassword = (EditText) findViewById(R.id.textCnfPassword);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        textLogin = (TextView) findViewById(R.id.textLogin);


        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(registerActivity.this, MainActivity.class);
                startActivity(login);

            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Username= textUsername.getText().toString().trim();
                String Password= textPassword.getText().toString().trim();
                String CnfPassword= textCnfPassword.getText().toString().trim();

                if (Password.equals(CnfPassword)) {
                    LoginDB.register(Username,Password);
                    Toast.makeText(registerActivity.this, "Registered successfully", Toast.LENGTH_LONG).show();
                    Intent goToLogin = new Intent(registerActivity.this, MainActivity.class);
                    startActivity(goToLogin);
                }else{
                    Toast.makeText(registerActivity.this, " Password is not matching", Toast.LENGTH_LONG).show();
                    ;
                }
                }
        });
    }
}