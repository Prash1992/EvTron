package com.ev.evtron.pojo.jsonresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateFavouriteResponseModel {

    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("chargeStationsStr")
    @Expose
    public String chargeStationsStr;
    @SerializedName("msg")
    @Expose
    public String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getChargeStationsStr() {
        return chargeStationsStr;
    }

    public void setChargeStationsStr(String chargeStationsStr) {
        this.chargeStationsStr = chargeStationsStr;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
