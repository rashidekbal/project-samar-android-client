package com.rtechnologies.samar.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {
    public static String getTimeStamp(){
        return LocalDateTime.now().toString();
    }
    public static Date getDateObject(String timeStamp){
        if(timeStamp.contains(" ")){
           timeStamp= timeStamp.replace(" ","T");
        }
        return Date.from(LocalDateTime.parse(timeStamp).atZone(ZoneId.systemDefault()).toInstant());
    }
    public static String getTimeDiff(Date date){
        Date currentDate=new Date();
        long diff=currentDate.getTime()-date.getTime();
        int min=(int)(diff/(1000*60));
        if(min==0){return "just now";}
        if(min<60){
            return min+" minutes ago";

        }
        int hours= min/60;
        if(hours>24){
            int days= hours/24;
            return days+" day's ago";
        }
        return hours+" hours ago";


    }
    public static String getMessageTime(String timeStamp){
        String d=timeStamp;
        if(d.contains(" ")){
            d=d.split(" ")[1];
        }else{
            d=d.split("T")[1];
        }
        d=d.split("\\.")[0];
        return (d.split(":")[0])+":"+(d.split(":")[1]);

    }
}
