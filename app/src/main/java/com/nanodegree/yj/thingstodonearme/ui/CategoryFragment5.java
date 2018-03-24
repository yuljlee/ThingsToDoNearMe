package com.nanodegree.yj.thingstodonearme.ui;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanodegree.yj.thingstodonearme.R;
import com.nanodegree.yj.thingstodonearme.model.EventAdapter;
import com.nanodegree.yj.thingstodonearme.model.EventContract;
import com.nanodegree.yj.thingstodonearme.sync.SyncUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment5 extends Fragment implements
        EventAdapter.EventAdapterOnClickHandler
        , LoaderManager.LoaderCallbacks<Cursor>{
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private static final String TAG = CategoryFragment5.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private EventAdapter mEventApdapter;
    private static final int EVENT_LOADER_ID = 85;
    private int mPosition = RecyclerView.NO_POSITION;

    public CategoryFragment5() {
        // Required empty public constructor
    }

    //public static CategoryFragment1 newInstance(int page) {
    public static CategoryFragment5 newInstance() {
        Bundle args = new Bundle();
        //args.putInt(ARG_PAGE, page);
        CategoryFragment5 fragment = new CategoryFragment5();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_fragment5, container, false);

        //TextView textView = (TextView) view.findViewById(R.id.textview);
        //textView.setText("Fragment #" + mPage);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_frag5);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mEventApdapter = new EventAdapter(this);
        mRecyclerView.setAdapter(mEventApdapter);

//        LoaderManager.LoaderCallbacks<Cursor> callback = this;
//        //Bundle bundleForLoader = new Bundle();
//        //bundleForLoader.putString("keySortBy", mSortBy);
//        //getSupportLoaderManager().initLoader(MOVIE_LOADER_ID, bundleForLoader, callback);
//        //getSupportLoaderManager().initLoader(FAVORITE_MOVIE_LOADER_ID, bundleForLoader, callback);
//
//
//        Log.d(TAG, "before init...");
//        //MovieSyncUtils.initialize(this);
//        //MovieSyncUtils.startImmediateSync(this, mSortBy);
//        Bundle bundle = new Bundle();
//        bundle.putString("category", "music");
//        getActivity().getSupportLoaderManager().initLoader(EVENT_LOADER_ID, bundle, callback);
//        SyncUtils.startImmediateSync(getContext(), "music");

        return view;
        //return inflater.inflate(R.layout.fragment_category_fragment1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LoaderManager.LoaderCallbacks<Cursor> callback = this;

        Log.d(TAG, "before init...");

        Bundle bundleForLoader = new Bundle();
        bundleForLoader.putString("category", "lectures-books");
        getActivity().getSupportLoaderManager().initLoader(EVENT_LOADER_ID, bundleForLoader, callback);
        SyncUtils.startImmediateSync(getContext(), "lectures-books");
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        //String cat = args[0].getString("category");
        String[] selectionArgs = {args.getString("category")};
        switch (id) {
            case EVENT_LOADER_ID:

                return new CursorLoader(getContext(),
                        EventContract.EventEntry.CONTENT_URI,
                        null,
                        //EventContract.EventEntry.COLUMN_CATEGORY + " = ?",
                        EventContract.EventEntry.COLUMN_CATEGORY + " = ?",
                        selectionArgs,
                        null);

            default:
                throw new RuntimeException("Loading Error " + id);
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

    @Override
    public void onClick(int eventId) {
//        Context context = getContext();
//        Class destinationClass = DetailActivity.class;
//        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
//        intentToStartDetailActivity.putExtra("movie", movie);
//        startActivity(intentToStartDetailActivity);
//
//        Intent movieDetailIntent = new Intent(MainActivity.this, DetailActivity.class);
//
//        // if selected movie is not the favorite, check it exists in favorite table to set the star on or off
//
//        //Toast.makeText(this, "sort -> " + mSortBy, Toast.LENGTH_LONG).show();
//        Log.d("onClick: indexId -> ", String.valueOf(indexId));
//        Log.d("onClick: mSortBy -> ", mSortBy);
//
//        Uri uriMovieClicked = EventContract.EventEntry.buildMovieUriWithId(indexId);
//        movieDetailIntent.setData(uriMovieClicked);
//        startActivity(movieDetailIntent);
//        //startActivityForResult(movieDetailIntent, PICK_MOVIE_REQUEST);
    }

    private void showEventDataView() {
        //mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}
