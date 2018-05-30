package com.nanodegree.yj.thingstodonearme.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nanodegree.yj.thingstodonearme.R;
import com.nanodegree.yj.thingstodonearme.model.CategoryFragmentPagerAdapter;
import com.nanodegree.yj.thingstodonearme.model.EventAdapter;
import com.nanodegree.yj.thingstodonearme.model.EventContract;
import com.nanodegree.yj.thingstodonearme.sync.SyncUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements
        EventAdapter.EventAdapterOnClickHandler
        , LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = MainActivity.class.getSimpleName();
    static final int PICK_CITY_REQUEST = 1;  // The request code

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.view_pager) ViewPager mViewPager;
    @BindView(R.id.sliding_tabs) TabLayout mTabLayout;

    private RecyclerView mRecyclerView;
    private EventAdapter mEventApdapter;
    private static final int EVENT_LOADER_ID = 77;
    private int mPosition = RecyclerView.NO_POSITION;
    private FragmentPagerAdapter mFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // show city name
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Los Angeles");

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

    @OnClick(R.id.toolbar)
    public void showCityList() {
        //Toast.makeText(this, "I'm in Los Angeles", Toast.LENGTH_LONG).show();

        Class destinationClass = SearchActivity.class;
        Intent intent = new Intent(this, destinationClass);
        //intent.putExtra("city_name", "San Diego");

        startActivityForResult(intent, PICK_CITY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_CITY_REQUEST)
        {
            if (resultCode == RESULT_OK) {
                String cityName = data.getStringExtra("city_name").toString();
                //Toast.makeText(this, "I got it ---> " + cityName, Toast.LENGTH_LONG).show();
                getSupportActionBar().setTitle(cityName);
            }
        }
    }

    public void onClickTest(View view){
         //URL url =  NetworkUtils.buildUrl();
         //NetworkUtils.fetchJsonArray();
        SyncUtils.startImmediateSync(this, "music");
    }

    @Override
    public void onClick(int indexId) {
//        Context context = this;
//        Class destinationClass = DetailActivity.class;
//        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
//        intentToStartDetailActivity.putExtra("movie", movie);
//        startActivity(intentToStartDetailActivity);

//        Intent movieDetailIntent = new Intent(MainActivity.this, DetailActivity.class);
//
//        // if selected movie is not the favorite, check it exists in favorite table to set the star on or off
//
          Toast.makeText(this, "sort -> hi", Toast.LENGTH_LONG).show();
//        Log.d("onClick: indexId -> ", String.valueOf(indexId));
//        Log.d("onClick: mSortBy -> ", mSortBy);
//        Uri uriMovieClicked;
//        if (mSortBy.equals(SORT_FAVORITE)) {
//            uriMovieClicked = MovieContract.FavoriteMovieEntry.buildMovieUriWithId(indexId);
//        } else {
//            uriMovieClicked = MovieContract.MovieEntry.buildMovieUriWithId(indexId);
//        }
//
//        movieDetailIntent.setData(uriMovieClicked);
//        //startActivity(movieDetailIntent);
//        startActivityForResult(movieDetailIntent, PICK_MOVIE_REQUEST);
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
