package com.nanodegree.yj.thingstodonearme.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by u2stay1915 on 3/10/18.
 */

public class Event implements Parcelable {

    private String event_id;
    private String name;
    private String description;
    private String event_site_url;
    private String image_url;
    private String time_start;
    private String time_end;
    private double latitude;
    private double longitude;
    //private ArrayList<Location> location;

    public Event (String event_id, String name, String description, String event_site_url
            , String image_url, String time_start, String time_end, double latitude, double longitude
            /*, ArrayList<Location> location*/) {
        this.event_id = event_id;
        this.name = name;
        this.description = description;
        this.event_site_url = event_site_url;
        this.image_url = image_url;
        this.time_start = time_start;
        this.time_end = time_end;
        this.latitude = latitude;
        this.longitude = longitude;
        //this.location = location;
    }

    private Event (Parcel in) {
        event_id = in.readString();
        name = in.readString();
        description = in.readString();
        event_site_url = in.readString();
        image_url = in.readString();
        time_start = in.readString();
        time_end = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
//        if (in.readByte() == 0x01) {
//            location = new ArrayList<Location>();
//            in.readList(location, com.nanodegree.yj.thingstodonearme.model.Location.class.getClassLoader());
//        } else {
//            location = null;
//        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(event_id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(event_site_url);
        dest.writeString(image_url);
        dest.writeString(time_end);
        dest.writeString(time_start);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
//        if (location == null) {
//            dest.writeByte((byte) (0x00));
//        } else {
//            dest.writeByte((byte) (0x01));
//            dest.writeList(location);
//        }
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getEvent_id() {
        return event_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEvent_site_url() {
        return event_site_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getTime_start() {
        return time_start;
    }

    public String getTime_end() {
        return time_end;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

//    public ArrayList<Location> getLocation() {
//        return location;
//    }
}
