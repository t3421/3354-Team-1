package com.t3421.calenderapp;

/**
 * Created by Chris on 11/9/2017.
 */

public class Event {

    //Event attributes
    private int id;
    private int startMin;
    private int endMin;
    private int startHour;
    private int endHour;
    private int day;
    private int year;
    private String month;
    private String eventName;
    private String eventDetails;
    private String occurance;

    //Default Constructor
    public Event() {
    }

    //Constructor for creating an event
    public Event(int startMin, int endMin, int startHour, int endHour, int day, int year, String month, String eventName, String eventDetails, String occurance) {
        this.startMin = startMin;
        this.endMin = endMin;
        this.startHour = startHour;
        this.endHour = endHour;
        this.day = day;
        this.year = year;
        this.month = month;
        this.eventName = eventName;
        this.eventDetails = eventDetails;
        this.occurance = occurance;
    }

    //Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartMin() {
        return startMin;
    }

    public void setStartMIn(int startMin) {
        this.startMin = startMin;
    }

    public int getEndMin() {
        return endMin;
    }

    public void setEndMin(int endMin) {
        this.endMin = endMin;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    public String getOccurance() {
        return occurance;
    }

    public void setOccurance(String occurance) {
        this.occurance = occurance;
    }
}
