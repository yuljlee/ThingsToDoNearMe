package com.nanodegree.yj.thingstodonearme.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by u2stay1915 on 3/12/18.
 */

public class Location implements Parcelable {

    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String zip_code;
    private String country;
    private String state;
    private String display_address;
    private String cross_streets;

    public Location (String address1, String address2, String address3, String city
            , String zip_code, String country, String state, String display_address
            , String cross_streets) {
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.city = city;
        this.zip_code = zip_code;
        this.country = country;
        this.state = state;
        this.display_address = display_address;
        this.cross_streets = cross_streets;
    }

    private Location (Parcel in) {
        address1 = in.readString();
        address2 = in.readString();
        address3 = in.readString();
        city = in.readString();
        zip_code = in.readString();
        country = in.readString();
        state = in.readString();
        display_address = in.readString();
        cross_streets = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address1);
        dest.writeString(address2);
        dest.writeString(address3);
        dest.writeString(city);
        dest.writeString(zip_code);
        dest.writeString(country);
        dest.writeString(state);
        dest.writeString(display_address);
        dest.writeString(cross_streets);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getAddress3() {
        return address3;
    }

    public String getCity() {
        return city;
    }

    public String getZip_code() {
        return zip_code;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getDisplay_address() {
        return display_address;
    }

    public String getCross_streets() {
        return cross_streets;
    }

}
