package com.nanodegree.yj.thingstodonearme.sync;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * Created by u2stay1915 on 3/10/18.
 */

public class SyncUtils {

    public static void startImmediateSync(@NonNull final Context context, String sortBy) {
        Intent intentToSyncImmediately = new Intent(context, SyncIntentService.class);
        intentToSyncImmediately.putExtra("sortBy", sortBy);
        context.startService(intentToSyncImmediately);
    }
}
