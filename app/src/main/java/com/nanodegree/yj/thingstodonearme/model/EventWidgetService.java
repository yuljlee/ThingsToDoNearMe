package com.nanodegree.yj.thingstodonearme.model;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by u2stay1915 on 6/1/18.
 */

public class EventWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetDataProvider(this.getApplicationContext(), intent);
    }
}
