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

import java.net.URL;

/**
 * Created by u2stay1915 on 3/10/18.
 */

public class SyncTask {

    //synchronized public static void syncMovie(Context context, String sortBy) {
    synchronized public static void syncEvent(Context context) {
        try {


            //String sort = "popular";
            //String sort = "rated";
            URL movieRequestUrl = NetworkUtils.buildUrl();

            //Log.v("url", );

            //String jsonMovieResponse = NetworkUtils.getResponseFromHttpUrl(movieRequestUrl);
            //String jsonMovieResponse = NetworkUtils.fetchJsonArray();

            String jsonItem = NetworkUtils.fetchJsonArray();
//            if (jsonItem == null) {
//                throw new JSONException("Invalid parsed item array" );
//            }

            Log.v("data:", jsonItem);

            ContentValues[] eventValues = JsonUtils
                    .getEventContentValuesFromJson(context, jsonItem);

            if (eventValues != null && eventValues.length != 0) {

                ContentResolver eventContentResolver = context.getContentResolver();

                eventContentResolver.delete(
                        EventContract.EventEntry.CONTENT_URI,
                        null,
                        null);

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
