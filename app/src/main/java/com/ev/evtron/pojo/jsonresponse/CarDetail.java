package com.ev.evtron.pojo.jsonresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarDetail {
    @SerializedName("carManufacturer")
    @Expose
    public String carManufacturer;
    @SerializedName("carManufacturerId")
    @Expose
    public Integer carManufacturerId;
    @SerializedName("carModel")
    @Expose
    public String carModel;
    @SerializedName("carModelId")
    @Expose
    public Integer carModelId;

    public String getCarManufacturer() {
        return carManufacturer;
    }

    public void setCarManufacturer(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }

    public Integer getCarManufacturerId() {
        return carManufacturerId;
    }

    public void setCarManufacturerId(Integer carManufacturerId) {
        this.carManufacturerId = carManufacturerId;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Integer getCarModelId() {
        return carModelId;
    }

    public void setCarModelId(Integer carModelId) {
        this.carModelId = carModelId;
    }
}
