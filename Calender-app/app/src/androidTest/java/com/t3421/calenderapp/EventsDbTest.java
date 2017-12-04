package com.t3421.calenderapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Chris on 12/4/2017.
 */
public class EventsDbTest {

    private EventsDb tester;
    private Event event;
    private List<Event> events;

    @Before
    public void setUp() throws Exception {
        tester = new EventsDb(InstrumentationRegistry.getContext());
    }

    @Test
    public void getEvent() throws Exception {
        assertEquals(null, tester.getEvent(0));
        assertEquals(event, tester.getEvent(1));
        assertEquals(event, tester.getEvent(2));
        assertEquals(event, tester.getEvent(3));
    }

    @Test
    public void checkForConflict() throws Exception {
        assertEquals(true, event);
        assertEquals(false, event);
    }

    @Test
    public void getAllEvents() throws Exception {
        assertEquals(events,null);
    }

}