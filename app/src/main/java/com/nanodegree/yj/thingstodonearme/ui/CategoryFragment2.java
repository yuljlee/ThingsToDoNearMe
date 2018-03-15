package com.nanodegree.yj.thingstodonearme.ui;


import android.database.Cursor;
import android.os.Bundle;
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
import com.nanodegree.yj.thingstodonearme.model.EventAdapterFrag2;
import com.nanodegree.yj.thingstodonearme.model.EventContract;
import com.nanodegree.yj.thingstodonearme.sync.SyncUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment2 extends Fragment implements
        EventAdapterFrag2.EventAdapterOnClickHandler
        , LoaderManager.LoaderCallbacks<Cursor>{

    private int mPage;
    private static final String TAG = CategoryFragment2.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private EventAdapterFrag2 mEventApdapter;
    private static final int EVENT_LOADER_ID = 82;
    private int mPosition = RecyclerView.NO_POSITION;

    public CategoryFragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_fragment2, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_frag2);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mEventApdapter = new EventAdapterFrag2(getContext(), this);
        mRecyclerView.setAdapter(mEventApdapter);

        LoaderManager.LoaderCallbacks<Cursor> callback = this;
        //Bundle bundleForLoader = new Bundle();
        //bundleForLoader.putString("keySortBy", mSortBy);
        //getSupportLoaderManager().initLoader(MOVIE_LOADER_ID, bundleForLoader, callback);
        //getSupportLoaderManager().initLoader(FAVORITE_MOVIE_LOADER_ID, bundleForLoader, callback);


        Log.d(TAG, "before init...");
        //MovieSyncUtils.initialize(this);
        //MovieSyncUtils.startImmediateSync(this, mSortBy);

        getActivity().getSupportLoaderManager().initLoader(EVENT_LOADER_ID, null, callback);
        //SyncUtils.startImmediateSync(getContext(), "visual-arts");
        SyncUtils.startImmediateSync(getContext(), "fashion");


        return view;
    }

    public static CategoryFragment2 newInstance() {
        Bundle args = new Bundle();
        //args.putInt(ARG_PAGE, page);
        CategoryFragment2 fragment = new CategoryFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case EVENT_LOADER_ID:

                return new CursorLoader(getContext(),
                        EventContract.EventEntry.CONTENT_URI,
                        null,
                        null,
                        null,
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

    }

    private void showEventDataView() {
        //mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}
