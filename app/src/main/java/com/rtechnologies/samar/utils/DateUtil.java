package com.rtechnologies.samar.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {
    public static String getTimeStamp(){
        return LocalDateTime.now().toString();
    }
    public static Date getDateObject(String timeStamp){
        return Date.from(LocalDateTime.parse(timeStamp).atZone(ZoneId.systemDefault()).toInstant());
    }
}
