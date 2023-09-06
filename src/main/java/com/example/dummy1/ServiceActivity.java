package com.example.dummy1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class ServiceActivity extends AppCompatActivity {


    EditText vnumber,lastdate,description,itemreplaced,cost;
    Button btnsaveservice,btnupdateservice,btnviewservice,btndeleteservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        vnumber= findViewById(R.id.txtvehiclenumber);
        lastdate=findViewById(R.id.servicedate);
        description=findViewById(R.id.sdescription);
        itemreplaced=findViewById(R.id.sitemsreplaced);
        cost=findViewById(R.id.scost);

        btnsaveservice=findViewById(R.id.btnsaveservice);
        btnupdateservice=findViewById(R.id.btnupdateservice);
        btnviewservice=findViewById(R.id.btnviewservice);
        btndeleteservice=findViewById(R.id.btndeleteservice);

        DBconnection db =new DBconnection(this);
        lastdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR); //current year
                int month = calendar.get(Calendar.MONTH); //current month
                int day = calendar.get(Calendar.DAY_OF_MONTH); //current day

                DatePickerDialog datePickerDialog = new DatePickerDialog(ServiceActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                lastdate.setText(day + "-" + month + "-" + year);
                            }
                        },year, month, day);
                datePickerDialog.show();
            }
        });

        btnsaveservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vnumbertxt= vnumber.getText().toString();
                String ldate= lastdate.getText().toString();
                String descrip= description.getText().toString();
                String itemreplce=itemreplaced.getText().toString();
                String costs = cost.getText().toString();

                Boolean checkInsert =db.insertServiceData(vnumbertxt,ldate,descrip,itemreplce,costs);

                if (checkInsert==true)
                {
                    Toast.makeText(ServiceActivity.this,"Added new service details",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ServiceActivity.this,"New service details not added",Toast.LENGTH_SHORT).show();

                }

            }
        });
       btnupdateservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String vnumbertxt= vnumber.getText().toString();
                String ldate= lastdate.getText().toString();
                String descrip= description.getText().toString();
                String itemreplce=itemreplaced.getText().toString();
                String costs = cost.getText().toString();

                Boolean checkUpdateData =db.updateServiceData(vnumbertxt,ldate,descrip,itemreplce,costs);


                if (checkUpdateData==true)
                {
                    Toast.makeText(ServiceActivity.this,"Updated Service details",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ServiceActivity.this,"New service details not update",Toast.LENGTH_SHORT).show();

                }
            }
        });



        btndeleteservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String vnumberTXt = vnumber.getText().toString();

                Boolean checkDeleteData =db.deleteServiceData(vnumberTXt);

                if (checkDeleteData==true)
                {
                    Toast.makeText(ServiceActivity.this,"Deleted Service details",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(ServiceActivity.this,"New service details not delete",Toast.LENGTH_SHORT).show();

                }
            }
        });



        btnviewservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor results =db.getServiceData();
                if (results.getCount()==0)

                {
                    Toast.makeText(ServiceActivity.this,"No service details",Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer= new StringBuffer();

                while (results.moveToNext())
                {
                    buffer.append("vnumber: "+results.getString(0)+"\n");
                    buffer.append("lastdate: "+results.getString(1)+"\n");
                    buffer.append("description: "+results.getString(2)+"\n");
                    buffer.append("itemreplaced: "+results.getString(3)+"\n");
                    buffer.append("cost: "+results.getString(4)+"\n\n");

                }

                AlertDialog.Builder builder=new AlertDialog.Builder(ServiceActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Service details");
                builder.setMessage(buffer.toString());
                builder.show();



            }
        });
    }
}