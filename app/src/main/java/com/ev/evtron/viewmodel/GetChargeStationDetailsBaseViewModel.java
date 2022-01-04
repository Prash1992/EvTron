package com.ev.evtron.viewmodel;

public class GetChargeStationDetailsBaseViewModel {
    private String latitude;
    private String longitude;
    private String currentlat;
    private String currentlong;
    private String distance;

    public String getCurrentlat() {
        return currentlat;
    }

    public void setCurrentlat(String currentlat) {
        this.currentlat = currentlat;
    }

    public String getCurrentlong() {
        return currentlong;
    }

    public void setCurrentlong(String currentlong) {
        this.currentlong = currentlong;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
