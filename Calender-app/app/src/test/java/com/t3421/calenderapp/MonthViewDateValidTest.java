package com.t3421.calenderapp;

import com.t3421.calenderapp.MonthView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by Alex on 12/6/2017.
 */
@RunWith(Parameterized.class)
public class MonthViewDateValidTest {

    private MonthView monthViewTest;
    private int dayInput;
    private int monthInput;
    private int yearInput;
    private boolean expectedOutput;

    public MonthViewDateValidTest(int day, int month, int year, boolean output) {
        dayInput = day;
        monthInput = month;
        yearInput = year;
        expectedOutput = output;
    }

    @Before
    public void setUp() throws Exception {
        monthViewTest = new MonthView();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> valuePairs() {
        return Arrays.asList(new Object[][] {{4,12,2017,true}, {32,12,2017,false}, {31,11,2017,false}, {29,2,2020,true},
                {29,2,2018,false}, {30,2,2020,false}, {0,3,2017,false}, {1,0,2017,false}, {1,1,1969,false}});
    }

    @Test
    public void isDateValid() throws Exception {
        assertEquals(expectedOutput, monthViewTest.isDateValid(dayInput, monthInput, yearInput));
    }
}