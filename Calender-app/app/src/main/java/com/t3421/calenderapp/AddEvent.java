package com.t3421.calenderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup;
import android.widget.RadioButton;

/**
 * Adds an event from the add event button or modifies an event from the edit button
 *
 * @author Theodore Sosnowski
 */
public class AddEvent extends AppCompatActivity {

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    RadioGroup occurrenceRG ;
    RadioButton btn;

    int startHour = 0 , startMinute = 0 , endHour = 0 , endMinute = 0 ;
    int startYear = 0 , startMonth = 0 , startDay = 0 ;

    /**
     * @param requestCode   gets return code from AddCalenderDate and AddTime
     * @param resultCode    gets info if OK or Cancel button was hit from AddCalenderDate or AddTime
     * @param data          imports data from Intent and adds to objects
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            int transH = startHour;
            int transM = startMinute;
            startHour = data.getIntExtra("selectedHour", 0);
            startMinute = data.getIntExtra("selectedMinute", 0);
            if (validate(startHour , startMinute , endHour , endMinute)){
                ((TextView)findViewById(R.id.start_time_display)).setText(getTimeString(startHour , startMinute));
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
                ((TextView)findViewById(R.id.end_time_display)).setText(getTimeString(endHour , endMinute));
            }
            else{
                startHour = transH;
                startMinute = transM;
                Toast.makeText(getBaseContext(),"The start time need to be prior to end time" , Toast.LENGTH_LONG).show();
            }
        }

        if (requestCode == 3 && resultCode == RESULT_OK) {
            startYear   = data.getIntExtra("selectedYear", 0);
            startMonth  = data.getIntExtra("selectedMonth" , 0);
            startDay    = data.getIntExtra("selectedDay", 0);
            String dateString = getDateString(startYear , startMonth, startDay);
            ((TextView)findViewById(R.id.date_display)).setText(dateString);
        }
    }

    /**
     * Changes data to a string displayed for date
     *
     * @param year  year as int to be converted
     * @param month month as a int from 0 to 11 to be converted
     * @param day   day as a int from 1 to 31 to be converted
     * @return      a string in the form of DDMonthYYYY , as the month is spelled out
     */
    public String getDateString(int year, int month, int day){
    String monthString;
    switch (month){
        case 0:  monthString = "0";
            break;
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
    if (year == 0 || day == 0){return "";}
    return  (day + (" ")+ monthString + (" ") + year);
}

    /**
     * Checks start time is before end time of event
     *
     * @param startH    start hour to check
     * @param startM    start minute to check
     * @param endH      end hour to check
     * @param endM      end minute to check
     * @return          true if start time is before end time, false if start time is after end time
     */
    public boolean validate(int startH , int startM , int endH , int endM) {
    return (((startH == 0 && startM == 0) || (endH == 0 && endM == 0))||((startH < endH)||((startH == endH) && (startM < endM ))));
}

    /**
     * Changes data to a string displayed for time
     *
     * @param hour      hour as int to be converted
     * @param minute    minute as int to be converted
     * @return          string in the form of HH:MM AM/PM
     */
    public String getTimeString(int hour, int minute){
    String ampm = "AM";
    if(hour == 0 && minute == 0){return "";}
    if (hour >= 12){
        if (hour>12){  hour = hour - 12;}
        ampm = "PM";
    }
    if (hour == 0){hour = 12;}
        return (hour + ":" + String.format("%02d" ,minute) + " " + ampm);
    }

    /**
     * Gets the spinner position from the color string
     *
     * @param color given color of selected event
     * @return      position of the color on the spinner
     */
    public int getSpinnerPosition(String color){
        if (color.length()==0){return 0;}
        switch(color){
            case "":return 0;
            case "Yellow":return 0;
            case "Fuchsia":return 1;
            case "Red":return 2;
            case "Green":return 3;
            case "Purple":return 4;
            case "Blue": return 5;
            case "Maroon": return 6;
            case "Teal":return 7;
            default : return 0;
        }
    }

    /**
     * Checks for error within the user input such as long comment or title along with missing needed items to add in the event
     *
     * @param startMinute the start minute must be assigned
     * @param startHour the start hour must be assigned
     * @param endMinute the end minute must be assigned
     * @param endHour   the end hour must be assigned
     * @param startDay  checking the day is not 0
     * @param eventTitle must be checked for for >0 and <64 characters
     * @param eventComments must be checkd for <256 characters
     * @return returns true if no errors are found, false is at least one is found
     */
    public boolean Noerrors(int startMinute , int startHour, int endMinute, int endHour, int startDay, String eventTitle, String eventComments){

        if(startMinute == 0 && startHour == 0 || endMinute == 0 && endHour == 0 || startDay == 0 || eventTitle.length()==0 ){

            StringBuilder errors =new StringBuilder();
            if ( eventComments.length()>256){errors.append("comment length,");}
            if ( eventTitle.length() == 0 || eventTitle.length()>32){errors.append("event title length, ");}
            if ( startMinute == 0 && startHour == 0){errors.append("start time, ");}
            if ( endMinute == 0 && endHour == 0){errors.append("end time, ");}
            if ( startDay == 0){errors.append("start Date, ");}
            Toast.makeText(getBaseContext()," Please correct the error/s " + errors, Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    /**
     * Sets occurrence from string to the radio button OccurrenceDB
     *
     * @param occurrence string of the type of occurrence can only be single, weekly, or monthly
     */
    public void setOccurrenceId(String occurrence) {
        if (occurrence.equals("Weekly")) {
            RadioButton weeklyRadio =  (RadioButton)findViewById(R.id.radioWeekly);
            weeklyRadio.setChecked(true);
        }
        else if (occurrence.equals("Monthly")) {
            RadioButton monthlyRadio =  (RadioButton)findViewById(R.id.radioMonthly);
            monthlyRadio.setChecked(true);
        }
        else{
            RadioButton singleRadio =  (RadioButton)findViewById(R.id.radioSingle);
            singleRadio.setChecked(true);
        }
    }

    /**
     * creates GUI from activity_add_event including all buttons and pulling needed information
     * from edited event via intent.
     *
     * @param savedInstanceState    saved state
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        Intent intent = getIntent();
        int startY = intent.getIntExtra("startYear", 0);
        int startM = intent.getIntExtra("startMonth" , 0);
        int startD = intent.getIntExtra("startDay", 0);
        int startH = intent.getIntExtra("startHour", 0);
        int startMi = intent.getIntExtra("startMinute", 0);
        int endH = intent.getIntExtra("endHour", 0);
        int endM = intent.getIntExtra("endMinute", 0);
        String occurrenceId = intent.getStringExtra("occurrence");
        String eventId = intent.getStringExtra("eventTitle");
        String extraId = intent.getStringExtra("extraComments");
        ((TextView)findViewById(R.id.date_display)).setText(getDateString(startY, startM , startD));
        ((TextView)findViewById(R.id.start_time_display)).setText(getTimeString(startH , startMi));
        ((TextView)findViewById(R.id.end_time_display)).setText(getTimeString(endH , endM));
        ((EditText)findViewById(R.id.event_Name)).setText(eventId);
        ((EditText)findViewById(R.id.comments_entered)).setText(extraId);

        if(!intent.getBooleanExtra("default", true)){

            startHour = startH;
            startMinute = startMi;
            endHour = endH;
            endMinute = endM;
            startYear = startY;
            startMonth = startM;
            startDay = startD;
        }

        setOccurrenceId(occurrenceId);

        spinner = (Spinner) findViewById(R.id.spinnerColor);
        adapter = ArrayAdapter.createFromResource(this , R.array.colors_array , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        ((Spinner) findViewById(R.id.spinnerColor)).setSelection(getSpinnerPosition(intent.getStringExtra("colorSelected")));

        Button cancelEvent = (Button) findViewById(R.id.event_view_back);
        Button okAddEvent = (Button) findViewById(R.id.ok_add_event);
        Button addStartTime = (Button) findViewById(R.id.start_time);
        Button addEndTime = (Button) findViewById(R.id.end_time);
        Button addDate = (Button) findViewById(R.id.start_date);


        cancelEvent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        okAddEvent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                EditText et1 = (EditText)findViewById (R.id.event_Name);
                String eventTitle = et1.getText().toString ();
                EditText et2 = (EditText)findViewById (R.id.comments_entered);
                String eventComments = et2.getText().toString ();


                if(Noerrors(startMinute, startHour, endMinute, endHour, startDay, eventTitle , eventComments)){

                    String colorSelected =  spinner.getSelectedItem().toString();
                    occurrenceRG = (RadioGroup)findViewById(R.id.radioOccur);
                    int id= occurrenceRG.getCheckedRadioButtonId();
                    View radioButton = occurrenceRG.findViewById(id);
                    int radioId = occurrenceRG.indexOfChild(radioButton);
                    btn = (RadioButton) occurrenceRG.getChildAt(radioId);
                    String selection = (String)btn.getText();

                /*
                lets put the check for overlap here, with if else statement. something like pull the items one by one and check if
                the date is the same and then check that event for the time conflict. Once we find an overlap we can just throw an
                error for it and make a toast message to fix values or just reset the hole page back to default.
                 */

                    Intent okButton = new Intent();
                    okButton.putExtra("startMinute" , startMinute );
                    okButton.putExtra("startHour" , startHour );
                    okButton.putExtra("endHour" , endHour );
                    okButton.putExtra("endMinute" , endMinute );
                    okButton.putExtra("eventTitle" , eventTitle );
                    okButton.putExtra("extraComments" , eventComments);
                    okButton.putExtra("occurrence" , selection );
                    okButton.putExtra("startDay" , startDay );
                    okButton.putExtra("startMonth" , startMonth );
                    okButton.putExtra("startYear" , startYear );
                    okButton.putExtra("colorSelected" , colorSelected );
                    setResult(RESULT_OK, okButton);
                    finish();
                }
            }
        });

        addStartTime.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent = new Intent(AddEvent.this, AddTime.class);
                startActivityForResult(intent,1);
            }
        });

        addEndTime.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent = new Intent(AddEvent.this, AddTime.class);
                startActivityForResult(intent,2);
            }
        });

        addDate.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent intent = new Intent(AddEvent.this, AddCalenderDate.class);
                startActivityForResult(intent,3);
            }
        });
    }
}
