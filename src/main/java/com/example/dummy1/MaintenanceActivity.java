package com.example.dummy1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MaintenanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance);
    }
    public void onClickbuttonS(View view)
    {

        Intent i = new Intent("com.example.dummy1.ServiceActivity");
        startActivity(i);
    }
    public void onClickbuttonR(View view)
    {

        Intent i = new Intent("com.example.dummy1.RepairActivity");
        startActivity(i);
    }
    public void onClickbuttonF(View view)
    {

        Intent i = new Intent("com.example.dummy1.FuelActivity");
        startActivity(i);
    }
}