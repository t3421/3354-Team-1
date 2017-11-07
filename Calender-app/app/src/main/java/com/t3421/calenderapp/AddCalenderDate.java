

package com.t3421.calenderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddCalenderDate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_calender_date);

        Button cancelAddDate = (Button) findViewById(R.id.cancel_add_date);
        cancelAddDate.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent = new Intent(AddCalenderDate.this, AddEvent.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });



    }
}
