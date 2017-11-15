package com.t3421.calenderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.os.Bundle;

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
        Cursor cursor = db.rawQuery(query, null);

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
        contentValues.put(KEY_END_MIN,endMin);
        contentValues.put(KEY_START_HOUR,startHour);
        contentValues.put(KEY_END_HOUR,endHour);
        contentValues.put(KEY_DAY,day);
        contentValues.put(KEY_YEAR,year);
        contentValues.put(KEY_MONTH,month);
        contentValues.put(KEY_NAME,eventName);
        contentValues.put(KEY_DETAILS,eventDetails);
        contentValues.put(KEY_OCCURANCE,occurance);
        contentValues.put(KEY_COLOR,color);

        db.update(TABLE_NAME, contentValues, KEY_ID+"="+id, null);

    }





}
