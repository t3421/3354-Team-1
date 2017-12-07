package com.t3421.calenderapp;

import com.t3421.calenderapp.AddEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by Chris on 12/6/2017.
 */
@RunWith(Parameterized.class)
public class AddEventValidateTest {

    private AddEvent addEventTester;
    private boolean expectedOut;
    private int inputStartH;
    private int inputStartM;
    private int inputEndH;
    private int inputEndM;

    public AddEventValidateTest(int startH, int startM, int endH, int endM, boolean expectedOutput) {
        inputStartH = startH;
        inputStartM = startM;
        inputEndH = endH;
        inputEndM = endM;
        expectedOut = expectedOutput;
    }

    @Before
    public void setUp() throws Exception {
        addEventTester = new AddEvent();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> valuePairs() {
        return Arrays.asList(new Object[][] {{0,0,12,30,true}, {24,0,24,1,true}, {12,30,0,0,true}, {12,0,11,1,false}});
    }

    @Test
    public void validate() throws Exception {
        assertEquals(expectedOut, addEventTester.validate(inputStartH, inputStartM, inputEndH, inputEndM));
    }

}