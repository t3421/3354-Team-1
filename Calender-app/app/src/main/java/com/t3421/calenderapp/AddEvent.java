package com.t3421.calenderapp;

import android.content.Intent;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            String timeStart = data.getStringExtra("selectedTime");
            Toast.makeText(getBaseContext(),timeStart , Toast.LENGTH_LONG).show();
            ((TextView)findViewById(R.id.start_time_display)).setText(timeStart);
        }
        if (requestCode == 2 && resultCode == RESULT_OK) {
            String timeEnd = data.getStringExtra("selectedTime");
            Toast.makeText(getBaseContext(),timeEnd , Toast.LENGTH_LONG).show();
            ((TextView)findViewById(R.id.end_time_display)).setText(timeEnd);
        }
        if (requestCode == 3 && resultCode == RESULT_OK) {
                String dateString = data.getStringExtra("selectedDate");
                Toast.makeText(getBaseContext(),dateString , Toast.LENGTH_LONG).show();
                ((TextView)findViewById(R.id.date_display)).setText(dateString);
        }

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
