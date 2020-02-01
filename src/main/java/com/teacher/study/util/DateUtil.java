package com.teacher.study.util;

import java.util.Calendar;

public class DateUtil {
    public static String getTime(){
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        /*int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);*/
        //System.out.println(year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second);
        return year + "/" + (month+1) + "/" + date;
    }

}
