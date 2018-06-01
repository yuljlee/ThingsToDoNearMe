package com.nanodegree.yj.thingstodonearme.model;

import com.nanodegree.yj.thingstodonearme.BuildConfig;

/**
 * Created by u2stay1915 on 3/10/18.
 */

public class Constant {
    public static final String YELP_TOKEN = BuildConfig.YELP_TOKEN;
    public static final String YELP_BASE_URL = "https://api.yelp.com/v3/events";
    public static final String YELP_CATEGORY_QUERY_PARAMETER = "categories";
    public static final String YELP_LOCATION_QUERY_PARAMETER = "location";
    public static final String YELP_LIMIT_QUERY_PARAMETER = "limit";
    public static final String YELP_SORT_QUERY_PARAMETER = "sort_on";
    public static final String YELP_STARTDATE_QUERY_PARAMETER = "start_date";

    public static final String LOCATION_KEY = "location_key";
    public static final String DEFAULT_LOCATION = "Los Angeles, CA";

    //    public static final String[] CATEGORY = {"music",
//            Visual Arts (visual-arts)
//            Performing Arts (performing-arts)
//            Film (film)
//            Lectures & Books (lectures-books)
//            Fashion (fashion)
//            Food & Drink (food-and-drink)
//            Festivals & Fairs (festivals-fairs)
//            Charities (charities)
//            Sports & Active Life (sports-active-life)
//            Nightlife (nightlife)
//            Kids & Family (kids-family)
//            Other (other)};
}
