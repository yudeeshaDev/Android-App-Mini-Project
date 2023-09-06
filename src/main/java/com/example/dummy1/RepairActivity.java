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

public class RepairActivity extends AppCompatActivity {

    EditText vhnumber,repairtype,description,itemreplaced,repaircost,repairdate;
    Button btnsaverepair,btnviewrepair;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair);

        vhnumber= findViewById(R.id.vehiclenumberr);
        repairtype=findViewById(R.id.repairtype);
        description=findViewById(R.id.rdescription);
        itemreplaced=findViewById(R.id.ritemreplce);
        repaircost=findViewById(R.id.rcost);
        repairdate=findViewById(R.id.repairdate);

        btnsaverepair=findViewById(R.id.saverepair);
        btnviewrepair=findViewById(R.id.viewrepair);

        DBconnection db1 =new DBconnection(this);

        repairdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR); //current year
                int month = calendar.get(Calendar.MONTH); //current month
                int day = calendar.get(Calendar.DAY_OF_MONTH); //current day

                DatePickerDialog datePickerDialog = new DatePickerDialog(RepairActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                repairdate.setText(day + "-" + month + "-" + year);
                            }
                        },year, month, day);
                datePickerDialog.show();
            }
        });

        btnsaverepair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vhnumbertxt= vhnumber.getText().toString();
                String rtype= repairtype.getText().toString();
                String des= description.getText().toString();
                String itemreplc=itemreplaced.getText().toString();
                String rcosts = repaircost.getText().toString();
                String rdate = repairdate.getText().toString();

                Boolean checkInsert =db1.insertRepairData(vhnumbertxt,rtype,des,itemreplc,rcosts,rdate);

                if (checkInsert==true)
                {
                    Toast.makeText(RepairActivity.this,"Added new repair details",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(RepairActivity.this,"New repair details not added",Toast.LENGTH_SHORT).show();

                }

            }
        });
        btnviewrepair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor results =db1.getRepairData();
                if (results.getCount()==0)

                {
                    Toast.makeText(RepairActivity.this,"No Fuel details",Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer= new StringBuffer();

                while (results.moveToNext())
                {
                    buffer.append("vehi_number: "+results.getString(0)+"\n");
                    buffer.append("repairtype: "+results.getString(1)+"\n");
                    buffer.append("description: "+results.getString(2)+"\n");
                    buffer.append("item_replaced: "+results.getString(3)+"\n");
                    buffer.append("repaircost: "+results.getString(4)+"\n");
                    buffer.append("repair_date: "+results.getString(5)+"\n");


                }

                AlertDialog.Builder builder=new AlertDialog.Builder(RepairActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Repair details");
                builder.setMessage(buffer.toString());
                builder.show();



            }
        });
    }
}