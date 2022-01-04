package com.ev.evtron.utils;

public class ApiClass {

    public ApiClass() {

    }

    public static final String BASE_URL = "http://103.212.120.220:5000/api/";
    public static final String MOBILE_URL = "userAuth/";

    public static final String GENERATE_OTP_API = BASE_URL + MOBILE_URL + "generateOtp";
    public static final String VALIDATE_OTP_API = BASE_URL + MOBILE_URL + "validateOtp";
    public static final String GET_CUSTOMER_INFO_API = BASE_URL + "customer";
    public static final String GET_EV_DETAILS_API= BASE_URL + "evdetails";
    public static final String UPDATE_CUSTOMER_INFO_API = BASE_URL + "customerDetails";
    public static final String UPDATE_FAVOURITE_API = BASE_URL + "customer/favChargeStations";
    public static final String GET_CHARGESTATION_DETAILS = BASE_URL + "chargeStationDetails";
    public static final String GET_SLOTS = BASE_URL + "slots";
    public static final String GET_SLOT_BOOKING = BASE_URL + "slotbooking";
    public static final String POST_REVIEW = BASE_URL + "reviews";



}
