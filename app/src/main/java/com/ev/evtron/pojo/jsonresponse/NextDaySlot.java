package com.ev.evtron.pojo.jsonresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NextDaySlot implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("timeString")
    @Expose
    private String timeString;
    @SerializedName("slotStatus")
    @Expose
    private String slotStatus;
    @SerializedName("slotStatusCode")
    @Expose
    private String slotStatusCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    public String getSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(String slotStatus) {
        this.slotStatus = slotStatus;
    }

    public String getSlotStatusCode() {
        return slotStatusCode;
    }

    public void setSlotStatusCode(String slotStatusCode) {
        this.slotStatusCode = slotStatusCode;
    }
}
