package com.ev.evtron.pojo.jsonresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetfavouriteResponseModel {
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("favIds")
    @Expose
    public String favIds;
    @SerializedName("ChargeStation")
    @Expose
    public List<ChargeStation> ChargeStation = null;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFavIds() {
        return favIds;
    }

    public void setFavIds(String favIds) {
        this.favIds = favIds;
    }

    public List<ChargeStation> getChargeStation() {
        return ChargeStation;
    }

    public void setChargeStation(List<ChargeStation> chargeStation) {
        this.ChargeStation = chargeStation;
    }
}
