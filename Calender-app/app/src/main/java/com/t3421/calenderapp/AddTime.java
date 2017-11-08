package com.t3421.calenderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class AddTime extends AppCompatActivity {
private TimePicker timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_time);

        Button cancelAddTime = (Button) findViewById(R.id.cancel_time);
        cancelAddTime.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        Button okAddTime = (Button) findViewById(R.id.ok_time);
        okAddTime.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                int hour =0 , minute = 0;
                String amPm = "AM";
                timePicker = (TimePicker)findViewById(R.id.time_picker);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    hour = timePicker.getHour();
                    minute = timePicker.getMinute();

                }

                StringBuilder sb=new StringBuilder();
                sb.append(hour).append(":").append(minute).append(" ").append(amPm);
                String selectedTime=sb.toString();

                Intent intent = new Intent();
                intent.putExtra("selectedMinute" , minute );
                intent.putExtra("selectedHour" , hour );
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
