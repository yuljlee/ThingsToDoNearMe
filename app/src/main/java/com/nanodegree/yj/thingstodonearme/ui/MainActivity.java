package com.nanodegree.yj.thingstodonearme.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nanodegree.yj.thingstodonearme.R;
import com.nanodegree.yj.thingstodonearme.model.CategoryFragmentPagerAdapter;
import com.nanodegree.yj.thingstodonearme.model.EventAdapter;
import com.nanodegree.yj.thingstodonearme.model.EventContract;
import com.nanodegree.yj.thingstodonearme.sync.SyncUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.nanodegree.yj.thingstodonearme.model.Constant.DEFAULT_LOCATION;
import static com.nanodegree.yj.thingstodonearme.model.Constant.LOCATION_KEY;

public class MainActivity extends AppCompatActivity implements
        EventAdapter.EventAdapterOnClickHandler
        , LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int PICK_CITY_REQUEST = 1;  // The request code
    private static final String CURRENT_LOCATION = "location";

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.view_pager) ViewPager mViewPager;
    @BindView(R.id.sliding_tabs) TabLayout mTabLayout;
    @BindView(R.id.textView_city) TextView mCityName;

    private RecyclerView mRecyclerView;
    private EventAdapter mEventApdapter;
    private static final int EVENT_LOADER_ID = 77;
    private int mPosition = RecyclerView.NO_POSITION;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private String mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        loadPreferences();
        // Initialize Mobile Ads SDK
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        // show city name
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        // draw underline of city name
        SpannableString content = new SpannableString(mLocation);
        content.setSpan(new UnderlineSpan(), 0, mLocation.length(), 0);
        //mTextView.setText(content);

        //getSupportActionBar().setTitle("around   " + mLocation);
        getSupportActionBar().setTitle("");
        mCityName.setText(content);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        //ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        mFragmentPagerAdapter = new CategoryFragmentPagerAdapter(getSupportFragmentManager(), this);
//        viewPager.setAdapter(new CategoryFragmentPagerAdapter(getSupportFragmentManager(),
//                MainActivity.this));
        mViewPager.setAdapter((mFragmentPagerAdapter));

        // Give the TabLayout the ViewPager
        //TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public void loadPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        mLocation = sharedPreferences.getString(LOCATION_KEY, DEFAULT_LOCATION);
        //Toast.makeText(this, "pref! ---> " + mSortBy, Toast.LENGTH_LONG).show();
    }

    public void savePreferences(String location) {
        SharedPreferences sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LOCATION_KEY, location);
        editor.apply();

        //mSortBy = sortOrder;
    }

    private void broadcastIntent() {
        Intent intent = new Intent(this, EventAppWidget.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE\"");
        sendBroadcast(intent);
    }

    @OnClick(R.id.toolbar)
    public void showCityList() {
        //Toast.makeText(this, "I'm in Los Angeles", Toast.LENGTH_LONG).show();

        Class destinationClass = SearchActivity.class;
        Intent intent = new Intent(this, destinationClass);
        //intent.putExtra("city_name", "San Diego");

        startActivityForResult(intent, PICK_CITY_REQUEST);
    }

    // show city name get chosen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_CITY_REQUEST)
        {
            if (resultCode == RESULT_OK) {
                String cityName = data.getStringExtra("city_name");
                //Toast.makeText(this, "I got it ---> " + cityName, Toast.LENGTH_LONG).show();
                savePreferences(cityName);
                //getSupportActionBar().setTitle("around   " + cityName);
                SpannableString content = new SpannableString(cityName);
                content.setSpan(new UnderlineSpan(), 0, cityName.length(), 0);
                mCityName.setText(content);
                //broadcastIntent();
            }
        }
    }

    public void onClickTest(View view){
         //URL url =  NetworkUtils.buildUrl();
         //NetworkUtils.fetchJsonArray();
        SyncUtils.startImmediateSync(this, "music", "San Diego, CA");
    }

    @Override
    public void onClick(int indexId) {
          Toast.makeText(this, "sort -> hi", Toast.LENGTH_LONG).show();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {

        switch (loaderId) {
            case EVENT_LOADER_ID:

                return new CursorLoader(this,
                        EventContract.EventEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);
            default:
                throw new RuntimeException("Loading Error " + loaderId);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mEventApdapter.swapCursor(data);

        if (mPosition == RecyclerView.NO_POSITION) mPosition = 0;

        mRecyclerView.smoothScrollToPosition(mPosition);

        Log.v("loader called", Integer.toString(mPosition));

        if (data.getCount() != 0) showEventDataView();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mEventApdapter.swapCursor(null);
    }

    private void showEventDataView() {
        //mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();

       mFragmentPagerAdapter.notifyDataSetChanged();
    }
}
