package com.nanodegree.yj.thingstodonearme.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;

import com.nanodegree.yj.thingstodonearme.model.EventContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by u2stay1915 on 3/10/18.
 */

public final class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    public static ContentValues[] getEventContentValuesFromJson(Context context, String JsonStr)
            throws JSONException {

        JSONObject eventJson = new JSONObject(JsonStr);
        JSONArray eventArray = eventJson.getJSONArray("events");

        //ContentValues[] movieContentValues = new ContentValues[moiveJsonStr.length()];
        ContentValues[] eventContentValues = new ContentValues[eventArray.length()];

        String id;
        String name;
        String description;
        String event_site_url;
        String image_url;
        String time_start;
        String time_end;
        long latitude;
        long longitude;

        //ArrayList<Movie> movies = new ArrayList<>();

        for (int i = 0; i < eventArray.length(); i++) {
            JSONObject eachMoive = eventArray.getJSONObject(i);

            id = eachMoive.getString("id");
            name = eachMoive.getString("name");
            description = eachMoive.getString("description");
            event_site_url = eachMoive.getString("event_site_url");
            image_url = eachMoive.getString("image_url");
            time_start = eachMoive.getString("time_start");
            time_end = eachMoive.getString("time_end");
            latitude = eachMoive.getLong("latitude");
            longitude = eachMoive.getLong("longitude");

            //movies.add(new Movie(movieTitle, posterPath, releaseDate, voteAverage, overview));
            ContentValues eventValues = new ContentValues();
            eventValues.put(EventContract.EventEntry.COLUMN_ID, id);
            eventValues.put(EventContract.EventEntry.COLUMN_NAME, name);
            eventValues.put(EventContract.EventEntry.COLUMN_DESC, description);
            eventValues.put(EventContract.EventEntry.COLUMN_SITE_URL, event_site_url);
            eventValues.put(EventContract.EventEntry.COLUMN_IMAGE_URL, image_url);
            eventValues.put(EventContract.EventEntry.COLUMN_TIME_START, time_start);
            eventValues.put(EventContract.EventEntry.COLUMN_TIME_END, time_end);
            eventValues.put(EventContract.EventEntry.COLUMN_LATITUDE, latitude);
            eventValues.put(EventContract.EventEntry.COLUMN_LONGITUDE, longitude);

            eventContentValues[i] = eventValues;
        }

        return eventContentValues;
    }

    public static ContentValues[] getLcationContentValuesFromJson(Context context, String JsonStr)
            throws JSONException {

        JSONObject eventJson = new JSONObject(JsonStr);
        JSONArray eventArray = eventJson.getJSONArray("location");

        //ContentValues[] movieContentValues = new ContentValues[moiveJsonStr.length()];
        ContentValues[] eventContentValues = new ContentValues[eventArray.length()];

        String id;
        String name;
        String description;
        String event_site_url;
        String image_url;
        String time_start;
        String time_end;
        long latitude;
        long longitude;

        //ArrayList<Movie> movies = new ArrayList<>();

        for (int i = 0; i < eventArray.length(); i++) {
            JSONObject eachMoive = eventArray.getJSONObject(i);

            id = eachMoive.getString("id");
            name = eachMoive.getString("name");
            description = eachMoive.getString("description");
            event_site_url = eachMoive.getString("event_site_url");
            image_url = eachMoive.getString("image_url");
            time_start = eachMoive.getString("time_start");
            time_end = eachMoive.getString("time_end");
            latitude = eachMoive.getLong("latitude");
            longitude = eachMoive.getLong("longitude");

            //movies.add(new Movie(movieTitle, posterPath, releaseDate, voteAverage, overview));
            ContentValues eventValues = new ContentValues();
            eventValues.put(EventContract.EventEntry.COLUMN_ID, id);
            eventValues.put(EventContract.EventEntry.COLUMN_NAME, name);
            eventValues.put(EventContract.EventEntry.COLUMN_DESC, description);
            eventValues.put(EventContract.EventEntry.COLUMN_SITE_URL, event_site_url);
            eventValues.put(EventContract.EventEntry.COLUMN_IMAGE_URL, image_url);
            eventValues.put(EventContract.EventEntry.COLUMN_TIME_START, time_start);
            eventValues.put(EventContract.EventEntry.COLUMN_TIME_END, time_end);
            eventValues.put(EventContract.EventEntry.COLUMN_LATITUDE, latitude);
            eventValues.put(EventContract.EventEntry.COLUMN_LONGITUDE, longitude);

            eventContentValues[i] = eventValues;
        }

        return eventContentValues;
    }

    public static void setEventContentValuesFromJson(Context context, String JsonStr)
            throws JSONException {

        JSONObject eventJson = new JSONObject(JsonStr);
        JSONArray eventArray = eventJson.getJSONArray("events");

        //ContentValues[] movieContentValues = new ContentValues[moiveJsonStr.length()];
        ContentValues[] eventContentValues = new ContentValues[eventArray.length()];

        String id;
        String name;
        String description;
        String event_site_url;
        String image_url;
        String time_start;
        String time_end;
        long latitude;
        long longitude;

        //ArrayList<Movie> movies = new ArrayList<>();

        for (int i = 0; i < eventArray.length(); i++) {
            JSONObject eachMoive = eventArray.getJSONObject(i);

            id = eachMoive.getString("id");
            name = eachMoive.getString("name");
            description = eachMoive.getString("description");
            event_site_url = eachMoive.getString("event_site_url");
            image_url = eachMoive.getString("image_url");
            time_start = eachMoive.getString("time_start");
            time_end = eachMoive.getString("time_end");
            latitude = eachMoive.getLong("latitude");
            longitude = eachMoive.getLong("longitude");

            //movies.add(new Movie(movieTitle, posterPath, releaseDate, voteAverage, overview));
            ContentValues eventValues = new ContentValues();
            eventValues.put(EventContract.EventEntry.COLUMN_ID, id);
            eventValues.put(EventContract.EventEntry.COLUMN_NAME, name);
            eventValues.put(EventContract.EventEntry.COLUMN_DESC, description);
            eventValues.put(EventContract.EventEntry.COLUMN_SITE_URL, event_site_url);
            eventValues.put(EventContract.EventEntry.COLUMN_IMAGE_URL, image_url);
            eventValues.put(EventContract.EventEntry.COLUMN_TIME_START, time_start);
            eventValues.put(EventContract.EventEntry.COLUMN_TIME_END, time_end);
            eventValues.put(EventContract.EventEntry.COLUMN_LATITUDE, latitude);
            eventValues.put(EventContract.EventEntry.COLUMN_LONGITUDE, longitude);

            eventContentValues[i] = eventValues;
        }

        if (eventContentValues != null && eventContentValues.length != 0) {

            ContentResolver eventContentResolver = context.getContentResolver();

            eventContentResolver.delete(
                    EventContract.EventEntry.CONTENT_URI,
                    null,
                    null);

            eventContentResolver.bulkInsert(
                    EventContract.EventEntry.CONTENT_URI,
                    eventContentValues);
        }

        //return eventContentValues;
    }

}
