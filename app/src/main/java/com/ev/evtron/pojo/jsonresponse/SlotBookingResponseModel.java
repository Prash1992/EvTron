package com.ev.evtron.pojo.jsonresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SlotBookingResponseModel {
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("bookingInternalId")
    @Expose
    public Integer bookingInternalId;
    @SerializedName("bookingId")
    @Expose
    public String bookingId;
    @SerializedName("startDateTime")
    @Expose
    public String startDateTime;
    @SerializedName("endDateTime")
    @Expose
    public String endDateTime;
    @SerializedName("totalHours")
    @Expose
    public String totalHours;
    @SerializedName("unitsConsumed")
    @Expose
    public String unitsConsumed;
    @SerializedName("batteryCapacity")
    @Expose
    public String batteryCapacity;
    @SerializedName("estimatedAmountPerHour")
    @Expose
    public String estimatedAmountPerHour;
    @SerializedName("totalEstimatedAmount")
    @Expose
    public String totalEstimatedAmount;
    @SerializedName("msg")
    @Expose
    public String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getBookingInternalId() {
        return bookingInternalId;
    }

    public void setBookingInternalId(Integer bookingInternalId) {
        this.bookingInternalId = bookingInternalId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(String totalHours) {
        this.totalHours = totalHours;
    }

    public String getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(String unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    public String getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(String batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public String getEstimatedAmountPerHour() {
        return estimatedAmountPerHour;
    }

    public void setEstimatedAmountPerHour(String estimatedAmountPerHour) {
        this.estimatedAmountPerHour = estimatedAmountPerHour;
    }

    public String getTotalEstimatedAmount() {
        return totalEstimatedAmount;
    }

    public void setTotalEstimatedAmount(String totalEstimatedAmount) {
        this.totalEstimatedAmount = totalEstimatedAmount;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
