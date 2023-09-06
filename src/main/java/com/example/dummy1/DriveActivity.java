package com.example.dummy1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DriveActivity extends AppCompatActivity {

    ListView lstView;
    TextView textView;
    String[] tips = {
            "Press down fully on the clutch",
            "Move the gear shift into first gear",
            "Slowly lift your foot off the clutch",
            "Begin pressing down on the accelerator",
            "Beware of stalling",
            "Move into second gear"
    };
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start_vehicle);

        //AlertDialog actual = null;
        //= actual.getListView();

        lstView=findViewById(R.id.list);
        textView=(TextView)findViewById(R.id.textView);

        ArrayAdapter<String> setListAdapter;
        setListAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, tips);
        lstView.setAdapter(setListAdapter);
        lstView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lstView.setTextFilterEnabled(true);


    }
    public void onListItemClick(
            ListView parent, View v, int position, long id)
    {
        Toast.makeText(this,
                "You have selected " + tips[position],
                Toast.LENGTH_SHORT).show();
    }
    public void onClick(View view) {


        String itemsSelected = "Selected items: \n";
        for (int i=0; i<lstView.getCount(); i++) {
            if (lstView.isItemChecked(i)) {
                itemsSelected += lstView.getItemAtPosition(i) + "\n";
            }
        }
        Toast.makeText(this, itemsSelected, Toast.LENGTH_LONG).show();
    }
}