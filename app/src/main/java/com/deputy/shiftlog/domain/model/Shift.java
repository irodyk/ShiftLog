package com.deputy.shiftlog.domain.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 01.09.2017.
 */

public class Shift implements Parcelable{

    private String id;
    private String startTime;
    private String endTime;
    private String duration;
    private String startLatitude;
    private String startLongitude;
    private String endtLatitude;
    private String endLongitude;
    private Bitmap image;

    public Shift(){
    }

    protected Shift(Parcel in) {
        id = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        duration = in.readString();
        startLatitude = in.readString();
        startLongitude = in.readString();
        endtLatitude = in.readString();
        endLongitude = in.readString();
        image = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<Shift> CREATOR = new Creator<Shift>() {
        @Override
        public Shift createFromParcel(Parcel in) {
            return new Shift(in);
        }

        @Override
        public Shift[] newArray(int size) {
            return new Shift[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(String startLatitude) {
        this.startLatitude = startLatitude;
    }

    public String getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(String startLongitude) {
        this.startLongitude = startLongitude;
    }

    public String getEndtLatitude() {
        return endtLatitude;
    }

    public void setEndtLatitude(String endtLatitude) {
        this.endtLatitude = endtLatitude;
    }

    public String getEndLongitude() {
        return endLongitude;
    }

    public void setEndLongitude(String endLongitude) {
        this.endLongitude = endLongitude;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(startTime);
        parcel.writeString(endTime);
        parcel.writeString(duration);
        parcel.writeString(startLatitude);
        parcel.writeString(startLongitude);
        parcel.writeString(endtLatitude);
        parcel.writeString(endLongitude);
        parcel.writeParcelable(image, i);
    }
}
