/**AgendaView Class
 * Pulls Events from the EventsDb and displays them in a clickable list view.
 * On clicking an element of the list, user is taken to the EventView.
 *
 * @author Alex Wimer
 */

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
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class AgendaView extends Activity {

    String stringsArr[] = {"No Events"};
    private EventsDb database;
    List<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_view);

        database = new EventsDb(this);

        //Get the list of events from database and convert to a list of strings.
        events = database.getAllEvents();
        List<String> stringEvents = toListOfEventStrings(events);
        ArrayAdapter<String> adapter;

        if(stringEvents != null && !stringEvents.isEmpty())
            adapter = new ArrayAdapter<String>(this, R.layout.activity_agenda_view, R.id.textViewForListView, stringEvents);
        else
            adapter = new ArrayAdapter<String>(this, R.layout.activity_agenda_view, R.id.textViewForListView, stringsArr);

        ListView listView = (ListView) findViewById(R.id.agendaListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                //Intent intent = new Intent(AgendaView.this, EventView.class);
                //startActivity(intent);
                Event e = events.get(position);
                //String s;
                //Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(AgendaView.this, EventView.class);
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
                intent.putExtra("default" , true);
                startActivityForResult(intent, 1);
            }
        });
    }

    private List<String> toListOfEventStrings(List<Event> events) {
        List<String> eventsString = new LinkedList<String>();
        for(Event e : events) {
            eventsString.add(e.toString());
        }
        return eventsString;
    }
}
