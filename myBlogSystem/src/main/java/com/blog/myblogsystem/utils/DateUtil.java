package com.blog.myblogsystem.utils;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final String FORMATTER_MINUTE = "yyyy-MM-dd HH:mm";
    public static final String FORMATTER_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMATTER_MILLISECOND = "yyyy-MM-dd HH:mm:ss.SSS";


    public static Timestamp getCurrentTime(){
        return new Timestamp(System.currentTimeMillis());
    }

    public static Date parseDate(String dateTimeStr, String formatter) throws ParseException {
        Date dateTime = null;
        if (StringUtils.isNotBlank(dateTimeStr)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
            dateTime = simpleDateFormat.parse(dateTimeStr);
        }
        return dateTime;
    }
}
