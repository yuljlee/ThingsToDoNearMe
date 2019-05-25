package com.nanodegree.yj.thingstodonearme.sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.nanodegree.yj.thingstodonearme.model.EventContract;
import com.nanodegree.yj.thingstodonearme.utils.JsonUtils;
import com.nanodegree.yj.thingstodonearme.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by u2stay1915 on 3/10/18.
 */

public class SyncTask {

    synchronized public static void syncEvent(Context context, String cat, String location) {

        long epoch = System.currentTimeMillis()/1000;
        String startTime = Long.toString(epoch);

        Log.d("current_time --> ", startTime);

        try {

            String jsonItem = NetworkUtils.fetchJsonArray(cat, location, startTime);
            if (jsonItem == null) {
                throw new JSONException("Invalid parsed item array");
            }
//
//            /////////////////////
//            JSONObject eventJson = new JSONObject(jsonItem);
//            //JSONObject locationJson = new JSONObject(jsonItem);
//            JSONArray eventArray = eventJson.getJSONArray("events");
//            //JSONArray locationArray = locationJson.getJSONArray("location");
//
//            //ContentValues[] movieContentValues = new ContentValues[moiveJsonStr.length()];
//            ContentValues[] eventContentValues = new ContentValues[eventArray.length()];
//            //ContentValues[] locationContentValues = new ContentValues[locationArray.length()];
//
//            String id;
//            String name;
//            String description;
//            String event_site_url;
//            String image_url;
//            String time_start;
//            String time_end;
//            long latitude;
//            long longitude;
//            JSONObject location;
//            String address1;
//            String display_address;
//
//            //ArrayList<Movie> movies = new ArrayList<>();
//
//            for (int i = 0; i < eventArray.length(); i++) {
//                JSONObject eachMoive = eventArray.getJSONObject(i);
//
//                id = eachMoive.getString("id");
//                name = eachMoive.getString("name");
//                description = eachMoive.getString("description");
//                event_site_url = eachMoive.getString("event_site_url");
//                image_url = eachMoive.getString("image_url");
//                time_start = eachMoive.getString("time_start");
//                time_end = eachMoive.getString("time_end");
//                latitude = eachMoive.getLong("latitude");
//                longitude = eachMoive.getLong("longitude");
//
//                location = eachMoive.getJSONObject("location");
//
//                //address1 = location.getString("address1");
//                address1 = location.getString("display_address");
//
//
//                //movies.add(new Movie(movieTitle, posterPath, releaseDate, voteAverage, overview));
//                ContentValues eventValues = new ContentValues();
//                eventValues.put(EventContract.EventEntry.COLUMN_ID, id);
//                eventValues.put(EventContract.EventEntry.COLUMN_NAME, name);
//                eventValues.put(EventContract.EventEntry.COLUMN_DESC, description);
//                eventValues.put(EventContract.EventEntry.COLUMN_SITE_URL, event_site_url);
//                eventValues.put(EventContract.EventEntry.COLUMN_IMAGE_URL, image_url);
//                eventValues.put(EventContract.EventEntry.COLUMN_TIME_START, time_start);
//                eventValues.put(EventContract.EventEntry.COLUMN_TIME_END, time_end);
//                eventValues.put(EventContract.EventEntry.COLUMN_LATITUDE, latitude);
//                eventValues.put(EventContract.EventEntry.COLUMN_LONGITUDE, longitude);
//                eventValues.put(EventContract.EventEntry.COLUMN_ADDRESS1, address1);
//
//                eventContentValues[i] = eventValues;
//
//            }
//
//            if (eventContentValues != null && eventContentValues.length != 0) {
//
//                ContentResolver eventContentResolver = context.getContentResolver();
//
//                eventContentResolver.delete(
//                        EventContract.EventEntry.CONTENT_URI,
//                        null,
//                        null);
//
//                eventContentResolver.bulkInsert(
//                        EventContract.EventEntry.CONTENT_URI,
//                        eventContentValues);
//            }
//
//
//
//            /////////////////////

            Log.v("data:", jsonItem);

            ContentValues[] eventValues = JsonUtils
                    .getEventContentValuesFromJson(context, jsonItem);

            String[] selecttionArgs = {cat};

            ContentResolver eventContentResolver = context.getContentResolver();

            eventContentResolver.delete(
                    EventContract.EventEntry.CONTENT_URI,
                    EventContract.EventEntry.COLUMN_CATEGORY + " = ?",
                    selecttionArgs);

            if (eventValues != null && eventValues.length != 0) {

                eventContentResolver.bulkInsert(
                        EventContract.EventEntry.CONTENT_URI,
                        eventValues);
            }


        } catch (Exception e) {
            /* Server probably invalid */
            e.printStackTrace();
        }
    }
}
