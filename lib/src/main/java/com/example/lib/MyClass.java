package com.example.lib;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MyClass {

    public static void main (String[] args) throws ParseException {
        System.out.println("Hello!");

//        Date fDate = fromISO8601UTC("2019-05-23");
//        System.out.println(fDate.toString());

//        String eDate = null;
//        try {
//            eDate = reformatDate("20190523");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(eDate);
//        SimpleDateFormat ISODateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
//        DateTimeFormatter parser2 = ISODateTimeFormat
//        String jtdate = "2010-01-01T12:00:00+01:00";
//        System.out.println(parser2.parseDateTime(jtdate));
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String string1 = "2001-07-04T12:08:56.235-0700";
        Date result1 = null;
        try {
            result1 = df1.parse(string1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //System.out.println(result1);
//
//        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
//        //DateFormat df3 = new SimpleDateFormat("EEE, MMM d, yyyy, h:mm a"); // Sat, Mar 24, 2018, 4:00 PM
//        //DateFormat df2 = new SimpleDateFormat("MMM d, yyyy, h:mm a"); // Sat, Mar 24, 2018, 4:00 PM
//        String string2 = "2019-05-23T20:00:00-07:00";
//        Date result2 = null;
//        //Date result3 = null;
//        try {
//            result2 = df2.parse(string2);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(result2.toString());


        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        //DateFormat df3 = new SimpleDateFormat("EEE, MMM d, yyyy, h:mm a"); // Sat, Mar 24, 2018, 4:00 PM
        //DateFormat df2 = new SimpleDateFormat("MMM d, yyyy, h:mm a"); // Sat, Mar 24, 2018, 4:00 PM

        //String oldString = "2019-05-23T20:00:00-07:00";
        String oldString = "2019-06-12T19:00:00-04:00";
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(oldString);
        String newString = new SimpleDateFormat("EEE, MMM d, yyyy, h:mm a").format(date);

//        Date result2 = null;
//        //Date result3 = null;
//        try {
//            result2 = df2.parse(string2);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        System.out.println(newString);
    }

    public static String toISO8601UTC(Date date) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);
        return df.format(date);
    }

    public static Date fromISO8601UTC(String dateStr) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);

        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

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
//
//    public class DateTest {
//        public static void main(String[] args) {
//            Date now = new Date();
//
//            //This is just Date's toString method and doesn't involve SimpleDateFormat
//            System.out.println("toString(): " + now);  // dow mon dd hh:mm:ss zzz yyyy
//            //Shows  "Mon Oct 08 08:17:06 EDT 2012"
//
//            SimpleDateFormat dateFormatter = new SimpleDateFormat("E, y-M-d 'at' h:m:s a z");
//            System.out.println("Format 1:   " + dateFormatter.format(now));
//            // Shows  "Mon, 2012-10-8 at 8:17:6 AM EDT"
//
//            dateFormatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
//            System.out.println("Format 2:   " + dateFormatter.format(now));
//            // Shows  "Mon 2012.10.08 at 08:17:06 AM EDT"
//
//            dateFormatter = new SimpleDateFormat("EEEE, MMMM d, yyyy");
//            System.out.println("Format 3:   " + dateFormatter.format(now));
//            // Shows  "Monday, October 8, 2012"
//
//            // SimpleDateFormat can be used to control the date/time display format:
//            //   E (day of week): 3E or fewer (in text xxx), >3E (in full text)
//            //   M (month): M (in number), MM (in number with leading zero)
//            //              3M: (in text xxx), >3M: (in full text full)
//            //   h (hour): h, hh (with leading zero)
//            //   m (minute)
//            //   s (second)
//            //   a (AM/PM)
//            //   H (hour in 0 to 23)
//            //   z (time zone)
//            //  (there may be more listed under the API - I didn't check)
//
//        }
//    }
}
