package com.example.dummy1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DBconnection extends SQLiteOpenHelper {


    public DBconnection(Context context) {
        super(context,"driveApp.db", null, 6);

    }


    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table vehicleDetails(Vehicle_type TEXT , Vehicle_number TEXT primary key,Register_date TEXT,Engine_number TEXT,Model TEXT,Fuel_type TEXT,Year_manufacture TEXT, Engine_capacity TEXT)");
        DB.execSQL("create table serviceDetails(vnumber TEXT primary key, lastdate TEXT, description TEXT, itemreplaced TEXT, cost TEXT)");
        DB.execSQL("create table fuelDetails(fueldate TEXT primary key, fueltype TEXT, reason TEXT, liters TEXT,totalcost TEXT)");
        DB.execSQL("create table repairDetails(vehi_number TEXT primary key, repairtype TEXT, description TEXT, item_replaced TEXT,repaircost TEXT,repair_date TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISts vehicleDetails" );
        DB.execSQL("DROP TABLE IF EXISts serviceDetails" );
        DB.execSQL("DROP TABLE IF EXISts fuelDetails" );
        DB.execSQL("DROP TABLE IF EXISts repairDetails" );
        onCreate(DB);
    }

    public Boolean insertRepairData(String vehinumber,String repairtype, String description,String itemreplace,String repaircost,String rdate)
    {
        SQLiteDatabase DB =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("vehi_number",vehinumber);
        contentValues.put("repairtype",repairtype);
        contentValues.put("description", description);
        contentValues.put("item_replaced",itemreplace);
        contentValues.put("repaircost",repaircost);
        contentValues.put("repair_date",rdate);

        long results =DB.insert("repairDetails",null,contentValues);
        if(results== -1)
        {
            return  false;
        }
        else
        {
            return  true;
        }
    }
    public Cursor getRepairData()
    {
        SQLiteDatabase DB =this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from repairDetails", null);
        return cursor;
    }
    public Boolean insertFuelData(String fueldate,String fueltype, String reason,String liters,String totalcost)
    {
        SQLiteDatabase DB =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fueldate",fueldate);
        contentValues.put("fueltype",fueltype);
        contentValues.put("reason", reason);
        contentValues.put("liters",liters);
        contentValues.put("totalcost",totalcost);

        long results =DB.insert("fuelDetails",null,contentValues);
        if(results== -1)
        {
            return  false;
        }
        else
        {
            return  true;
        }
    }
    public Cursor getFuelData()
    {
        SQLiteDatabase DB =this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from fuelDetails", null);
        return cursor;
    }

    public Boolean insertVehicleData(String Vehicle_type,String Vehicle_number, String Register_date,String Engine_number,String Model,String Fuel_type,String Year_manufacture,String Engine_capacity)
    {
        SQLiteDatabase DB =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Vehicle_type",Vehicle_type);
        contentValues.put("Vehicle_number",Vehicle_number);
        contentValues.put("Register_date", Register_date);
        contentValues.put("Engine_number",Engine_number);
        contentValues.put("Model",Model);
        contentValues.put("Fuel_type",Fuel_type);
        contentValues.put("Year_manufacture",Year_manufacture);
        contentValues.put("Engine_capacity",Engine_capacity);
        long results =DB.insert("vehicleDetails",null,contentValues);
        if(results== -1)
        {
            return  false;
        }
        else
        {
            return  true;
        }
    }

    public Boolean updateVehicleData(String Vehicle_type,String Vehicle_number, String Register_date,String Engine_number,String Model,String Fuel_type,String Year_manufacture,String Engine_capacity)
    {
        SQLiteDatabase DB =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Vehicle_type",Vehicle_type);
        contentValues.put("Register_date", Register_date);
        contentValues.put("Engine_number",Engine_number);
        contentValues.put("Model",Model);
        contentValues.put("Fuel_type",Fuel_type);
        contentValues.put("Year_manufacture",Year_manufacture);
        contentValues.put("Engine_capacity",Engine_capacity);

        Cursor cursor = DB.rawQuery("select * from vehicleDetails where Vehicle_number= ?", new String[]{Vehicle_number});

        if(cursor.getCount() >0)
        {
            long results = DB.update("vehicleDetails", contentValues, "Vehicle_number=?", new String[]{Vehicle_number});
            if (results == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {
            return  false;
        }
    }

    public Boolean deleteVehicleData(String Vehicle_number)
    {
        SQLiteDatabase DB =this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from vehicleDetails where Vehicle_number= ?", new String[]{Vehicle_number});

        if(cursor.getCount() >0)
        {
            long results = DB.delete("vehicleDetails", "Vehicle_number=?", new String[]{Vehicle_number});
            if (results == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {
            return  false;
        }
    }

    public Cursor getVehicleData()
    {
        SQLiteDatabase DB =this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from vehicleDetails", null);
        return cursor;
    }

    public Boolean insertServiceData(String vehicle_number,String last_date, String description,String item_replaced,String cost)
    {
        SQLiteDatabase DB =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("vnumber",vehicle_number);
        contentValues.put("lastdate",last_date);
        contentValues.put("description",description);
        contentValues.put("itemreplaced",item_replaced);
        contentValues.put("cost",cost);

        long results =DB.insert("serviceDetails",null,contentValues);
        if(results== -1)
        {
            return  false;
        }
        else
        {
            return  true;
        }
    }
    public Boolean updateServiceData(String vehicle_number,String last_date, String description,String item_replaced,String cost)
    {
        SQLiteDatabase DB =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("lastdate",last_date);
        contentValues.put("description",description);
        contentValues.put("itemreplaced",item_replaced);
        contentValues.put("cost",cost);

        Cursor cursor = DB.rawQuery("select * from serviceDetails where vnumber= ?", new String[]{vehicle_number});

        if(cursor.getCount() >0)
        {
            long results = DB.update("serviceDetails", contentValues, "vnumber=?", new String[]{vehicle_number});
            if (results == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {
            return  false;
        }
    }


    public Boolean deleteServiceData(String vehicle_number)
    {
        SQLiteDatabase DB =this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from serviceDetails where vnumber= ?", new String[]{vehicle_number});

        if(cursor.getCount() >0)
        {
            long results = DB.delete("serviceDetails", "vnumber=?", new String[]{vehicle_number});
            if (results == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {
            return  false;
        }
    }


    public Cursor getServiceData()
    {
        SQLiteDatabase DB =this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from serviceDetails", null);
        return cursor;
    }



}
