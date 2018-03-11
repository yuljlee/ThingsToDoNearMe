package com.nanodegree.yj.thingstodonearme.utils;

import android.content.ContentValues;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by u2stay1915 on 3/10/18.
 */

public final class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();
    public static ContentValues[] getMovieContentValuesFromJson(Context context, String JsonStr)
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

            //movies.add(new Movie(movieTitle, posterPath, releaseDate, voteAverage, overview));
            ContentValues movieValues = new ContentValues();
//            movieValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_ID, movieId);
//            movieValues.put(MovieContract.MovieEntry.COLUMN_TITLE, movieTitle);
//            movieValues.put(MovieContract.MovieEntry.COLUMN_POSTER_URL, posterPath);
//            movieValues.put(MovieContract.MovieEntry.COLUMN_RELEASE_DATE, releaseDate);
//            movieValues.put(MovieContract.MovieEntry.COLUMN_AVERAGE, voteAverage);
//            movieValues.put(MovieContract.MovieEntry.COLUMN_OVERVIEW, overview);
//            movieValues.put(MovieContract.MovieEntry.COLUMN_VIDEO_URL, "video");
//            movieValues.put(MovieContract.MovieEntry.COLUMN_REVIEW_URL, "review");

            eventContentValues[i] = movieValues;
        }

        return eventContentValues;
    }

}
