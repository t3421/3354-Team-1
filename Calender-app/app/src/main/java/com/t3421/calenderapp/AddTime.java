package com.t3421.calenderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddTime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_time);

        Button cancelAddTime = (Button) findViewById(R.id.cancel_time);
        cancelAddTime.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent = new Intent(AddTime.this, AddEvent.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}
