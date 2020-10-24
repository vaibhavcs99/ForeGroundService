package com.example.foregroundservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    Button startServ;
    Button stopServ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startServ = findViewById(R.id.start_service);

        stopServ = findViewById(R.id.stop_service);

        startServ.setOnClickListener(this);

        stopServ.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        if (v == startServ) {
            startService(new Intent(this, OGService.class));

        }
        if (v == stopServ) {
            stopService(new Intent(this, OGService.class));
        }
    }
}