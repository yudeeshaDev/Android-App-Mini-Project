package com.example.dummy1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class FuelActivity extends AppCompatActivity {

    EditText fuelDate,fuel_type,reasons,liters,total_cost;
    Button btnsvfuel,btnviewfuel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel);

        fuelDate =findViewById(R.id.fueldate);
        fuel_type =findViewById(R.id.fueltype);
        reasons =findViewById(R.id.reason);
        liters=findViewById(R.id.liters);
        total_cost=findViewById(R.id.tcost);


        btnsvfuel=findViewById(R.id.btnsavefuel);
        btnviewfuel=findViewById(R.id.btnviewfuel);

        //click event on edit text
        fuelDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR); //current year
                int month = calendar.get(Calendar.MONTH); //current month
                int day = calendar.get(Calendar.DAY_OF_MONTH); //current day

                DatePickerDialog datePickerDialog = new DatePickerDialog(FuelActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                fuelDate.setText(day + "-" + month + "-" + year);
                            }
                        },year, month, day);
                datePickerDialog.show();
            }
        });

        DBconnection DB = new DBconnection(this);

        btnsvfuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String fdate = fuelDate.getText().toString();
                String ftype = fuel_type.getText().toString();
                String resontxt = reasons.getText().toString();
                String liter = liters.getText().toString();
                String tcost = (total_cost.getText().toString());


                Boolean checkInsert =DB.insertFuelData(fdate,ftype, resontxt,liter,tcost);

                if (checkInsert==true)
                {
                    Toast.makeText(FuelActivity.this,"Added new fuel details",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(FuelActivity.this,"New fuel details not added",Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnviewfuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor results =DB.getFuelData();
                if (results.getCount()==0)

                {
                    Toast.makeText(FuelActivity.this,"No Fuel details",Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer= new StringBuffer();

                while (results.moveToNext())
                {
                    buffer.append("fueldate: "+results.getString(0)+"\n");
                    buffer.append("fueltype: "+results.getString(1)+"\n");
                    buffer.append("reason: "+results.getString(2)+"\n");
                    buffer.append("liters: "+results.getString(3)+"\n");
                    buffer.append("totalcost: "+results.getString(4)+"\n");


                }

                AlertDialog.Builder builder=new AlertDialog.Builder(FuelActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Fuel details");
                builder.setMessage(buffer.toString());
                builder.show();



            }
        });
    }
}