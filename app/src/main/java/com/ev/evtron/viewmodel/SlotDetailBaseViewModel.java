package com.ev.evtron.viewmodel;

public class SlotDetailBaseViewModel {
    public String favStationId;
    public Integer flag;
    public String latitude;
    public String longitude;


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

    public String getFavStationId() {
        return favStationId;
    }

    public void setFavStationId(String favStationId) {
        this.favStationId = favStationId;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
