package com.t3421.calenderapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button button;
int startYear = 0, startMonth = 0, startDay = 0, startHour = 0, startMinute = 0, endHour = 0, endMinute = 0;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {

            String eventTitle = data.getStringExtra("eventTitle");
            startYear = data.getIntExtra("startYear", 0);
            startMonth = data.getIntExtra("startMonth" , 0);
            startDay = data.getIntExtra("startDay", 0);
            startHour = data.getIntExtra("startHour", 0);
            startMinute = data.getIntExtra("startMinute", 0);
            endHour = data.getIntExtra("endHour", 0);
            endMinute = data.getIntExtra("endMinute", 0);
            String occurrance = data.getStringExtra("occurrence");
            String extraComments = data.getStringExtra("extraComments");
            String colorSelected = data.getStringExtra("colorSelected");

            Toast.makeText(getBaseContext(),eventTitle + " Starts on " + startDay + "/" + startMonth + "/" + startYear + " at " + startHour + ":" + startMinute + " ending " + endHour + ":" + endMinute + " with comments '" + extraComments + "'" + " using color " + colorSelected + " occuring, " + occurrance, Toast.LENGTH_LONG).show();
        }
        else if (requestCode ==1 && resultCode == RESULT_CANCELED){
            Toast.makeText(getBaseContext(), "Add event was canceled by user", Toast.LENGTH_LONG).show();
        }
    }

            @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_main);

        // Locate the button in activity_main.xml
        Button eventAdd = (Button) findViewById(R.id.add_event);
        eventAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddEvent.class);
                startActivityForResult(intent, 1);
            }
        });

        Spinner viewsSpinner = (Spinner) findViewById(R.id.view_spinner);
        ArrayAdapter<CharSequence> views_adapter = ArrayAdapter.createFromResource(this, R.array.views_array, android.R.layout.simple_spinner_item);
        views_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        viewsSpinner.setAdapter(views_adapter);
        viewsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){

                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position)+" selected", Toast.LENGTH_LONG).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }

        });


    }}
