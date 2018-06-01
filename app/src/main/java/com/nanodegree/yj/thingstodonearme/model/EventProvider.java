package com.nanodegree.yj.thingstodonearme.model;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by u2stay1915 on 3/11/18.
 */

public class EventProvider extends ContentProvider {

    public static final int CODE_EVENT = 100;
    public static final int CODE_EVENT_WITH_ID = 101;
    public static final int CODE_LOCATION = 200;
    public static final int CODE_LOCATION_WITH_ID = 201;


    private static final UriMatcher sUriMatcher = buildUriMatcher();


    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = EventContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, EventContract.PATH_EVENT, CODE_EVENT);

        matcher.addURI(authority, EventContract.PATH_EVENT + "/#", CODE_EVENT_WITH_ID);

        return matcher;
    }

    private EventDbHelper mOpenHelper;

    @Override
    public boolean onCreate() {
        mOpenHelper = new EventDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor;
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        selection = "category = ?";

        switch (sUriMatcher.match(uri)) {

            case CODE_EVENT_WITH_ID: {

                String normalizedUtcDateString = uri.getLastPathSegment();

                String lastPathSegment = uri.getLastPathSegment();

                String[] selectionArguments = new String[]{lastPathSegment};

                //Log.v("SelectionArg -> ", selectionArgs[0]);

                cursor = mOpenHelper.getReadableDatabase().query(

                        EventContract.EventEntry.TABLE_NAME,
                        projection,

                        EventContract.EventEntry._ID + " = ? ",
                        selectionArguments,
                        null,
                        null,
                        sortOrder);

                break;
            }

            case CODE_EVENT: {
//
//                //This is an left join which looks like
//                //weather LEFT JOIN location ON event.event_id = location.event_id
//                queryBuilder.setTables(EventContract.EventEntry.TABLE_NAME + " LEFT JOIN " +
//                        EventContract.LocationEntry.TABLE_NAME + " ON " +
//                        EventContract.EventEntry.TABLE_NAME + "." + EventContract.EventEntry.COLUMN_ID + " = " +
//                        EventContract.LocationEntry.TABLE_NAME + "." + EventContract.LocationEntry.COLUMN_ID);
//
//                cursor = queryBuilder.query(
//                        mOpenHelper.getReadableDatabase(),
//                        projection,
//                        selection,
//                        selectionArgs,
//                        null,
//                        null,
//                        sortOrder);

                cursor = mOpenHelper.getReadableDatabase().query(
                        EventContract.EventEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                break;
            }


            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        switch (sUriMatcher.match(uri)) {

            case CODE_EVENT:
                db.beginTransaction();
                int rowsInserted = 0;
                try {
                    for (ContentValues value : values) {

                        long _id = db.insert(EventContract.EventEntry.TABLE_NAME, null, value);
                        if (_id != -1) {
                            rowsInserted++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }

                if (rowsInserted > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                    int contentLength = values.length;
                    Log.v("Provider Insert -> ", String.valueOf(rowsInserted));
                    Log.v("contentLength -> ", String.valueOf(contentLength));
                }

                return rowsInserted;

            case CODE_LOCATION:
                db.beginTransaction();
                int rowsInserted2 = 0;
                try {
                    for (ContentValues value : values) {

                        long _id = db.insert(EventContract.LocationEntry.TABLE_NAME, null, value);
                        if (_id != -1) {
                            rowsInserted2++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }

                if (rowsInserted2 > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                    int contentLength = values.length;
                    Log.v("Provider Insert -> ", String.valueOf(rowsInserted2));
                    Log.v("contentLength -> ", String.valueOf(contentLength));
                }

                return rowsInserted2;

            default:
                return super.bulkInsert(uri, values);
        }
    }


    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int numRowsDeleted;

        //selection = "category = ?";

        if (null == selection) selection = "1";

        switch (sUriMatcher.match(uri)) {

            case CODE_EVENT:
                numRowsDeleted = mOpenHelper.getWritableDatabase().delete(
                        EventContract.EventEntry.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;

            case CODE_LOCATION:
                numRowsDeleted = mOpenHelper.getWritableDatabase().delete(
                        EventContract.LocationEntry.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        if (numRowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numRowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
