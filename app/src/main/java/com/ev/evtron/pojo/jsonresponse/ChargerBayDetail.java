package com.ev.evtron.pojo.jsonresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChargerBayDetail implements Serializable {
    @SerializedName("chargerBayId")
    @Expose
    public Integer chargerBayId;
    @SerializedName("chargerModel")
    @Expose
    public String chargerModel;
    @SerializedName("chargerType")
    @Expose
    public String chargerType;
    @SerializedName("range")
    @Expose
    public String range;
    @SerializedName("powerOutput")
    @Expose
    public String powerOutput;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("timings")
    @Expose
    public String timings;
    @SerializedName("pricePerHour")
    @Expose
    public String pricePerHour;
    @SerializedName("pinType")
    @Expose
    public String pinType;

    public Integer getChargerBayId() {
        return chargerBayId;
    }

    public void setChargerBayId(Integer chargerBayId) {
        this.chargerBayId = chargerBayId;
    }

    public String getChargerModel() {
        return chargerModel;
    }

    public void setChargerModel(String chargerModel) {
        this.chargerModel = chargerModel;
    }

    public String getChargerType() {
        return chargerType;
    }

    public void setChargerType(String chargerType) {
        this.chargerType = chargerType;
    }

    public Object getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getPowerOutput() {
        return powerOutput;
    }

    public void setPowerOutput(String powerOutput) {
        this.powerOutput = powerOutput;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(String pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getPinType() {
        return pinType;
    }

    public void setPinType(String pinType) {
        this.pinType = pinType;
    }
}
