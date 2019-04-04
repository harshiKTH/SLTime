package com.example.sltime.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateAndTime {

    Calendar calendar = Calendar.getInstance();
    java.util.Date date;
    String mydate;

    public String setDate(String mdate) {
        date = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh.mm");
        formatter.format(date);
        return mdate;
    }
}
