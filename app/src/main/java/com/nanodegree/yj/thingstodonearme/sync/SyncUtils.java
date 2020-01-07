package com.nanodegree.yj.thingstodonearme.sync;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;

/**
 * Created by u2stay1915 on 3/10/18.
 */

public class SyncUtils {

    public static void startImmediateSync(@NonNull final Context context, String cat, String location) {
        Intent intentToSyncImmediately = new Intent(context, SyncIntentService.class);
        intentToSyncImmediately.putExtra("cat", cat);
        intentToSyncImmediately.putExtra("location", location);
        context.startService(intentToSyncImmediately);
    }
}
