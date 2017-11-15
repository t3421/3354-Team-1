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
                timePicker = (TimePicker)findViewById(R.id.time_picker);
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                Intent intent = new Intent();
                intent.putExtra("selectedMinute" , minute );
                intent.putExtra("selectedHour" , hour );
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
