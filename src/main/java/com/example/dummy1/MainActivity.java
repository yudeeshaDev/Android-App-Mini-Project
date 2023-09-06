package com.example.dummy1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        */

    }
    public void onClickbutton1(View view)
    {

        Intent i = new Intent("com.example.dummy1.VehicledetailsActivity");
        startActivity(i);
    }
    public void onClickbutton2(View view)
    {

        Intent i2 = new Intent("com.example.dummy1.MaintenanceActivity");
        startActivity(i2);
    }
    public void onClickbutton3(View view)
    {

        Intent i2 = new Intent("com.example.dummy1.GuideBookActivity");
        startActivity(i2);
    }
}