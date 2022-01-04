package com.ev.evtron.pojo.jsonresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelDetail {
    @SerializedName("evmId")
    @Expose
    public Integer evmId;
    @SerializedName("fuelType")
    @Expose
    public String fuelType;
    @SerializedName("modName")
    @Expose
    public String modName;
    @SerializedName("modelId")
    @Expose
    public Integer modelId;

    public Integer getEvmId() {
        return evmId;
    }

    public void setEvmId(Integer evmId) {
        this.evmId = evmId;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getModName() {
        return modName;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }
}
