package com.ev.evtron.pojo.jsonresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetEvDetailsResponseModel implements Serializable {
    @SerializedName("manufacturerId")
    @Expose
    public Integer manufacturerId;
    @SerializedName("manufacturerName")
    @Expose
    public  String manufacturerName;
    @SerializedName("modelDetails")
    @Expose
    public List<ModelDetail> modelDetails = null;

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public List<ModelDetail> getModelDetails() {
        return modelDetails;
    }

    public void setModelDetails(List<ModelDetail> modelDetails) {
        this.modelDetails = modelDetails;
    }
}
