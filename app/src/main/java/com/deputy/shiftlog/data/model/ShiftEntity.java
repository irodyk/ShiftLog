package com.deputy.shiftlog.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * ShiftLog
 * Created by Iurii Rodyk on 02.09.2017.
 */

public class ShiftEntity {

    @SerializedName("id")
    int id;
    @SerializedName("start")
    String startTime;
    @SerializedName("end")
    String endTime;
    @SerializedName("startLatitude")
    String startLatitude;
    @SerializedName("startLongitude")
    String startLongitude;
    @SerializedName("endLatitude")
    String endtLatitude;
    @SerializedName("endLongitude")
    String endLongitude;
    @SerializedName("image")
    String imageUrl;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
