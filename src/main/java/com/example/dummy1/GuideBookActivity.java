package com.example.dummy1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GuideBookActivity extends AppCompatActivity {

    private Spinner spinerTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_book);

        spinerTip=findViewById(R.id.spinnerTips);

        List<String> tip = new ArrayList<>();
        tip.add(0, "Select");
        tip.add("How to Start The Vehicle");
        tip.add("How to begin to Drive Vehicle on the Road");
        tip.add("Following Traffic Rules in Vehicles");
        tip.add("How to Stop the Vehicle");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, tip);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinerTip.setAdapter(dataAdapter);
        spinerTip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Select"))
                {

                }
                else
                {
                    String item = adapterView.getItemAtPosition(i).toString();
                    Toast.makeText(adapterView.getContext(), "Selected: " +item, Toast.LENGTH_SHORT).show();

                    if (adapterView.getItemAtPosition(i).equals("How to Start The Vehicle"))
                    {
                        Intent intent = new Intent(GuideBookActivity.this, StartVehicleActivity.class);
                        startActivity(intent);
                    }
                    else if (adapterView.getItemAtPosition(i).equals("How to begin to Drive Vehicle on the Road"))
                    {
                        Intent intent = new Intent(GuideBookActivity.this, DriveActivity.class);
                        startActivity(intent);
                    }

                    else if (adapterView.getItemAtPosition(i).equals("Following Traffic Rules in Vehicles"))
                    {
                        Intent intent = new Intent(GuideBookActivity.this, TraficLightsActivity.class);
                        startActivity(intent);
                    }
                    else if (adapterView.getItemAtPosition(i).equals("How to Stop the Vehicle"))
                    {
                        Intent intent = new Intent(GuideBookActivity.this, StopeVehicleActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}