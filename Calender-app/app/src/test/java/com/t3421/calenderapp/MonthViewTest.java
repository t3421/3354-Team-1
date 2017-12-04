package com.t3421.calenderapp;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Connor Mahaffey on 12/3/2017.
 */
public class MonthViewTest {

    private MonthView monthView;

    @Before
    public void setUp() throws Exception {
        monthView = new MonthView();
    }

    @Test
    public void toEpoch() throws Exception {
        assertEquals(1512370800000L, monthView.toEpoch(4, 12, 2017));
        assertEquals(0L, monthView.toEpoch(50, 12, 2017));
        assertEquals(0L, monthView.toEpoch(4, 18, 2017));
        assertEquals(0L, monthView.toEpoch(31, 12, 1969));
    }

    @Test
    public void getDayFromEpoch() throws Exception {
        assertEquals("01", monthView.getDayFromEpoch(1512111600000L));
        assertEquals("15", monthView.getDayFromEpoch(1544857200000L));
        assertEquals("31", monthView.getDayFromEpoch(0L));
    }

    @Test
    public void getMonthFromEpoch() throws Exception {
        assertEquals("01", monthView.getMonthFromEpoch(1483254000000L));
        assertEquals("06", monthView.getMonthFromEpoch(1497506400000L));
        assertEquals("12", monthView.getMonthFromEpoch(0L));
    }

    @Test
    public void getYearFromEpoch() throws Exception {
        assertEquals("2017", monthView.getYearFromEpoch(1483254000000L));
        assertEquals("2018", monthView.getYearFromEpoch(1514790000000L));
        assertEquals("1969", monthView.getYearFromEpoch(0L));
    }

    @Test
    public void isDateValid() throws Exception {
        assertTrue(monthView.isDateValid(4, 12, 2017));
        assertFalse(monthView.isDateValid(32, 12, 2017));
        assertFalse(monthView.isDateValid(31, 11, 2017));
        assertTrue(monthView.isDateValid(29, 2, 2020));
        assertFalse(monthView.isDateValid(29, 2, 2018));
        assertFalse(monthView.isDateValid(30, 2, 2020));
        assertFalse(monthView.isDateValid(0, 3, 2017));
        assertFalse(monthView.isDateValid(1, 0, 2017));
        assertFalse(monthView.isDateValid(1, 1, 1969));
    }

}