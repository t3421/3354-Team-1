

package com.t3421.calenderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class AddCalenderDate extends AppCompatActivity {
    private DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_calender_date);
        Button cancelAddDate = (Button) findViewById(R.id.cancel_add_date);
        cancelAddDate.setOnClickListener(new View.OnClickListener(){public void onClick(View view){finish();}});
        Button addDate = (Button) findViewById(R.id.ok_add_date);
        addDate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                datePicker = (DatePicker)findViewById(R.id.datePicker);
                Integer year = datePicker.getYear();
                Integer month = datePicker.getMonth();
                Integer day = datePicker.getDayOfMonth();
                String monthString;
                switch (month+1){
                    case 1:  monthString = "January";
                        break;
                    case 2:  monthString = "February";
                        break;
                    case 3:  monthString = "March";
                        break;
                    case 4:  monthString = "April";
                        break;
                    case 5:  monthString = "May";
                        break;
                    case 6:  monthString = "June";
                        break;
                    case 7:  monthString = "July";
                        break;
                    case 8:  monthString = "August";
                        break;
                    case 9:  monthString = "September";
                        break;
                    case 10: monthString = "October";
                        break;
                    case 11: monthString = "November";
                        break;
                    case 12: monthString = "December";
                        break;
                    default: monthString = "Invalid month";
                        break;
                }


                StringBuilder sb=new StringBuilder();
                sb.append(day.toString()).append(" ").append(monthString).append(" ").append(year.toString());
                String dateString=sb.toString();

                Intent intent = new Intent();
                intent.putExtra("selectedDate" , dateString );
                setResult(RESULT_OK, intent);
                finish();

            }
        });


    }
}
