package com.t3421.calenderapp;

/** Basic Event class on which the database is built. Is best utilized through
 *  its constructor, though it does contain setters as well.
 *  @author Chris Wilson
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
    private int month;
    private int occurrenceId;
    private String eventName;
    private String eventDetails;
    private String occurrence;
    private String color;

    //Default Constructor
    public Event() {
    }

    //Constructor for creating an event
    public Event(int startMin, int endMin, int startHour, int endHour, int day, int year, int month, String eventName, String eventDetails, String occurrence, String color) {
        this.startMin = startMin;
        this.endMin = endMin;
        this.startHour = startHour;
        this.endHour = endHour;
        this.day = day;
        this.year = year;
        this.month = month;
        this.eventName = eventName;
        this.eventDetails = eventDetails;
        this.occurrence = occurrence;
        this.color = color;
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

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) { this.month = month;}

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

    public String getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(String occurrence) {
        this.occurrence = occurrence;
    }

    public String getColor() { return color;}

    public void setColor(String color) { this.color = color; }

    public  int getOccurrenceId(){return occurrenceId;}

    public void setOccurrenceId(int occurrenceId){this.occurrenceId = occurrenceId;}

    /** Overloads object's toString() method to return all relevant event information
    *   in a concise string format.
    *   @return Event information in string format
    */
    @Override
    public String toString(){
        String startAMPM = toAMPM(startHour, startMin);
        String endAMPM = toAMPM(endHour, endMin);

        String s = getEventName() + "\n" + month + "/" + day + "/" + year + "\n" + startAMPM + " - " + endAMPM + "\n"
                + eventDetails;
        return s;
    }

    /** Private helper method for use in toString(), converts 24-hour scale to 12-hour plus AM or PM,
    *   adds leading zero to minute if necessary.
    *   @param  hour    24-hour scale hour integer
    *   @param  minute  start or end time minute value
    *   return  formatted time string          
    */
    private String toAMPM(int hour, int min){
        String AMPM = hour + ":" + String.format("%02d", min) + " AM";
        if(hour >= 12){
            if(hour > 12)
                AMPM = hour - 12 + ":" + String.format("%02d", min) + " PM";
        }
        return AMPM;
    }
}
