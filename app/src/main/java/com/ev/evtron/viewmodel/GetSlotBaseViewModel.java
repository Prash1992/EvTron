package com.ev.evtron.viewmodel;

public class GetSlotBaseViewModel {
    private String chargerbayId;
    private String time;
    private String chargerType;
    private String stationId;

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getChargerType() {
        return chargerType;
    }

    public void setChargerType(String chargerType) {
        this.chargerType = chargerType;
    }

    public String getChargerbayId() {
        return chargerbayId;
    }

    public void setChargerbayId(String chargerbayId) {
        this.chargerbayId = chargerbayId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
