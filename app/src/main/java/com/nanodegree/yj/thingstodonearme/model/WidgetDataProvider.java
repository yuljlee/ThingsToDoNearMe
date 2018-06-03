package com.nanodegree.yj.thingstodonearme.model;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Binder;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.nanodegree.yj.thingstodonearme.R;
import com.nanodegree.yj.thingstodonearme.ui.EventAppWidget;

/**
 * Created by u2stay1915 on 6/1/18.
 */

public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {
    private static final String TAG = WidgetDataProvider.class.getSimpleName();

    private Context context;
    private Cursor cursor;
    private Intent intent;

    //For obtaining the activity's context and intent
    public WidgetDataProvider(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
    }

    private void initCursor() {
        if (cursor != null) {
            cursor.close();
        }

        final long identityToken = Binder.clearCallingIdentity();

        cursor = context.getContentResolver().query(EventContract.EventEntry.CONTENT_URI,
                new String[]{EventContract.EventEntry._ID, EventContract.EventEntry.COLUMN_CATEGORY,
                        EventContract.EventEntry.COLUMN_NAME, EventContract.EventEntry.COLUMN_TIME_START},
                EventContract.EventEntry.COLUMN_CATEGORY + " = ?",
                new String[]{"music"},null);

        Binder.restoreCallingIdentity(identityToken);
    }

    @Override
    public void onCreate() {
        initCursor();

        if (cursor != null) {
            cursor.moveToFirst();
        }
    }

    @Override
    public void onDataSetChanged() {
        initCursor();
    }

    @Override
    public void onDestroy() {
        if (cursor != null) {
            cursor.close();
        }
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Log.d(TAG, "getViewAt --> " + position);

        if (position == AdapterView.INVALID_POSITION ||
                cursor == null || !cursor.moveToPosition(position)) {
            return null;
        }

        /** Populate your widget's single list item **/
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_item);

        cursor.moveToPosition(position);
        remoteViews.setTextViewText(R.id.name_text_view, cursor.getString(cursor.getColumnIndex(EventContract.EventEntry.COLUMN_NAME)));

        Intent fillInIntent = new Intent();
        Uri uriEventClicked = EventContract.EventEntry.buildEventUriWithId(cursor.getInt(cursor.getColumnIndex(EventContract.EventEntry._ID)));
        fillInIntent.putExtra("event_uri", uriEventClicked);
        //remoteViews.setOnClickFillInIntent(R.id.widget_item_container, fillInIntent);

        Log.d(TAG, "uriEventClicked --> " + uriEventClicked.toString());

        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
