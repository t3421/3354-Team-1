package com.t3421.calenderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * view an event, from the edit event button modifies this event and the back button returns to previous view
 *
 * @author Theodore Sosnowski
 */
public class EventView extends AppCompatActivity {
    int startY = 0, startM = 0, startD = 0, startH = 0, startMi = 0, endH = 0, endM = 0;
    String occurrenceId, eventId, extraId, color;

    /**
     * gets data from intent for results on the edit events button
     *
     * @param requestCode checks code for the correct number 1 or 2
     * @param resultCode checks results for RESULTS_OK or CANCEL_RESULTS
     * @param data all items passed via intent
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if(requestCode == 2 && requestCode == RESULT_OK)
        //Toast.makeText(getBaseContext(),startMinute + endMinute +startHour + endHour + startDay + startYear, Toast.LENGTH_LONG).show();

        if (requestCode == 2 && resultCode == RESULT_OK) {

            eventId = data.getStringExtra("eventTitle");
            startY = data.getIntExtra("startYear", 0);
            startM = data.getIntExtra("startMonth", 0);
            startD = data.getIntExtra("startDay", 0);
            startH = data.getIntExtra("startHour", 0);
            startM = data.getIntExtra("startMinute", 0);
            endH = data.getIntExtra("endHour", 0);
            endM = data.getIntExtra("endMinute", 0);
            occurrenceId = data.getStringExtra("occurrence");
            extraId = data.getStringExtra("extraComments");
            color = data.getStringExtra("colorSelected");

            setData(startY, startM, startD, startH, startMi, endH, endM, occurrenceId, eventId, extraId, color);
        }
    }

    /**
     * cretaes GUI
     *
     * @param savedInstanceState pass instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);


        Intent intent = getIntent();
        startY = intent.getIntExtra("startYear", 0);
        startM = intent.getIntExtra("startMonth" , 0);
        startD = intent.getIntExtra("startDay", 0);
        startH = intent.getIntExtra("startHour", 0);
        startMi = intent.getIntExtra("startMinute", 0);
        endH = intent.getIntExtra("endHour", 0);
        endM = intent.getIntExtra("endMinute", 0);
        occurrenceId = intent.getStringExtra("occurrence");
        eventId = intent.getStringExtra("eventTitle");
        extraId = intent.getStringExtra("extraComments");
        color = intent.getStringExtra("colorSelected");

        setData(startY, startM, startD, startH, startMi, endH, endM, occurrenceId, eventId, extraId, color);

        Button eventEdit = (Button) findViewById(R.id.event_view_edit);
        eventEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EventView.this, AddEvent.class);
                intent.putExtra("startMinute" , startMi );
                intent.putExtra("startHour" , startH );
                intent.putExtra("endHour" , endH );
                intent.putExtra("endMinute" , endM );
                intent.putExtra("eventTitle" , eventId );
                intent.putExtra("extraComments" , extraId);
                intent.putExtra("occurrence" , occurrenceId );
                intent.putExtra("startDay" , startD );
                intent.putExtra("startMonth" , startM );
                intent.putExtra("startYear" , startY );
                intent.putExtra("colorSelected" , color );
                intent.putExtra("default" , false);
                startActivityForResult(intent, 2);
            }
        });

        Button eventViewBack = (Button) findViewById(R.id.event_view_back);
        eventViewBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    /**
     * Sets all data to be displayed, called from on create and on activity results
     *
     * @param startY
     * @param startM
     * @param startD
     * @param startH
     * @param startMi
     * @param endH
     * @param endM
     * @param occurrenceId
     * @param eventId
     * @param extraId
     * @param color
     */
    public void setData(int startY, int startM, int startD, int startH, int startMi, int endH, int endM, String occurrenceId, String eventId, String extraId, String color){

        String occurrence;
        AddEvent eventData = new AddEvent();
        StringBuilder data = new StringBuilder();
        if (occurrenceId.equals("Single")){occurrence = "occurring once";}
        else {occurrence = "occurring " + occurrenceId;}
        ((TextView) findViewById(R.id.event_view_data)).setText(occurrence);

        data.append("\n" + "Starts on " + eventData.getDateString(startY, startM , startD));
        data.append("\n" + " at " + eventData.getTimeString(startH , startMi) + " until " +  eventData.getTimeString(endH , endM));
        data.append("\n" + occurrence + " denoted with the color " + color);
        String eventStartDate = "Event start date is " + eventData.getDateString(startY, startM , startD);
        String eventStartTime = "Starting at " + eventData.getTimeString(startH , startMi);
        String eventEndTime = "Ending at " + eventData.getTimeString(endH , endM);
        String colorSelected = "Colored " + color;

        ((TextView)findViewById(R.id.event_view_data)).setText(data);
        ((TextView)findViewById(R.id.event_view_name)).setText(eventId);
        ((TextView)findViewById(R.id.event_view_comments)).setText(extraId);
        ((TextView)findViewById(R.id.event_view_comments)).setMovementMethod(new ScrollingMovementMethod());

    }
}
