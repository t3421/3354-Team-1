package com.t3421.calenderapp;

import android.graphics.Color;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.*;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class MonthView extends AppCompatActivity {

    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
    private CompactCalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_view);

        final TextView title = (TextView) findViewById(R.id.month_title_text);
        title.setText(dateFormatMonth.format(new Date()));

        calendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setUseThreeLetterAbbreviation(true);

        calendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

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
}
