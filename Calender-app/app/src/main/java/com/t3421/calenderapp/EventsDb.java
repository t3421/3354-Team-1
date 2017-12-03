package com.t3421.calenderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.os.Bundle;

import java.util.Calendar;
import java.util.LinkedList;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.LinkedList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ExpandableListView;


public class EventsDb extends SQLiteOpenHelper {

    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final  String DATABASE_NAME = "Events";

    //SQL Table Name
    private static final String TABLE_NAME = "CalendarEvents";

    //Columns Names
    private static final String KEY_ID = "id";
    private static final String KEY_START_MIN = "StartMin";
    private static final String KEY_END_MIN = "EndMin";
    private static final String KEY_START_HOUR = "StartHour";
    private static final String KEY_END_HOUR = "EndHour";
    private static final String KEY_DAY = "Day";
    private static final String KEY_YEAR = "Year";
    private static final String KEY_MONTH = "Month";
    private static final String KEY_NAME = "EventName";
    private static final String KEY_DETAILS = "EventDetails";
    private static final String KEY_OCCURANCE = "Occurance";
    private static final String KEY_COLOR = "Color";
    private static final String KEY_OCCURANCEID = "OccuranceId";

    //private static final String[] COLUMNS = {KEY_ID,KEY_START,KEY_END,KEY_DAY,KEY_YEAR,KEY_MONTH,KEY_NAME,KEY_DETAILS};

    public EventsDb(Context context ){super(context, DATABASE_NAME,null, DATABASE_VERSION);}


    //Create Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_START_MIN + " INTEGER, "+
                KEY_END_MIN + " INTEGER, "+
                KEY_START_HOUR + " INTEGER, "+
                KEY_END_HOUR + " INTEGER, "+
                KEY_DAY + " INTEGER, " +
                KEY_YEAR + " INTEGER, " +
                KEY_MONTH + " INTEGER, " +
                KEY_NAME + " TEXT, " +
                KEY_DETAILS + " TEXT, "+
                KEY_OCCURANCE + " TEXT, "+
                KEY_COLOR + " TEXT )";

        db.execSQL(CREATE_EVENTS_TABLE);
    }

    //Upgrades on new version of DB
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    //Insert new event into table
    public void insertEvent( Event event ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //Collects event information
        contentValues.put(KEY_START_MIN, event.getStartMin());
        contentValues.put(KEY_END_MIN,event.getEndMin());
        contentValues.put(KEY_START_HOUR,event.getStartHour());
        contentValues.put(KEY_END_HOUR,event.getEndHour());
        contentValues.put(KEY_DAY,event.getDay());
        contentValues.put(KEY_YEAR,event.getYear());
        contentValues.put(KEY_MONTH,event.getMonth());
        contentValues.put(KEY_NAME,event.getEventName());
        contentValues.put(KEY_DETAILS,event.getEventDetails());
        contentValues.put(KEY_OCCURANCE,event.getOccurance());
        contentValues.put(KEY_COLOR,event.getColor());

        db.insert(TABLE_NAME, null, contentValues);

        db.close();
    }

    public void deleteEvent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID+"="+id, null);
    }

    //Pulls event from table based on ID
    public Event getEvent(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor object, access data in SQL database
        Cursor cursor =
                db.query(TABLE_NAME, new String[] {KEY_ID,KEY_START_MIN,KEY_END_MIN,KEY_START_HOUR,KEY_END_HOUR,KEY_DAY,KEY_YEAR,KEY_MONTH,KEY_NAME,KEY_DETAILS,KEY_OCCURANCE,KEY_COLOR},
                        KEY_ID + "=?",
                        new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Event event = new Event();
        event.setId(Integer.parseInt(cursor.getString(0)));
        event.setStartMIn(Integer.parseInt(cursor.getString(1)));
        event.setEndMin(Integer.parseInt(cursor.getString(2)));
        event.setStartHour(Integer.parseInt(cursor.getString(3)));
        event.setEndHour(Integer.parseInt(cursor.getString(4)));
        event.setDay(Integer.parseInt(cursor.getString(5)));
        event.setYear(Integer.parseInt(cursor.getString(6)));
        event.setMonth(Integer.parseInt(cursor.getString(7)));
        event.setEventName(cursor.getString(8));
        event.setEventDetails(cursor.getString(9));
        event.setOccurance(cursor.getString(10));
        event.setColor(cursor.getString(11));

        return event;

    }

    public List<Event> getAllEvents(){
        List<Event> events = new LinkedList<>();

        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] {KEY_ID,KEY_START_MIN,KEY_END_MIN,KEY_START_HOUR,KEY_END_HOUR,KEY_DAY,KEY_YEAR,KEY_MONTH,KEY_NAME,KEY_DETAILS,KEY_OCCURANCE,KEY_COLOR},
                null, null, null, null, KEY_YEAR + " ASC, " + KEY_MONTH + " ASC, " + KEY_DAY + " ASC, " + KEY_START_HOUR + " ASC");

        Event event = null;
        if( cursor.moveToFirst()){
            do{
                event = new Event();
                event.setId(Integer.parseInt(cursor.getString(0)));
                event.setStartMIn(Integer.parseInt(cursor.getString(1)));
                event.setEndMin(Integer.parseInt(cursor.getString(2)));
                event.setStartHour(Integer.parseInt(cursor.getString(3)));
                event.setEndHour(Integer.parseInt(cursor.getString(4)));
                event.setDay(Integer.parseInt(cursor.getString(5)));
                event.setYear(Integer.parseInt(cursor.getString(6)));
                event.setMonth(Integer.parseInt(cursor.getString(7)));
                event.setEventName(cursor.getString(8));
                event.setEventDetails(cursor.getString(9));
                event.setOccurance(cursor.getString(10));
                event.setColor(cursor.getString(11));

                events.add(event);
            }while (cursor.moveToNext());
            cursor.close();
        }
        return events;
    }

    public void updateEvent(int id, int startMin, int endMin, int startHour, int endHour, int day, int year, int month, String eventName, String eventDetails, String occurance, String color ){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_START_MIN, startMin);
        contentValues.put(KEY_END_MIN, endMin);
        contentValues.put(KEY_START_HOUR, startHour);
        contentValues.put(KEY_END_HOUR, endHour);
        contentValues.put(KEY_DAY, day);
        contentValues.put(KEY_YEAR, year);
        contentValues.put(KEY_MONTH, month);
        contentValues.put(KEY_NAME, eventName);
        contentValues.put(KEY_DETAILS, eventDetails);
        contentValues.put(KEY_OCCURANCE, occurance);
        contentValues.put(KEY_COLOR, color);

        db.update(TABLE_NAME, contentValues, KEY_ID+"="+id, null);


    }

    public boolean checkForConflict(Event event){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =
                db.query(TABLE_NAME, new String[] {KEY_ID,KEY_START_MIN,KEY_END_MIN,KEY_START_HOUR,KEY_END_HOUR},
                        KEY_DAY + "=? and " + KEY_MONTH + "=? and " + KEY_YEAR + "=?",
                        new String[] {String.valueOf(event.getDay()),String.valueOf(event.getMonth()),String.valueOf(event.getYear()) }
                        , null, null, null, null);
        if (cursor == null)
            return  false;
        if( cursor.moveToFirst()) {
            if (event.getId() == cursor.getInt(0) && event.getId() != 0) {
                return false;
            }
            int eventStart = event.getStartHour() * 60 + event.getStartMin();
            int eventEnd = event.getEndHour() * 60 + event.getEndMin();
            do {
                int dbStart = cursor.getInt(3) * 60 + cursor.getInt(1);
                int dbEnd = cursor.getInt(4) * 60 + cursor.getInt(2);

                if (eventStart >= dbStart && eventStart <= dbEnd)
                    return true;

                if (eventEnd >= dbStart && eventEnd <= dbEnd)
                    return true;

            } while (cursor.moveToNext());
            cursor.close();
        }
        cursor.close();
        return false;


    }

    public void eventOccurance(Event event){
        int currentYear = event.getYear();

        if (event.getOccurance().equals("Weekly")) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, event.getDay());
            cal.set(Calendar.MONTH, event.getMonth() - 1);
            cal.set(Calendar.YEAR, event.getYear());
            while (cal.get(Calendar.YEAR) <= currentYear ){
                cal.add(Calendar.DAY_OF_MONTH, 7);
                event.setDay(cal.get(Calendar.DAY_OF_MONTH));
                event.setMonth(cal.get(Calendar.MONTH) + 1);
                event.setYear(cal.get(Calendar.YEAR));
                if (!checkForConflict(event)) {
                    insertEvent(event);
                }
            }
        }

        if (event.getOccurance().equals("Monthly")) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.DAY_OF_MONTH, event.getDay());
            cal.set(Calendar.MONTH, event.getMonth() - 1);
            cal.set(Calendar.YEAR, event.getYear());
            while (cal.get(Calendar.YEAR) <= currentYear ){
                cal.add(Calendar.MONTH, 1);
                event.setMonth(cal.get(Calendar.MONTH) + 1);
                event.setYear(cal.get(Calendar.YEAR));
                if (!checkForConflict(event)) {
                    insertEvent(event);
                }
            }
        }

    }





}
