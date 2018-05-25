package com.nanodegree.yj.thingstodonearme.utils;

import android.net.Uri;
import android.util.Log;

import com.nanodegree.yj.thingstodonearme.BuildConfig;
import com.nanodegree.yj.thingstodonearme.model.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by u2stay1915 on 3/10/18.
 */

public final class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    public static URL buildUrl(String category) {

        Uri builtUri = Uri.parse(Constant.YELP_BASE_URL).buildUpon()
                .appendQueryParameter(Constant.YELP_CATEGORY_QUERY_PARAMETER, category)
                //.appendQueryParameter(Constant.YELP_CATEGORY_QUERY_PARAMETER, "food-and-drink")
                .appendQueryParameter(Constant.YELP_LOCATION_QUERY_PARAMETER, "los angeles, CA")
                //.appendQueryParameter(Constant.YELP_LOCATION_QUERY_PARAMETER, "san francisco, CA")
                .appendQueryParameter(Constant.YELP_LIMIT_QUERY_PARAMETER, "10")
                .appendQueryParameter(Constant.YELP_SORT_QUERY_PARAMETER, "popularity")
                .appendQueryParameter(Constant.YELP_STARTDATE_QUERY_PARAMETER, "1527013169")
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Built URL " + url);

        return url;
    }

    public static String fetchJsonArray(String cat) {
        String itemsJson = null;
        try {
            itemsJson = fetchPlainText(buildUrl(cat));
        } catch (IOException e) {
            Log.e(TAG, "Error fetching items JSON", e);
            return null;
        }

//        // Parse JSON
//        try {
//            JSONTokener tokener = new JSONTokener(itemsJson);
//            Object val = tokener.nextValue();
//            if (!(val instanceof JSONArray)) {
//                throw new JSONException("Expected JSONArray");
//            }
//            return (JSONArray) val;
//        } catch (JSONException e) {
//            Log.e(TAG, "Error parsing items JSON", e);
//        }

        return itemsJson;
    }

    static String fetchPlainText(URL url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Constant.YELP_TOKEN)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
