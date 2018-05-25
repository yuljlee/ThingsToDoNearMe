package com.nanodegree.yj.thingstodonearme.utils;

import android.net.ParseException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by u2stay1915 on 5/22/18.
 */

public final class CommonUtils {

    private static final String TAG = CommonUtils.class.getSimpleName();

    public static String reformatDate(String dateIn) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Calendar calendar = Calendar.getInstance();

        Date date = null;
        try {
            date = simpleDateFormat.parse(dateIn);
            calendar.setTime(date);
            calendar.add(Calendar.HOUR, -7); // I don't know why?

            simpleDateFormat = new SimpleDateFormat("EEE, MMM d, yyyy, h:mm a"); // Sat, Mar 24, 2018, 4:00 PM
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return simpleDateFormat.format(calendar.getTime());
    }

    public static String convertAddress(String address) {
        //String newAddress;
        address = address.replace("\"", "");
        address = address.replace("[", "");
        address = address.replace("]", "");
        address = address.replace(",", "");

        return address;
    }
}
