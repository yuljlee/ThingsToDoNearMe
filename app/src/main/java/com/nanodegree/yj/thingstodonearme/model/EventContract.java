package com.nanodegree.yj.thingstodonearme.model;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by u2stay1915 on 3/11/18.
 */

public class EventContract {


    public static final String CONTENT_AUTHORITY = "com.nanodegree.yj.thingstodonearme";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_EVENT = "event";
//    public static final String PATH_FAVORTIE_MOVIE = "favorite_movie";
//    public static final String PATH_TRAILER = "trailer";
//    public static final String PATH_REVIEW = "review";

    public static final class EventEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_EVENT)
                .build();

        public static final String TABLE_NAME = "event";

        public static final String COLUMN_ID = "id";

        public static final String COLUMN_NAME = "name";

        public static final String COLUMN_DESC = "description";

        public static final String COLUMN_SITE_URL = "event_site_url";

        public static final String COLUMN_IMAGE_URL = "image_url";

        public static final String COLUMN_TIME_START = "time_start";

        public static final String COLUMN_TIME_END = "time_end";

        public static Uri buildEventUriWithId(int indexId) {
            return CONTENT_URI.buildUpon()
                    .appendPath(Integer.toString(indexId))
                    .build();
        }
    }
}
