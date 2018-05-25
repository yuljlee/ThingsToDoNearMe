package com.nanodegree.yj.thingstodonearme.model;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanodegree.yj.thingstodonearme.R;
import com.nanodegree.yj.thingstodonearme.utils.CommonUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by u2stay1915 on 3/11/18.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventAdapterViewHolder> {

    private static final String TAG = EventAdapter.class.getSimpleName();

    //private final Context mContext;
    private ArrayList<Event> mEventData;
    private final EventAdapterOnClickHandler mClickHandler;
    private Cursor mCursor;

    public interface EventAdapterOnClickHandler {
        //void onLoadFinished(Loader<ArrayList<Movie>> loader, ArrayList<Movie> data);

        //void onClick(Movie weatherForDay);
        void onClick(int eventId);
    }

    public EventAdapter(EventAdapterOnClickHandler clickHandler) {
        //mContext = context;
        mClickHandler = clickHandler;
    }

    public class EventAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final ImageView mMoviePoster1;
        public final TextView mEventTextView;
        public final TextView mDateTextView;
        public final TextView mLocationTextView;

        public EventAdapterViewHolder(View view) {
            super(view);
            mMoviePoster1 = (ImageView) view.findViewById((R.id.event_imageview));
            mEventTextView = (TextView) view.findViewById(R.id.textview);
            mDateTextView = (TextView) view.findViewById(R.id.date_textview);
            mLocationTextView = (TextView) view.findViewById(R.id.location_textview);

            view.setOnClickListener(this);
        }

        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            //Movie movie = mMovieData.get(adapterPosition);
            //mClickHandler.onClick(movie);
            mCursor.moveToPosition(adapterPosition);
            int indexId = mCursor.getInt(0); // movie id
            mClickHandler.onClick(indexId);

            Log.v(TAG + " click ---> ", String.valueOf(indexId));
        }
    }

    public EventAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.event_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean attachToRoot = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, attachToRoot);

        return new EventAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventAdapter.EventAdapterViewHolder holder, int position) {
        mCursor.moveToPosition(position);

        Log.v("position ---> ", String.valueOf(position));

        String posterUrl = mCursor.getString(5);
        //movieAdapterViewHolder.mMovieTextView.setText(mCursor.getString(1));

        if (!posterUrl.equals("") && posterUrl != null) {
            Context context = holder.mMoviePoster1.getContext();
            Picasso.with(context)
                    .load(posterUrl)
                    .into(holder.mMoviePoster1);
        }

        holder.mEventTextView.setText(mCursor.getString(2)); // event name
        holder.mDateTextView.setText(CommonUtils.reformatDate(mCursor.getString(6)));  // event date
        holder.mLocationTextView.setText(CommonUtils.convertAddress(mCursor.getString(17))); // event location
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) return 0;
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        mCursor = newCursor;
        notifyDataSetChanged();
    }
}
