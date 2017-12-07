package com.t3421.calenderapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class AddEventTest {

     AddEvent tester;

    @Before
    public void setUp() throws Exception {
        tester = new AddEvent();
    }

    @Test
    public void getDateString() throws Exception {
        assertEquals("1 January 2017" , tester.getDateString(2017,1,1));
        assertEquals("31 November 2017" , tester.getDateString(2017,11,31));
        assertEquals("" , tester.getDateString(0,1,1));
        assertEquals("" , tester.getDateString(2017,1,0));
    }


    @Test
    public void validate() throws Exception {
        assertEquals(true , tester.validate(0,0,12,30));
        assertEquals(true , tester.validate(24,0,24,1));
        assertEquals(true , tester.validate(12,30,0,0));
        assertEquals(false , tester.validate(12,0,11,1));
    }

    @Test
    public void getTimeString() throws Exception {
        assertEquals("" , tester.getTimeString(0,0));
        assertEquals("12:00 PM" , tester.getTimeString(12,0));
        assertEquals("12:30 AM" , tester.getTimeString(0,30));
        assertEquals("9:01 PM" , tester.getTimeString(21,1));
    }

    @Test
    public void getSpinnerPosition() throws Exception {
        assertEquals( 0 , tester.getSpinnerPosition(""));
        assertEquals( 0 , tester.getSpinnerPosition("Yellow"));
        assertEquals( 1 , tester.getSpinnerPosition("Fuchsia"));
        assertEquals( 2 , tester.getSpinnerPosition("Red"));
        assertEquals( 3 , tester.getSpinnerPosition("Green"));
        assertEquals( 4 , tester.getSpinnerPosition("Purple"));
        assertEquals( 5 , tester.getSpinnerPosition("Blue"));
        assertEquals( 6 , tester.getSpinnerPosition("Maroon"));
        assertEquals( 7 , tester.getSpinnerPosition("Teal"));
    }
}