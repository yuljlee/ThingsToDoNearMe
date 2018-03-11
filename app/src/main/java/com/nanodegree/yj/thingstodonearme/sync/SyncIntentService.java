package com.nanodegree.yj.thingstodonearme.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by u2stay1915 on 3/10/18.
 */

public class SyncIntentService extends IntentService {

    public SyncIntentService() {super("SyncIntentService");}

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        SyncTask.syncEvent(this);
    }
}
