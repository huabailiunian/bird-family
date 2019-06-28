package com.bird.core.tools;

import com.bird.core.consts.BirdConst;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author youly
 * 2018/10/31 11:10
 */
public class DateTools {

    public static String formatDate(Date date) {
        return DateFormatUtils.format(date, BirdConst.FORMAT_DATE);
    }

    public static String formatDate(long millis) {
        return DateFormatUtils.format(millis, BirdConst.FORMAT_DATE);
    }

    public static String formatTime(Date date) {
        return DateFormatUtils.format(date, BirdConst.FORMAT_TIME);
    }

    public static String formatTime(long millis) {
        return DateFormatUtils.format(millis, BirdConst.FORMAT_TIME);
    }

    public static String formatDateTime(long millis) {
        return DateFormatUtils.format(millis, BirdConst.FORMAT_DATETIME);
    }

    public static String formatTimestamp(long millis) {
        return DateFormatUtils.format(millis, BirdConst.FORMAT_DATETIME);
    }

    public static Date parseTimestamp(String source) throws ParseException {
        return DateUtils.parseDate(source, BirdConst.FORMAT_TIMESTAMP);
    }

    public static Date parseDateTime(String source) throws ParseException {
        return DateUtils.parseDate(source, BirdConst.FORMAT_DATETIME);
    }

    public static Date parseDate(String source) throws ParseException {
        return DateUtils.parseDate(source, BirdConst.FORMAT_DATE);
    }

    public static Date startOfDay(Date date) {
        return startOfDay(date.getTime());
    }

    public static Date startOfDay(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date endOfDay(Date date) {
        return endOfDay(date.getTime());
    }

    public static Date endOfDay(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

}
