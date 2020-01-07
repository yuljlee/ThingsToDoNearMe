package com.nanodegree.yj.thingstodonearme.sync;

import android.app.IntentService;
import android.content.Intent;
import androidx.annotation.Nullable;

/**
 * Created by u2stay1915 on 3/10/18.
 */

public class SyncIntentService extends IntentService {

    public SyncIntentService() {super("SyncIntentService");}

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String cat = intent.getStringExtra("cat");
        String location = intent.getStringExtra("location");
        SyncTask.syncEvent(this, cat, location);
    }
}
