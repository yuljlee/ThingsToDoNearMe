package com.nanodegree.yj.thingstodonearme.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by u2stay1915 on 3/11/18.
 */

public class EventDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "eventDB.db";

    private static final int DATABASE_VERSION = 3;

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
                        EventContract.EventEntry.COLUMN_TIME_END    + " TEXT NOT NULL, "                    +
                        EventContract.EventEntry.COLUMN_LATITUDE    + " LONG NOT NULL, "                    +
                        EventContract.EventEntry.COLUMN_LONGITUDE   + " LONG NOT NULL "                    +
                        " );";

        db.execSQL(SQL_CREATE_EVENT_TABLE);

        final String SQL_CREATE_LOCATION_TABLE =

                "CREATE TABLE " + EventContract.LocationEntry.TABLE_NAME + " (" +
                        EventContract.LocationEntry._ID                 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        EventContract.LocationEntry.COLUMN_ID           + " TEXT NOT NULL, "                    +
                        EventContract.LocationEntry.COLUMN_ADDRESS1     + " TEXT NOT NULL, "                 +
                        EventContract.LocationEntry.COLUMN_ADDRESS2     + " TEXT NOT NULL,"                  +
                        EventContract.LocationEntry.COLUMN_ADDRESS3     + " INTEGER NOT NULL, "                    +
                        EventContract.LocationEntry.COLUMN_CITY         + " TEXT NOT NULL, "                    +
                        EventContract.LocationEntry.COLUMN_ZIP_CODE     + " TEXT NOT NULL, "                    +
                        EventContract.LocationEntry.COLUMN_COUNTRY      + " TEXT NOT NULL, "                    +
                        EventContract.LocationEntry.COLUMN_STATE        + " TEXT NOT NULL, "                    +
                        EventContract.LocationEntry.COLUMN_DISPLAY_ADDRESS  + " TEXT NOT NULL, "                    +
                        EventContract.LocationEntry.COLUMN_CROSS_STREETS    + " TEXT NOT NULL "                    +
                        " );";

        db.execSQL(SQL_CREATE_LOCATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // event table
        db.execSQL("DROP TABLE IF EXISTS " + EventContract.EventEntry.TABLE_NAME);
        // location table
        db.execSQL("DROP TABLE IF EXISTS " + EventContract.LocationEntry.TABLE_NAME);

        onCreate(db);
    }

}
