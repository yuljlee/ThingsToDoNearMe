package com.nanodegree.yj.thingstodonearme.ui;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.TaskStackBuilder;
import android.widget.RemoteViews;

import com.nanodegree.yj.thingstodonearme.R;
import com.nanodegree.yj.thingstodonearme.model.EventWidgetService;

/**
 * Implementation of App Widget functionality.
 */
public class EventAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.event_app_widget);
        //views.setTextViewText(R.id.appwidget_text, widgetText);

        setRemoteAdapter(context, views);

        // template to handle the click listener for each item
        Intent clickIntentTemplate = new Intent(context, DetailActivity.class);
        PendingIntent clickPendingIntentTemplate = TaskStackBuilder.create(context)
                .addNextIntentWithParentStack(clickIntentTemplate)
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widget_listview, clickPendingIntentTemplate);

        /** PendingIntent to launch the MainActivity when the widget was clicked **/
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.info, pendingIntent);

        //Intent intentSearch = new Intent(context, SearchActivity.class);
        //PendingIntent pendingIntentSearch = PendingIntent.getActivity(context, 0, intentSearch, 0);
        //views.setOnClickPendingIntent(R.id.search_button, pendingIntentSearch);


        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_listview);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
//        final String action = intent.getAction();
//        if (action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
//            // refresh all your widgets
//            AppWidgetManager mgr = AppWidgetManager.getInstance(context);
//            ComponentName cn = new ComponentName(context, EventAppWidget.class);
//            mgr.notifyAppWidgetViewDataChanged(mgr.getAppWidgetIds(cn), R.id.widget_listview);
//        }
        super.onReceive(context, intent);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


    /** Set the Adapter for out widget **/

    //@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private static void setRemoteAdapter(Context context, @NonNull final RemoteViews views) {
        views.setRemoteAdapter(R.id.widget_listview,
                new Intent(context, EventWidgetService.class));
    }

}

