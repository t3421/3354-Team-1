package com.t3421.calenderapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button button;
    int startYear = 0, startMonth = 0, startDay = 0, startHour = 0, startMinute = 0, endHour = 0, endMinute = 0;
    String eventTitle, eventComments, colorSelected, selection;
    EventsDb db = new EventsDb(this);
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if(requestCode == 2 && requestCode == RESULT_OK)
        //Toast.makeText(getBaseContext(),startMinute + endMinute +startHour + endHour + startDay + startYear, Toast.LENGTH_LONG).show();


        if (requestCode == 1 && resultCode == RESULT_OK) {

            String eventTitle = data.getStringExtra("eventTitle");
            startYear = data.getIntExtra("startYear", 0);
            startMonth = data.getIntExtra("startMonth" , 0);
            startDay = data.getIntExtra("startDay", 0);
            startHour = data.getIntExtra("startHour", 0);
            startMinute = data.getIntExtra("startMinute", 0);
            endHour = data.getIntExtra("endHour", 0);
            endMinute = data.getIntExtra("endMinute", 0);
            String occurrence = data.getStringExtra("occurrence");
            String extraComments = data.getStringExtra("extraComments");


            Event event =  new Event(startMinute, endMinute, startHour, endHour, startDay, startYear, startMonth, eventTitle, extraComments, occurrence, colorSelected);
            if(db.checkForDuplicate(event)){
                Toast.makeText(getBaseContext(), "Duplicate", Toast.LENGTH_LONG).show();
            }
            else {
                db.insertEvent(event);
                Toast.makeText(getBaseContext(), eventTitle + " Starts on " + startDay + "/" + startMonth + "/" + startYear + " at " + startHour + ":" + startMinute + " ending " + endHour + ":" + endMinute + " with comments '" + extraComments + "'" + " using color " + colorSelected + " occurs, " + occurrence, Toast.LENGTH_LONG).show();
            }
        }
        else if ((requestCode == 1 || requestCode == 2) && resultCode == RESULT_CANCELED){
            Toast.makeText(getBaseContext(), "Canceled by user", Toast.LENGTH_LONG).show();
        }
        if (requestCode == 2 && resultCode==RESULT_OK){
            //Delete from DB and add new items from above
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from activity_main.xml
        setContentView(R.layout.activity_main);

        // Locate the button in activity_main.xml
        Button eventAdd = (Button) findViewById(R.id.add_event);
        eventAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddEvent.class);
                intent.putExtra("startMinute" , 0);
                intent.putExtra("startHour" , 0);
                intent.putExtra("endHour" , 0);
                intent.putExtra("endMinute" , 0);
                intent.putExtra("eventTitle" , "");
                intent.putExtra("extraComments" , "");
                intent.putExtra("occurrence" , "");
                intent.putExtra("startDay" , 0);
                intent.putExtra("startMonth" , 0);
                intent.putExtra("startYear" , 0);
                intent.putExtra("colorSelected" , "");
                intent.putExtra("default" , true);
                startActivityForResult(intent, 1);
            }
        });



        Button eventEdit = (Button) findViewById(R.id.edit_event);
        eventEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddEvent.class);
                intent.putExtra("startMinute" , 10);//startMinute );
                intent.putExtra("startHour" , 10);//startHour );
                intent.putExtra("endHour" , 11);//endHour );
                intent.putExtra("endMinute" , 11);//endMinute );
                intent.putExtra("eventTitle" , "event 1");//eventTitle );
                intent.putExtra("extraComments" , "hello to all");//eventComments);
                intent.putExtra("occurrence" , "Weekly");//selection );
                intent.putExtra("startDay" , 1);//startDay );
                intent.putExtra("startMonth" , 1);//startMonth );
                intent.putExtra("startYear" , 2011);//startYear );
                intent.putExtra("colorSelected" , "Green");//colorSelected );
                intent.putExtra("default" , false);
                startActivityForResult(intent, 2);
            }
        });

        //go to week view:
        Button weekView = (Button) findViewById(R.id.week_view_button);
        weekView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WeekView.class);
                //pass in some data?
                startActivity(intent);
            }
        });

        //go to agenda view:
        Button agendaView = (Button) findViewById(R.id.agenda_view_button);
        agendaView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(MainActivity.this, AgendaView.class);
            }
        });


    }
}
