package com.t3421.calenderapp;

import android.widget.ListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Alexander on 12/4/2017.
 */

public class AgendaViewTest {

    AgendaView tester;

    @Before
    public void setup() throws Exception{
        tester = new AgendaView();
    }

    @Test
    public void testStringsArray()throws Exception{
        Assert.assertNotEquals(null, tester.getStringsArr_array());
    }

    @Test
    public void testEventsList()throws Exception{
        Assert.assertNotEquals(null, tester.getEvents_list());
    }

    @Test
    public void testArraysAdapter()throws Exception{
        Assert.assertNotEquals(null, tester.getAdapter());

    }

    @Test
    public void testListView()throws Exception{
        Assert.assertNotEquals(null, tester.getListView());
    }
}
