package com.t3421.calenderapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by Connor Mahaffey on 12/3/2017.
 */
@RunWith(Parameterized.class)
public class MonthViewTest {

    private MonthView monthView;
    private int inputDay;
    private int inputMonth;
    private int inputYear;
    private long expectedOutput;

    public MonthViewTest(int day, int month, int year, long output) {
        inputDay = day;
        inputMonth = month;
        inputYear = year;
        expectedOutput = output;
    }

    @Before
    public void setUp() throws Exception {
        monthView = new MonthView();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> valueParis() {
        return Arrays.asList(new Object[][] {{4, 12, 2017, 1512370800000L}, {50, 12, 2017, 0L}, {4, 18, 2017, 0L}, {31, 12, 1969, 0L}});
    }

    @Test
    public void toEpochTest() throws Exception {
        assertEquals("Expected epoch for " + inputDay + "/" + inputMonth + "/" + inputYear + " ", expectedOutput, monthView.toEpoch(inputDay, inputMonth, inputYear));
    }

}