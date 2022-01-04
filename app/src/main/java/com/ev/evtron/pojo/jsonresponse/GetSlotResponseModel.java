package com.ev.evtron.pojo.jsonresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetSlotResponseModel implements Serializable {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("chargerBayId")
    @Expose
    private String chargerBayId;
    @SerializedName("splitsPerHour")
    @Expose
    private String splitsPerHour;
    @SerializedName("currentDate")
    @Expose
    private String currentDate;
    @SerializedName("nextDate")
    @Expose
    private String nextDate;
    @SerializedName("currentDaySlot")
    @Expose
    private List<CurrentDaySlot> currentDaySlot = null;
    @SerializedName("nextDaySlot")
    @Expose
    private List<NextDaySlot> nextDaySlot = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getChargerBayId() {
        return chargerBayId;
    }

    public void setChargerBayId(String chargerBayId) {
        this.chargerBayId = chargerBayId;
    }

    public String getSplitsPerHour() {
        return splitsPerHour;
    }

    public void setSplitsPerHour(String splitsPerHour) {
        this.splitsPerHour = splitsPerHour;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getNextDate() {
        return nextDate;
    }

    public void setNextDate(String nextDate) {
        this.nextDate = nextDate;
    }

    public List<CurrentDaySlot> getCurrentDaySlot() {
        return currentDaySlot;
    }

    public void setCurrentDaySlot(List<CurrentDaySlot> currentDaySlot) {
        this.currentDaySlot = currentDaySlot;
    }

    public List<NextDaySlot> getNextDaySlot() {
        return nextDaySlot;
    }

    public void setNextDaySlot(List<NextDaySlot> nextDaySlot) {
        this.nextDaySlot = nextDaySlot;
    }


}
