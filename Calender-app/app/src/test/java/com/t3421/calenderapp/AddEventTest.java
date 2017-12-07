package com.t3421.calenderapp;

import com.t3421.calenderapp.AddEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;



import static org.junit.Assert.*;

/**
 * @author Theodore Sosnowski
 */
@RunWith(Parameterized.class)
public class AddEventTest {

    AddEvent tester;
    int yearInput;
    int monthInput;
    int dayInput;
    String expectedOutput;

    public AddEventTest(String output, int year, int month, int day) {
        expectedOutput = output;
        yearInput = year;
        monthInput = month;
        dayInput = day;
    }

    @Before
    public void setUp() throws Exception {
        tester = new AddEvent();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> valuePairs() {
        return Arrays.asList(new Object[][] {{"1 January 2017", 2017, 1, 1}, {"31 November 2017", 2017, 11, 31}, {"", 0, 1, 1}, {"", 2017, 1, 0}});
    }

    @Test
    public void getDateString() throws Exception {
        assertEquals(expectedOutput, tester.getDateString(yearInput, monthInput, dayInput));

    }
}