

package com.t3421.calenderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

/**
 * select a date from a calender view
 *
 * @author Theodore Sosnowski
 */

public class AddCalenderDate extends AppCompatActivity {
    private DatePicker datePicker;

    /**
     * Creates a date picker and pushes objects back to AddEvent via startActivityForResults intent
     * it also sets GUI from activity_add_calender_date along with needed buttons
     * @param savedInstanceState    Saved instances
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_calender_date);
        Button cancelAddDate = (Button) findViewById(R.id.cancel_add_date);
        cancelAddDate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                finish();
            }
        });

        Button addDate = (Button) findViewById(R.id.ok_add_date);
        addDate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                datePicker = (DatePicker)findViewById(R.id.datePicker);

                Intent intent = new Intent();
                intent.putExtra("selectedYear" , datePicker.getYear() );
                intent.putExtra("selectedMonth" , datePicker.getMonth() + 1);
                intent.putExtra("selectedDay" , datePicker.getDayOfMonth() );
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
