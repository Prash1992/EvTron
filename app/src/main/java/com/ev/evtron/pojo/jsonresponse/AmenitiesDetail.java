package com.ev.evtron.pojo.jsonresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AmenitiesDetail implements Serializable {
    @SerializedName("restaurant")
    @Expose
    public Integer restaurant;
    @SerializedName("rooms")
    @Expose
    public Integer rooms;
    @SerializedName("playArea")
    @Expose
    public Integer playArea;
    @SerializedName("wifi")
    @Expose
    public Integer wifi;
    @SerializedName("noOfChargers")
    @Expose
    public Integer noOfChargers;
    @SerializedName("barFacility")
    @Expose
    public Integer barFacility;
    @SerializedName("pool")
    @Expose
    public Integer pool;

    public Integer getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Integer restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getPlayArea() {
        return playArea;
    }

    public void setPlayArea(Integer playArea) {
        this.playArea = playArea;
    }

    public Integer getWifi() {
        return wifi;
    }

    public void setWifi(Integer wifi) {
        this.wifi = wifi;
    }

    public Integer getNoOfChargers() {
        return noOfChargers;
    }

    public void setNoOfChargers(Integer noOfChargers) {
        this.noOfChargers = noOfChargers;
    }

    public Integer getBarFacility() {
        return barFacility;
    }

    public void setBarFacility(Integer barFacility) {
        this.barFacility = barFacility;
    }

    public Integer getPool() {
        return pool;
    }

    public void setPool(Integer pool) {
        this.pool = pool;
    }

}
