package com.epam.engx.cleancode.functions.task5;

import java.util.Calendar;
import java.util.Date;

public final class DateUtil {

    private DateUtil() {
    }

    public static Date changeToMidnightAndIncrement(Date date) {
        Calendar calendar = changeToMidnight(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    public static Date changeToMidnightAndReverse(Date date) {
        Calendar calendar = changeToMidnight(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    public static Calendar changeToMidnight(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

}
