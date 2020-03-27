package com.mk.coronavirus.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeConverter {
    public static String zuluToGmtPlus3(final String zuluTime) {
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(zuluTime);
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, 3);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return zuluTime;
    }

    public static boolean has5minPassed(long time) {
        long currentTime = System.currentTimeMillis();
        long previousUpdateTime = time;
        long diff = currentTime - previousUpdateTime;
        return diff > 300000L;//5 mins
    }

    public static long currentTimeInMillis() {
        return System.currentTimeMillis();
    }
}
