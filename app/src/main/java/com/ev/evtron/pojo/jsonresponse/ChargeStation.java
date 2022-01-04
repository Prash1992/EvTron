package com.ev.evtron.pojo.jsonresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ChargeStation implements Serializable {
    @SerializedName("distance")
    @Expose
    public String distance;
    @SerializedName("chargeStationId")
    @Expose
    public Integer chargeStationId;
    @SerializedName("chargerBaysCount")
    @Expose
    public Integer chargerBaysCount;
    @SerializedName("chargeStationCode")
    @Expose
    public Object chargeStationCode;
    @SerializedName("chargeStationName")
    @Expose
    public String chargeStationName;
    @SerializedName("propertyName")
    @Expose
    public Object propertyName;
    @SerializedName("propertyType")
    @Expose
    public Object propertyType;
    @SerializedName("location")
    @Expose
    public String location;
    @SerializedName("latitude")
    @Expose
    public String latitude;
    @SerializedName("longitude")
    @Expose
    public String longitude;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("contact")
    @Expose
    public String contact;
    @SerializedName("district")
    @Expose
    public String district;
    @SerializedName("state")
    @Expose
    public String state;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("avgRating")
    @Expose
    public String avgRating;
    @SerializedName("imgUrl")
    @Expose
    public String imgUrl;
    @SerializedName("placeType")
    @Expose
    public String placeType;
    @SerializedName("ACChargerCount")
    @Expose
    public Integer ACChargerCount;
    @SerializedName("DCChargerCount")
    @Expose
    public Integer DCChargerCount;
    @SerializedName("amenitiesDetails")
    @Expose
    public List<AmenitiesDetail> amenitiesDetails = null;
    @SerializedName("chargerBayDetails")
    @Expose
    public List<ChargerBayDetail> chargerBayDetails = null;

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Integer getChargeStationId() {
        return chargeStationId;
    }

    public void setChargeStationId(Integer chargeStationCode) {
        this.chargeStationId = chargeStationCode;
    }

    public Integer getChargerBaysCount() {
        return chargerBaysCount;
    }

    public void setChargerBaysCount(Integer chargerBaysCount) {
        this.chargerBaysCount = chargerBaysCount;
    }

    public Object getChargeStationCode() {
        return chargeStationCode;
    }

    public void setChargeStationCode(Object chargeStationCode) {
        this.chargeStationCode = chargeStationCode;
    }

    public String getChargeStationName() {
        return chargeStationName;
    }

    public void setChargeStationName(String chargeStationName) {
        this.chargeStationName = chargeStationName;
    }

    public Object getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(Object propertyName) {
        this.propertyName = propertyName;
    }

    public Object getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(Object propertyType) {
        this.propertyType = propertyType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(String avgRating) {
        this.avgRating = avgRating;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public Integer getACChargerCount() {
        return ACChargerCount;
    }

    public void setACChargerCount(Integer acChargerCount) {
        this.ACChargerCount = acChargerCount;
    }

    public Integer getDCChargerCount() {
        return DCChargerCount;
    }

    public void setDCChargerCount(Integer dcChargerCount) {
        this.DCChargerCount = dcChargerCount;
    }

    public List<AmenitiesDetail> getAmenitiesDetails() {
        return amenitiesDetails;
    }

    public void setAmenitiesDetails(List<AmenitiesDetail> amenitiesDetails) {
        this.amenitiesDetails = amenitiesDetails;
    }

    public List<ChargerBayDetail> getChargerBayDetails() {
        return chargerBayDetails;
    }

    public void setChargerBayDetails(List<ChargerBayDetail> chargerBayDetails) {
        this.chargerBayDetails = chargerBayDetails;
    }
}
