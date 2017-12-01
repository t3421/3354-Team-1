package com.t3421.calenderapp;

import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.*;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;



public class MonthView extends AppCompatActivity {

    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
    private CompactCalendarView calendar;
    private EventsDb database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_view);

        final TextView title = (TextView) findViewById(R.id.month_title_text);
        title.setText(dateFormatMonth.format(new Date()));

        calendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setUseThreeLetterAbbreviation(true);

        database = new EventsDb(this);
        final List<com.t3421.calenderapp.Event> events = database.getAllEvents();
        for(int i = 0; i < events.size(); i++)
        {
            com.t3421.calenderapp.Event eve = events.get(i);
            System.out.println(eve.getEventName() + ":  " + eve.getDay() + "/" + eve.getMonth() + "/" + eve.getYear() + "\t" + eve.getColor());
            calendar.addEvent(new Event(getColor(getColorInt(eve.getColor())), toEpoch(eve.getDay(),eve.getMonth(),eve.getYear())), true);
        }

        calendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                //Toast.makeText(getBaseContext(), String.valueOf(dateClicked.getTime()), Toast.LENGTH_LONG).show();
                List<Event> eventsOnDay = calendar.getEvents(dateClicked);
                String s = "";
                for(Event e : eventsOnDay) {
                    com.t3421.calenderapp.Event eventOnDay = getEventFromList(events, e.getTimeInMillis());
                    if(eventOnDay != null)
                        s += eventOnDay.getEventName() + "\n";
                }
                Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                title.setText(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });
    }

    private long toEpoch(int day, int month, int year) {
        long epoch = 0;
        try {
            epoch = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(month + "/" + day + "/" + year + " 01:00:00").getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return epoch;
    }

    private String getDayFromEpoch(long epoch) {
        String date = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (epoch));
        return date.substring(3,5);
    }

    private String getMonthFromEpoch(long epoch) {
        String date = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (epoch));
        return date.substring(0,2);
    }

    private String getYearFromEpoch(long epoch) {
        String date = new java.text.SimpleDateFormat("MM/dd/yyyy").format(new java.util.Date (epoch));
        return date.substring(6);
    }

    private com.t3421.calenderapp.Event getEventFromList(List<com.t3421.calenderapp.Event> eventsList, long epoch) {
        int day = Integer.parseInt(getDayFromEpoch(epoch));
        int month = Integer.parseInt(getMonthFromEpoch(epoch));
        int year = Integer.parseInt(getYearFromEpoch(epoch));

        com.t3421.calenderapp.Event event = null;
        for(com.t3421.calenderapp.Event e : eventsList) {
              if(e.getDay() == day && e.getMonth() == month && e.getYear() == year)
                  event = e;
        }
        return event;
    }

    private int getColorInt(String colorName) {
        switch(colorName.toLowerCase()) {
            case "yellow":
                return R.color.yellow;
            case "fuchsia":
                return R.color.fuchsia;
            case "red":
                return R.color.red;
            case "green":
                return R.color.green;
            case "purple":
                return R.color.purple;
            case "blue":
                return  R.color.blue;
            case "maroon":
                return R.color.maroon;
            case "teal":
                return R.color.teal;
            default:
                return R.color.red;
        }
    }
}
