package com.t3421.calenderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * view an event, from the edit event button modifies this event and the back button returns
 * to the previous view
 *
 * @author Theodore Sosnowski
 */
public class EventView extends AppCompatActivity {
    int startY = 0, startM = 0, startD = 0, startH = 0, startMi = 0, endH = 0, endM = 0, returnToView=0;
    String occurrenceId, eventId, extraId, color;
    private int id = 0;
    EventsDb db = new EventsDb(this);
    Event event = new Event();

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
            startMi = data.getIntExtra("startMinute", 0);
            endH = data.getIntExtra("endHour", 0);
            endM = data.getIntExtra("endMinute", 0);
            occurrenceId = data.getStringExtra("occurrence");
            extraId = data.getStringExtra("extraComments");
            color = data.getStringExtra("colorSelected");

            setData(startY, startM, startD, startH, startMi, endH, endM, occurrenceId, eventId,
                    extraId, color);
            //Checks for time conflict with updated event
            Event newValues = new Event(startMi, endM, startH, endH, startD, startY, startM, eventId, extraId, occurrenceId, color);
            newValues.setId(event.getId());

            if (db.checkForConflict(newValues))
                Toast.makeText(getBaseContext(), "Conflict", Toast.LENGTH_LONG).show();
            else
                db.updateEvent(event.getId(), startMi, endM, startH, endH, startD, startY, startM, eventId, extraId, occurrenceId, color);
        }
    }

    /**
     * creates GUI
     *
     * @param savedInstanceState pass instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);


        Intent intent = getIntent();
        startY = intent.getIntExtra("startYear", 0);
        startM = intent.getIntExtra("startMonth", 0);
        startD = intent.getIntExtra("startDay", 0);
        startH = intent.getIntExtra("startHour", 0);
        startMi = intent.getIntExtra("startMinute", 0);
        endH = intent.getIntExtra("endHour", 0);
        endM = intent.getIntExtra("endMinute", 0);
        occurrenceId = intent.getStringExtra("occurrence");
        eventId = intent.getStringExtra("eventTitle");
        extraId = intent.getStringExtra("extraComments");
        color = intent.getStringExtra("colorSelected");
        returnToView = intent.getIntExtra("viewType", 2);
        id = intent.getIntExtra("id", 0);
        event.setId(id);

        setData(startY, startM, startD, startH, startMi, endH, endM, occurrenceId, eventId,
                extraId, color);

        Button eventEdit = (Button) findViewById(R.id.event_view_edit);
        eventEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(EventView.this, AddEvent.class);
                intent.putExtra("startMinute", startMi);
                intent.putExtra("startHour", startH);
                intent.putExtra("endHour", endH);
                intent.putExtra("endMinute", endM);
                intent.putExtra("eventTitle", eventId);
                intent.putExtra("extraComments", extraId);
                intent.putExtra("occurrence", occurrenceId);
                intent.putExtra("startDay", startD);
                intent.putExtra("startMonth", startM);
                intent.putExtra("startYear", startY);
                intent.putExtra("colorSelected", color);
                intent.putExtra("default", false);
                startActivityForResult(intent, 2);
            }
        });


        Button eventBack = (Button) findViewById(R.id.event_view_delete);
        //TODO implement delete
        eventBack.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (returnToView == 2) {

                    db.deleteEvents(db.getEvent(event.getId()));
//                    MonthView epochGet = new MonthView();
//                   long epoch = epochGet.toEpoch(startD, startM, startY);
//                    String day = epochGet.getDayFromEpoch(epoch);
//                    String month = epochGet.getMonthFromEpoch(epoch);
//                    String year = epochGet.getYearFromEpoch(epoch);
//
//                    Intent intent = new Intent(EventView.this, DayView.class);
//                    intent.putExtra("day", day);
//                    intent.putExtra("month", month);
//                    intent.putExtra("year", year);
//                    intent.putExtra("dateEpoch", epoch);
//                    intent.putExtra("default", false);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
                } //else {
                // Intent intent = new Intent(EventView.this, AgendaView.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //startActivity(intent);
                //}
            }
        });
    }





    /**
     * Sets all data to be displayed, called from on create and on activity results
     *
     * @param startY Start year of event
     * @param startM Start month of event
     * @param startD Start day of the event
     * @param startH Start hour of the event
     * @param startMi Start minute of the event
     * @param endH End hour of the event
     * @param endM End minute of the event
     * @param occurrenceId Type of occurrence (Daily, Weekly, Monthly)
     * @param eventId Name of the event
     * @param extraId Comments of the event
     * @param color Color for the event
     */
    public void setData(int startY, int startM, int startD, int startH, int startMi, int endH,
                        int endM, String occurrenceId, String eventId, String extraId, String color){

        String occurrence;
        AddEvent eventData = new AddEvent();

        if (occurrenceId.equals("Single")){occurrence = "occurring once";}
        else {occurrence = "occurring " + occurrenceId;}
        ((TextView) findViewById(R.id.event_view_data)).setText(occurrence);

        StringBuilder data = new StringBuilder("\n" + "Starts on " +
                eventData.getDateString(startY, startM , startD) +
                "\n" + "at " + eventData.getTimeString(startH , startMi) + " until " +
                eventData.getTimeString(endH , endM) + "\n" + occurrence + "\n" +
                "denoted with the color " + color);

        ((TextView)findViewById(R.id.event_view_data)).setText(data);
        ((TextView)findViewById(R.id.event_view_name)).setText(eventId);
        ((TextView)findViewById(R.id.event_view_comments)).setText(extraId);
        ((TextView)findViewById(R.id.event_view_comments)).setMovementMethod(new ScrollingMovementMethod());

    }

}
