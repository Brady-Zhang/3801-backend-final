package com.example.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class ApiUtils {
    public static String baseUrl = "http://192.168.0.139:9005/image/";
    public static String getCurrentTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        String time = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return time;
    }
    public static String rangeSix(){
        StringBuffer sb = new StringBuffer();
        for (int i =0;i<5;i++){
            sb.append(new Random().nextInt(9)+"");
        }
        return sb.toString();
    }

    public static long dateDiff(String endTime) {
        long strTime = 0;
        // Create a SimpleDateFormat object according to the provided format
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long nd = 1000 * 24 * 60 * 60; // Number of milliseconds in a day
        long nh = 1000 * 60 * 60;      // Number of milliseconds in an hour
        long nm = 1000 * 60;           // Number of milliseconds in a minute
        long ns = 1000;                // Number of milliseconds in a second
        long diff;
        long day = 0;
        Date curDate = new Date(System.currentTimeMillis());//get current time
        String nowtime = simpleDateFormat.format(curDate);
        try {
            // Get the time difference in milliseconds between two times
            diff = simpleDateFormat.parse(nowtime).getTime() - simpleDateFormat.parse(endTime).getTime();
            day = diff / nd; // Calculate how many days the difference represents
            long hour = diff % nd / nh;// Calculate how many hours the difference represents
            long min = diff % nd % nh / nm;// Calculate how many minutes the difference represents
            long sec = diff % nd % nh % nm / ns;// Calculate how many seconds the difference represents
            // Output the results
            strTime = sec;
            System.out.println("====="+strTime);
            return strTime;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return strTime;
    }
}
