package com.nanodegree.yj.thingstodonearme;

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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.nanodegree.yj.thingstodonearme.R;
import com.nanodegree.yj.thingstodonearme.model.CategoryFragmentPagerAdapter;
import com.nanodegree.yj.thingstodonearme.model.EventAdapter;
import com.nanodegree.yj.thingstodonearme.model.EventContract;
import com.nanodegree.yj.thingstodonearme.sync.SyncUtils;
import com.nanodegree.yj.thingstodonearme.utils.NetworkUtils;

import java.io.IOException;
import java.net.URL;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements
        EventAdapter.EventAdapterOnClickHandler
        , LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private EventAdapter mEventApdapter;
    private static final int EVENT_LOADER_ID = 77;
    private int mPosition = RecyclerView.NO_POSITION;
    private FragmentPagerAdapter mFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        mFragmentPagerAdapter = new CategoryFragmentPagerAdapter(getSupportFragmentManager(), this);
//        viewPager.setAdapter(new CategoryFragmentPagerAdapter(getSupportFragmentManager(),
//                MainActivity.this));
        viewPager.setAdapter((mFragmentPagerAdapter));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        //ButterKnife.bind(this);
//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main);
//
//        LinearLayoutManager layoutManager
//                = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setHasFixedSize(true);
//        mEventApdapter = new EventAdapter(this, this);
//        mRecyclerView.setAdapter(mEventApdapter);
//
//        LoaderManager.LoaderCallbacks<Cursor> callback = MainActivity.this;
//        //Bundle bundleForLoader = new Bundle();
//        //bundleForLoader.putString("keySortBy", mSortBy);
//        //getSupportLoaderManager().initLoader(MOVIE_LOADER_ID, bundleForLoader, callback);
//        //getSupportLoaderManager().initLoader(FAVORITE_MOVIE_LOADER_ID, bundleForLoader, callback);
//
//
//        Log.d(TAG, "before init...");
//        //MovieSyncUtils.initialize(this);
//        //MovieSyncUtils.startImmediateSync(this, mSortBy);
//
//        getSupportLoaderManager().initLoader(EVENT_LOADER_ID, null, callback);
//        SyncUtils.startImmediateSync(this);

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
//        //Toast.makeText(this, "sort -> " + mSortBy, Toast.LENGTH_LONG).show();
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
