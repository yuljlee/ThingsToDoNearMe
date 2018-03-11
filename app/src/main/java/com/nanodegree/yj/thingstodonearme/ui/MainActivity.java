package com.nanodegree.yj.thingstodonearme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.nanodegree.yj.thingstodonearme.R;
import com.nanodegree.yj.thingstodonearme.sync.SyncUtils;
import com.nanodegree.yj.thingstodonearme.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickTest(View view){
         //URL url =  NetworkUtils.buildUrl();
         //NetworkUtils.fetchJsonArray();
        SyncUtils.startImmediateSync(this);
    }
}
