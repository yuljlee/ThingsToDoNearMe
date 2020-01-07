package com.nanodegree.yj.thingstodonearme.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nanodegree.yj.thingstodonearme.R;
import com.nanodegree.yj.thingstodonearme.model.Event;
import com.nanodegree.yj.thingstodonearme.model.EventAdapter;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.ContentValues.TAG;
import static com.nanodegree.yj.thingstodonearme.utils.CommonUtils.convertAddress;
import static com.nanodegree.yj.thingstodonearme.utils.CommonUtils.convertDateTime;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<Cursor> {

    @BindView(R.id.detail_imageview) ImageView mEventImage;
    @BindView(R.id.name_textview) TextView mName;
    @BindView(R.id.desc_textview) TextView mDesc;
    @BindView(R.id.date_textview) TextView mDate;
    @BindView(R.id.date_to_textview) TextView mDateTo;
    @BindView(R.id.location_textview) TextView mLocation;
    @BindView(R.id.adView) AdView mAdView;
    @BindView(R.id.fab) FloatingActionButton mFab;

    Unbinder unbinder;
    Uri mUri;
    String mSiteUrl;
    private static final int EVENT_DETAIL_LOADER = 10;
    private EventAdapter mEventApdapter;
    static final String KEY_URI = "KEY_URI";

    private double mLatitude = 33.898580;
    private double mLogitude = -117.983674;
    //private double mLatitude = 0.0;
    //private double mLogitude = -111.0;
    private double[] mLatLng = {mLatitude, mLogitude};

    public static final int INDEX_ID = 0;
    public static final int INDEX_EVENT_ID = 1;
    public static final int INDEX_EVENT_NAME = 2;
    public static final int INDEX_EVENT_DESC = 3;
    public static final int INDEX_EVENT_SITE_URL = 4;
    public static final int INDEX_EVENT_INAGE_URL = 5;
    public static final int INDEX_EVENT_DATE = 6;
    public static final int INDEX_EVENT_DATE_TO = 7;
    public static final int INDEX_EVENT_LATITUDE = 8;
    public static final int INDEX_EVENT_LONGITUGE = 9;
    public static final int INDEX_EVENT_LOCATION = 17;

    public DetailActivityFragment() {
    }

    @OnClick(R.id.url_button)
    public void launchUrl() {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mSiteUrl));
        startActivity(browserIntent);
    }

    @OnClick(R.id.fab)
    public void launchMap() {
        Context context = getActivity();
        Class destinationClass = MapsActivity.class;
        Intent intent = new Intent(context, destinationClass);
        //Uri uriMovieClicked = EventContract.EventEntry.buildEventUriWithId(eventId);
        //intentToStartDetailActivity.setData(uriMovieClicked);
        //intent.putExtra("event_location", mLatLng);
        //intent.putExtra("event_name", mName);
//
//        Location location = new Location("", "", ""
//                , "", "", "", "", "", "");
        Event event = new Event("", mName.getText().toString(), "", ""
                , "", "", "", mLatLng[0]
                , mLatLng[1]);

        intent.putExtra("event_location", event);

        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        unbinder = ButterKnife.bind(this, view);

        MobileAds.initialize(getActivity(), getString(R.string.admob_app_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdOpened();
                //Toast.makeText(getActivity(), "Ad opened.", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                //Toast.makeText(getActivity(), "What happened?", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(KEY_URI))
                mUri = Uri.parse(savedInstanceState.getString(KEY_URI));
        } else {
            mUri = getActivity().getIntent().getData();
        }

        Log.d(TAG, "uriEventClicked --> " + mUri.toString());
        if (mUri == null) throw new NullPointerException("URI for DetailActivity cannot be null");
        getActivity().getSupportLoaderManager().initLoader(EVENT_DETAIL_LOADER, null, this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mUri != null)
            outState.putString(KEY_URI, mUri.toString());
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

        if (!data.getString(INDEX_EVENT_INAGE_URL).equals("") && data.getString(INDEX_EVENT_INAGE_URL) != null) {
            Picasso.with(getContext())
                    .load(data.getString(INDEX_EVENT_INAGE_URL))
                    .into(mEventImage);
        }
        else {
            /* show default image */
//            Picasso.with(getContext())
//                    .load(R.drawable.no_image)
//                    .fit()
//                    .into(mEventImage);
        }

        mName.setText(data.getString(INDEX_EVENT_NAME));
        mDesc.setText(data.getString(INDEX_EVENT_DESC));
        String eventDate = convertDateTime(data.getString(INDEX_EVENT_DATE));
        mDate.setText("From: " + eventDate);
        String eventDateEnd = data.getString(INDEX_EVENT_DATE_TO);
        if (eventDateEnd != null && !eventDateEnd.isEmpty() && !eventDateEnd.equals("null")) {
            //eventDateEnd = reformatDate(data.getString(INDEX_EVENT_DATE_TO));
            mDateTo.setText("To: " + convertDateTime(eventDateEnd));
        }
        mLocation.setText(convertAddress(data.getString(INDEX_EVENT_LOCATION)));
        mSiteUrl = data.getString(INDEX_EVENT_SITE_URL);
        //mLatitude = data.getDouble(INDEX_EVENT_LATITUDE);
        //mLogitude = data.getDouble(INDEX_EVENT_LONGITUGE);
        mLatLng[0] = data.getDouble(INDEX_EVENT_LATITUDE);
        mLatLng[1] = data.getDouble(INDEX_EVENT_LONGITUGE);
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
