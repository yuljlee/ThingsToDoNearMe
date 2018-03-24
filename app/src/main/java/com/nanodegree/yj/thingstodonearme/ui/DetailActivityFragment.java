package com.nanodegree.yj.thingstodonearme.ui;

import android.database.Cursor;
import android.net.ParseException;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanodegree.yj.thingstodonearme.R;
import com.nanodegree.yj.thingstodonearme.model.EventAdapter;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.detail_imageview) ImageView mEventImage;
    @BindView(R.id.name_textview) TextView mName;
    @BindView(R.id.desc_textview) TextView mDesc;
    @BindView(R.id.date_textview) TextView mDate;
    @BindView(R.id.location_textview) TextView mLocation;

    Unbinder unbinder;
    Uri mUri;
    private static final int EVENT_DETAIL_LOADER = 10;
    private EventAdapter mEventApdapter;

    public static final int INDEX_ID = 0;
    public static final int INDEX_EVENT_ID = 1;
    public static final int INDEX_EVENT_NAME = 2;
    public static final int INDEX_EVENT_DESC = 3;
    public static final int INDEX_EVENT_INAGE_URL = 5;
    public static final int INDEX_EVENT_DATE = 6;
    public static final int INDEX_EVENT_LOCATION = 17;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mUri = getActivity().getIntent().getData();
        if (mUri == null) throw new NullPointerException("URI for DetailActivity cannot be null");
        getActivity().getSupportLoaderManager().initLoader(EVENT_DETAIL_LOADER, null, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {

        switch (loaderId) {

            case EVENT_DETAIL_LOADER:
                //Log.v("Loader -> ", String.valueOf(loaderId));
                return new CursorLoader(getContext(),
                        mUri,
                        null,
                        null,
                        null,
                        null);

            default:
                throw new RuntimeException("Loader Not Implemented: " + loaderId);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        //mEventApdapter.swapCursor(data);

        boolean cursorHasValidData = false;
        if (data != null && data.moveToFirst()) {
            cursorHasValidData = true;
        }

        if (!cursorHasValidData) {
            return;
        }

        Picasso.with(getContext())
                .load(data.getString(INDEX_EVENT_INAGE_URL))
                .into(mEventImage);
        mName.setText(data.getString(INDEX_EVENT_NAME));
        mDesc.setText(data.getString(INDEX_EVENT_DESC));
        String eventDate = convertDateFormat(data.getString(INDEX_EVENT_DATE));
        mDate.setText(eventDate);
        mLocation.setText(data.getString(INDEX_EVENT_LOCATION));

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        //mEventApdapter.swapCursor(null);
    }

    private String convertDateFormat(String dateString) {
        //String strCurrentDate = "Wed, 18 Apr 2012 07:55:29 +0000";
        String date = null;
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss Z");
        try {
            Date newDate = format.parse(dateString);
            format = new SimpleDateFormat("MMM dd,yyyy hh:mm a");
            date = format.format(newDate);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
