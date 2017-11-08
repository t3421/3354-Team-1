package com.t3421.calenderapp;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddEvent extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    int startHour = 0 , modifiedStartHour = 0 , startMinute = 0 , endHour = 0 , modifiedEndHour = 0 , endMinute = 0 ;
    String ampm = "AM";

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            int transH = startHour;
            int transM = startMinute;
            startHour = data.getIntExtra("selectedHour", 0);
            startMinute = data.getIntExtra("selectedMinute", 0);
            boolean isValid = validate(startHour , startMinute , endHour , endMinute);
            if (isValid){
                Toast.makeText(getBaseContext(),"hour = " + startHour + " minute = " + startMinute , Toast.LENGTH_LONG).show();
                modifiedStartHour = startHour;
                if ( startHour > 12 ){
                    modifiedStartHour = startHour - 12;
                    ampm = "PM";
                }
                else if (startHour == 0){startHour=+1;}
                String modifiedStartMinute =  String.format("%02d", startMinute);
                ((TextView)findViewById(R.id.start_time_display)).setText(modifiedStartHour + ":" + modifiedStartMinute + " " + ampm);
            }
            else{
                startHour = transH;
                startMinute = transM;
                Toast.makeText(getBaseContext(),"The start time needs to be prior to end time" , Toast.LENGTH_LONG).show();
            }
        }


        if (requestCode == 2 && resultCode == RESULT_OK) {
            int transH = startHour;
            int transM = startMinute;
            endHour = data.getIntExtra("selectedHour", 0);
            endMinute = data.getIntExtra("selectedMinute", 0);
            boolean isValid = validate(startHour , startMinute , endHour , endMinute);
            if (isValid){
                Toast.makeText(getBaseContext(),"hour = " + endHour + " minute = " + endMinute , Toast.LENGTH_LONG).show();
                modifiedEndHour = endHour;
                if ( endHour > 12 ){
                   modifiedEndHour = endHour - 12;
                  ampm = "PM";
                }
                else if (endHour == 0){endHour=+1;}
                String modifiedEndMinute =  String.format("%02d", endMinute);
                ((TextView)findViewById(R.id.end_time_display)).setText(endHour + ":" +  modifiedEndMinute + " " + ampm);
            }
            else{
                startHour = transH;
                startMinute = transM;
                Toast.makeText(getBaseContext(),"The start time need to be prior to end time" , Toast.LENGTH_LONG).show();
            }
        }

        if (requestCode == 3 && resultCode == RESULT_OK) {
                String dateString = data.getStringExtra("selectedDate");
                Toast.makeText(getBaseContext(),dateString , Toast.LENGTH_LONG).show();
                ((TextView)findViewById(R.id.date_display)).setText(dateString);
        }
    }
    
boolean validate(int startH , int startM , int endH , int endM) {
    if (startH == 0 && startM == 0 || endH == 0 && endM == 0){return true;}
    if (startH <= endH){if (startM < endM){return true;}}
    return false;
}


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        spinner = (Spinner) findViewById(R.id.spinnerColor);
        adapter = ArrayAdapter.createFromResource(this , R.array.colors_array , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
              //  Toast.makeText(getBaseContext(),parent.getItemAtPosition(position) + " selected", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button cancelEvent = (Button) findViewById(R.id.cancel);
        cancelEvent.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent = new Intent(AddEvent.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        Button addStartTime = (Button) findViewById(R.id.start_time);
        addStartTime.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent = new Intent(AddEvent.this, AddTime.class);
                startActivityForResult(intent,1);
            }
        });

        Button addEndTime = (Button) findViewById(R.id.end_time);
        addEndTime.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent = new Intent(AddEvent.this, AddTime.class);
                startActivityForResult(intent,2);
            }
        });

        Button addDate = (Button) findViewById(R.id.start_date);
        addDate.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent = new Intent(AddEvent.this, AddCalenderDate.class);
                startActivityForResult(intent,3);
            }
        });
    }
}
