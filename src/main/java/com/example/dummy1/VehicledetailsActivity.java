package com.example.dummy1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class VehicledetailsActivity extends AppCompatActivity {

    Spinner v_type;
    EditText vnumber,date,Engine_number,Model,Fuel_type,Year_manufacture, Engine_capacity;
    Button btnsave, btndelete,btnupdate, btnview;
    ArrayAdapter<CharSequence> adapter;
    String[] types = new String[]{"Car","Van","Motor Bike","Truck","Bus"};

    DBconnection DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicledetails);

        vnumber =findViewById(R.id.Vehicle_number);
        date =findViewById(R.id.Register_date);
        Engine_number =findViewById(R.id.Engine_number);
        Model=findViewById(R.id.Model);
        Fuel_type=findViewById(R.id.Fuel_type);
        Year_manufacture=findViewById(R.id.Year_manufacture);
        Engine_capacity=findViewById(R.id.Engine_capacity);

        btnsave=findViewById(R.id.btnsave);
        btnupdate=findViewById(R.id.btnupdate);
        btndelete=findViewById(R.id.btndelete);
        btnview=findViewById(R.id.btnview);


        v_type = (Spinner) findViewById(R.id.Vehicle_type);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.vehicle_type, android.R.layout.simple_spinner_item);
        adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        v_type.setAdapter(adapter);
        v_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0,
                                       View arg1, int arg2, long arg3)
            {
                int index = arg0.getSelectedItemPosition();
                Toast.makeText(getBaseContext(),
                        "You have selected item : " + types[index],
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) { }
        });

        //click event on edit text
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR); //current year
                int month = calendar.get(Calendar.MONTH); //current month
                int day = calendar.get(Calendar.DAY_OF_MONTH); //current day

                DatePickerDialog datePickerDialog = new DatePickerDialog(VehicledetailsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                date.setText(day + "-" + month + "-" + year);
                            }
                        },year, month, day);
                datePickerDialog.show();
            }
        });

        DB = new DBconnection(this);

       btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String type= v_type.getSelectedItem().toString();
                String vnumberTXt = vnumber.getText().toString();
                String dob = (date.getText().toString());
                String enumber = (Engine_number.getText().toString());
                String model = (Model.getText().toString());
                String ftype = (Fuel_type.getText().toString());
                String ymnu = (Year_manufacture.getText().toString());
                String capacity = (Engine_capacity.getText().toString());

                Boolean checkInsert =DB.insertVehicleData(type,vnumberTXt, dob,enumber,model,ftype,ymnu,capacity);

                if (checkInsert==true)
                {
                    Toast.makeText(VehicledetailsActivity.this,"Added new vehicle details",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(VehicledetailsActivity.this,"New vehicle details not added",Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String type= v_type.getSelectedItem().toString();
                String vnumberTXt = vnumber.getText().toString();
                String dob = (date.getText().toString());
                String enumber = (Engine_number.getText().toString());
                String model = (Model.getText().toString());
                String ftype = (Fuel_type.getText().toString());
                String ymnu = (Year_manufacture.getText().toString());
                String capacity = (Engine_capacity.getText().toString());

                Boolean checkUpdateData =DB.updateVehicleData(type,vnumberTXt, dob,enumber,model,ftype,ymnu,capacity);

                if (checkUpdateData==true)
                {
                    Toast.makeText(VehicledetailsActivity.this,"Updated vehicle details",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(VehicledetailsActivity.this,"New vehicle details not update",Toast.LENGTH_SHORT).show();

                }
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String vnumberTXt = vnumber.getText().toString();

                Boolean checkDeleteData =DB.deleteVehicleData(vnumberTXt);

                if (checkDeleteData==true)
                {
                    Toast.makeText(VehicledetailsActivity.this,"Deleted vehicle details",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(VehicledetailsActivity.this,"New vehicle details not delete",Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor results =DB.getVehicleData();
                if (results.getCount()==0)

                {
                    Toast.makeText(VehicledetailsActivity.this,"No vehicle details",Toast.LENGTH_SHORT).show();
                    return;
                }

                    StringBuffer buffer= new StringBuffer();

                while (results.moveToNext())
                {
                    buffer.append("Vehicle_type: "+results.getString(0)+"\n");
                    buffer.append("Vehicle_number: "+results.getString(1)+"\n");
                    buffer.append("Register_date: "+results.getString(2)+"\n");
                    buffer.append("Engine_number: "+results.getString(3)+"\n");
                    buffer.append("Model: "+results.getString(4)+"\n");
                    buffer.append("Fuel_type: "+results.getString(5)+"\n");
                    buffer.append("Year_manufacture: "+results.getString(6)+"\n");
                    buffer.append("Engine_capacity: "+results.getString(7)+"\n\n");

                }

                AlertDialog.Builder builder=new AlertDialog.Builder(VehicledetailsActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Vehicle details");
                builder.setMessage(buffer.toString());
                builder.show();



            }
        });
    }
}