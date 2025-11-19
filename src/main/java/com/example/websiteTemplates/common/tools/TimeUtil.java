package com.example.websiteTemplates.common.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static Date getCurrentTime() {
        Date date = new Date(System.currentTimeMillis());
        return date;
    }

    public static String timeFormat(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
}
