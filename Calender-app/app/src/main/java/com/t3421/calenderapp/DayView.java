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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_view);

        database = new EventsDb(this);

        Intent intent = getIntent();
        day = Integer.parseInt(intent.getStringExtra("day"));
        month = Integer.parseInt(intent.getStringExtra("month"));
        year = Integer.parseInt(intent.getStringExtra("year"));
        dateEpoch = intent.getLongExtra("dateEpoch", 0);

        String dateTitle = dateFormatTitle.format(new Date(dateEpoch));
        TextView titleText = (TextView) findViewById(R.id.title_date_text_view);
        titleText.setText(dateTitle);

        ArrayAdapter<String> adapter = createAdapter();
//        events = database.getAllEvents();
//        List<String> stringEvents = getEventStringsOnDay(events, day, month, year);
//        ArrayAdapter<String> adapter;
//
//        if(stringEvents != null && !stringEvents.isEmpty())
//            adapter = new ArrayAdapter<String>(this, R.layout.activity_day_view, R.id.text_view_for_list_view_day_view, stringEvents);
//        else
//            adapter = new ArrayAdapter<String>(this, R.layout.activity_day_view, R.id.text_view_for_list_view_day_view, defaultText);

        ListView listView = (ListView) findViewById(R.id.agenda_list_view_day_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Event e = eventsOnDay.get(position);
                System.out.println(e.toString());

                Intent intent = new Intent(DayView.this, EventView.class);
                intent.putExtra("startMinute", e.getStartMin());
                intent.putExtra("startHour", e.getStartHour());
                intent.putExtra("endHour", e.getEndHour());
                intent.putExtra("endMinute" , e.getEndMin());
                intent.putExtra("eventTitle" , e.getEventName());
                intent.putExtra("extraComments" , e.getEventDetails());
                intent.putExtra("occurrence" , e.getOccurance());
                intent.putExtra("startDay" , e.getDay());
                intent.putExtra("startMonth", e.getMonth());
                intent.putExtra("startYear" , e.getYear());
                intent.putExtra("colorSelected" , e.getColor());
                intent.putExtra("viewType", 2);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private ArrayAdapter<String> createAdapter() {
        events = database.getAllEvents();
        eventsOnDay = getEventsOnDay(events);
        List<String> stringEvents = getEventStringsOnDay(events, day, month, year);
        ArrayAdapter<String> adapter;

        if(stringEvents != null && !stringEvents.isEmpty())
            adapter = new ArrayAdapter<String>(this, R.layout.activity_day_view, R.id.text_view_for_list_view_day_view, stringEvents);
        else
            adapter = new ArrayAdapter<String>(this, R.layout.activity_day_view, R.id.text_view_for_list_view_day_view, defaultText);

        return adapter;
    }

    private List<Event> getEventsOnDay(List<Event> events) {
        List<Event> eventsOnDay = new LinkedList<Event>();
        for(Event e : events) {
            if(e.getDay() == day && e.getMonth() == month && e.getYear() == year)
                eventsOnDay.add(e);
        }
        return eventsOnDay;
    }

    private List<String> getEventStringsOnDay(List<Event> events, int day, int month, int year) {
        List<String> eventStringsOnDay = new LinkedList<String>();
        for(Event e : events) {
            if(e.getDay() == day && e.getMonth() == month && e.getYear() == year)
                eventStringsOnDay.add(e.toString());
        }
        return eventStringsOnDay;
    }
}
