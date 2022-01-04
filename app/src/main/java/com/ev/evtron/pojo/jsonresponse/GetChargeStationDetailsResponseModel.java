package com.ev.evtron.pojo.jsonresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetChargeStationDetailsResponseModel implements Serializable {

    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("ChargeStation")
    @Expose
    public List<ChargeStation> ChargeStation = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ChargeStation> getChargeStation() {
        return ChargeStation;
    }

    public void setChargeStation(List<ChargeStation> chargeStation) {
        this.ChargeStation = chargeStation;
    }
}
