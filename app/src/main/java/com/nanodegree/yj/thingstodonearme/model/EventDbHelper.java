package com.nanodegree.yj.thingstodonearme.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by u2stay1915 on 3/11/18.
 */

public class EventDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "event.db";

    private static final int DATABASE_VERSION = 1;

    public EventDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_EVENT_TABLE =

                "CREATE TABLE " + EventContract.EventEntry.TABLE_NAME + " (" +
                        EventContract.EventEntry._ID                + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        EventContract.EventEntry.COLUMN_ID          + " TEXT NOT NULL, "                    +
                        EventContract.EventEntry.COLUMN_NAME        + " TEXT NOT NULL, "                 +
                        EventContract.EventEntry.COLUMN_DESC        + " TEXT NOT NULL,"                  +
                        EventContract.EventEntry.COLUMN_SITE_URL    + " INTEGER NOT NULL, "                    +
                        EventContract.EventEntry.COLUMN_IMAGE_URL   + " TEXT NOT NULL, "                    +
                        EventContract.EventEntry.COLUMN_TIME_START  + " TEXT NOT NULL, "                    +
                        EventContract.EventEntry.COLUMN_TIME_END    + " TEXT NOT NULL "                    +
                        " );";

        db.execSQL(SQL_CREATE_EVENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // event table
        db.execSQL("DROP TABLE IF EXISTS " + EventContract.EventEntry.TABLE_NAME);

        onCreate(db);
    }

}
