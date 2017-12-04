package com.t3421.calenderapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

/**AgendaView Class
 * Pulls Events from the EventsDb and displays them in a clickable list view.
 * On clicking an element of the list, user is taken to the EventView.
 *
 * @author Alex Wimer
 * @author Connor Mahaffey
 */
public class AgendaView extends AppCompatActivity {

    String stringsArr[] = {"No Events"};
    private EventsDb database;
    private List<Event> events;
    private ArrayAdapter<String> adapter;
    private ListView listView;

    /**
     * Creates gui.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_view);

        TextView titleText = (TextView) findViewById(R.id.title_text_agenda_view);
        titleText.setText("Agenda");

        database = new EventsDb(this);

        //Get the list of events from database and convert to a list of strings.
        adapter = createAdapter();

        listView = (ListView) findViewById(R.id.agenda_list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                if(!events.isEmpty()) {
                    Event e = events.get(position);

                    Intent intent = new Intent(AgendaView.this, EventView.class);
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
                    intent.putExtra("id", e.getId());
                    intent.putExtra("viewType", 1);
                    //  startActivityForResult(intent, 1);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * Updates gui when view regains focus.
     */
    @Override
    protected void onRestart() {
        super.onRestart();

        adapter = createAdapter();
        listView.setAdapter(adapter);
    }

    /**
     * Creates the adapter that is used by the ListView
     * @return an ArrayAdapter with info needed for the ListView
     */
    private ArrayAdapter<String> createAdapter() {
        events = database.getAllEvents();
        List<String> stringEvents = toListOfEventStrings(events);
        ArrayAdapter<String> adapter;

        if(stringEvents != null && !stringEvents.isEmpty())
            adapter = new ArrayAdapter<String>(this, R.layout.activity_agenda_view, R.id.text_view_for_list_view, stringEvents);
        else
            adapter = new ArrayAdapter<String>(this, R.layout.activity_agenda_view, R.id.text_view_for_list_view, stringsArr);

        return adapter;
    }

    /**
     * Creates a list of all string representations of events in a given list.
     * @param events the list of events
     * @return a list of string representations of events in the given list.
     */
    private List<String> toListOfEventStrings(List<Event> events) {
        List<String> eventsString = new LinkedList<String>();
        for(Event e : events) {
            eventsString.add(e.toString());
        }
        return eventsString;
    }
}
