package com.labs.myweather;

import android.icu.text.SimpleDateFormat;

import java.util.Date;

public class ParsDayPos {
    static String ParsCurrentDayPos(String time) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");;
        Date date;
        int hh = 8;
        try {
            date = format.parse(time);
            hh = date.getHours();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 6-9-12 - утро, 12-15-18 - день, 18-21-0 - вечер, 0-3-6 - ночь
        if(hh >= 6 && hh < 12) return "Утро";
        if(hh >= 12 && hh < 18) return "День";
        if(hh >= 18 && hh <= 23) return "Вечер";
        if(hh >= 0 && hh < 6) return "Ночь";
        return "error";
    }

    static String ParsDayPos(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;
        Date date;
        int hh = 25;
        try {
            date = format.parse(time);
            hh = date.getHours();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 6-9-12 - утро, 12-15-18 - день, 18-21-0 - вечер, 0-3-6 - ночь
        if(hh == 0) return "Ночь+1";
        if(hh == 3) return "Ночь+2";
        if(hh == 6) return "Утро+1";
        if(hh == 9) return "Утро+2";
        if(hh == 12) return "День+1";
        if(hh == 15) return "День+2";
        if(hh == 18) return "Вечер+1";
        if(hh == 21) return "Вечер+2";

        return "error";
    }
}
