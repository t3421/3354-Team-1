package com.t3421.calenderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * DayView class creates the gui for teh day view. The day view shows a list of all the events on that day.
 * Clicking on an event takes the user to the event view page for that event.
 *
 * @author Connor Mahaffey
 */
public class DayView extends AppCompatActivity {

    private SimpleDateFormat dateFormatTitle = new SimpleDateFormat("MMMM dd yyyy", Locale.getDefault());
    String[] defaultText = {"No Events"};
    private EventsDb database;
    private List<Event> events;
    private List<Event> eventsOnDay;
    int day;
    int month;
    int year;
    long dateEpoch;
    private ListView listView;
    private ArrayAdapter<String> adapter;

    /**
     * Creates gui.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);

        database = new EventsDb(this);

        getInfo();

        String dateTitle = dateFormatTitle.format(new Date(dateEpoch));
        TextView titleText = (TextView) findViewById(R.id.title_date_text_view);
        titleText.setText(dateTitle);

        adapter = createAdapter();

        listView = (ListView) findViewById(R.id.agenda_list_view_day_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                if(!eventsOnDay.isEmpty()) {
                    Event e = eventsOnDay.get(position);

                    Intent intent = new Intent(DayView.this, EventView.class);
                    intent.putExtra("startMinute", e.getStartMin());
                    intent.putExtra("startHour", e.getStartHour());
                    intent.putExtra("endHour", e.getEndHour());
                    intent.putExtra("endMinute", e.getEndMin());
                    intent.putExtra("eventTitle", e.getEventName());
                    intent.putExtra("extraComments", e.getEventDetails());
                    intent.putExtra("occurrence", e.getOccurrence());
                    intent.putExtra("startDay", e.getDay());
                    intent.putExtra("startMonth", e.getMonth());
                    intent.putExtra("startYear", e.getYear());
                    intent.putExtra("colorSelected", e.getColor());
                    intent.putExtra("viewType", 2);
                    intent.putExtra("id", e.getId());
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * Updaates gui when this view regains focus.
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        getInfo();
        adapter = createAdapter();
        listView.setAdapter(adapter);
    }

    /**
     * Gets the info from that is passed from other views.
     */
    private void getInfo() {
        Intent intent = getIntent();
        day = Integer.parseInt(intent.getStringExtra("day"));
        month = Integer.parseInt(intent.getStringExtra("month"));
        year = Integer.parseInt(intent.getStringExtra("year"));
        dateEpoch = intent.getLongExtra("dateEpoch", 0);
        System.out.println(day + "/" + month + "/" + year + " " + dateEpoch);
    }

    /**
     * Creates the adapter that is used by the ListView
     * @return an ArrayAdapter with info needed for the ListView
     */
    private ArrayAdapter<String> createAdapter() {
        events = database.getAllEvents();
        eventsOnDay = getEventsOnDay(events);
        List<String> stringEvents = getEventStringsOnDay(eventsOnDay);
        ArrayAdapter<String> adapter;

        if(stringEvents != null && !stringEvents.isEmpty())
            adapter = new ArrayAdapter<String>(this, R.layout.activity_day_view, R.id.text_view_for_list_view_day_view, stringEvents);
        else
            adapter = new ArrayAdapter<String>(this, R.layout.activity_day_view, R.id.text_view_for_list_view_day_view, defaultText);

        return adapter;
    }

    /**
     * Creates a list of all events on the day of this view.
     * @param events the list of events
     * @return a list of all events on the day of this view.
     */
    private List<Event> getEventsOnDay(List<Event> events) {
        List<Event> eventsOnDay = new LinkedList<Event>();
        for(Event e : events) {
            if(e.getDay() == day && e.getMonth() == month && e.getYear() == year)
                eventsOnDay.add(e);
        }
        return eventsOnDay;
    }

    /**
     * Creates a list of all string representations of events in a given list.
     * @param events the list of events
     * @return a list of string representations of events in the given list.
     */
    private List<String> getEventStringsOnDay(List<Event> events) {
        List<String> eventStringsOnDay = new LinkedList<String>();
        for(Event e : events) {
            eventStringsOnDay.add(e.toString());
        }
        return eventStringsOnDay;
    }
}
