package com.ev.evtron.http;

import com.ev.evtron.interfaces.AppConstants;
import com.ev.evtron.pojo.jsonrequest.GenerateOtpRequestModel;
import com.ev.evtron.pojo.jsonrequest.GetChargeStationDetailsRequestModel;
import com.ev.evtron.pojo.jsonrequest.GetFavouriteRequestModel;
import com.ev.evtron.pojo.jsonrequest.GetSlotRequestModel;
import com.ev.evtron.pojo.jsonrequest.OtpVerificationRequestModel;
import com.ev.evtron.pojo.jsonrequest.ReviewRequestModel;
import com.ev.evtron.pojo.jsonrequest.SlotBookingRequestModel;
import com.ev.evtron.pojo.jsonrequest.UpdateCustomerInfoRequestModel;
import com.ev.evtron.pojo.jsonrequest.UpdateFavouriteRequestModel;
import com.ev.evtron.pojo.jsonresponse.GenerateOtpResponseModel;
import com.ev.evtron.pojo.jsonresponse.GetChargeStationDetailsResponseModel;
import com.ev.evtron.pojo.jsonresponse.GetCustomerInfoResponseModel;
import com.ev.evtron.pojo.jsonresponse.GetEvDetailsResponseModel;
import com.ev.evtron.pojo.jsonresponse.GetSlotResponseModel;
import com.ev.evtron.pojo.jsonresponse.GetfavouriteResponseModel;
import com.ev.evtron.pojo.jsonresponse.OtpVerificationResponseModel;
import com.ev.evtron.pojo.jsonresponse.ReviewResponseModel;
import com.ev.evtron.pojo.jsonresponse.SlotBookingResponseModel;
import com.ev.evtron.pojo.jsonresponse.UpdateCustomerInfoResponseModel;
import com.ev.evtron.pojo.jsonresponse.UpdateFavouriteResponseModel;
import com.ev.evtron.utils.ApiClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;

public interface EvTronApiInterface {

    @POST
    Call<GenerateOtpResponseModel>generateotpPostCall(@Url String url, @Body GenerateOtpRequestModel generateOtpRequestModel);

    @POST
    Call<GetChargeStationDetailsResponseModel> generategetchargestationdetailsPostCall(@Url String url, @Body GetChargeStationDetailsRequestModel getChargeStationDetailsRequestModel);

    @POST(ApiClass.GET_SLOTS)
    Call<GetSlotResponseModel> getslotPostCall(@Header(AppConstants.authorization)String auth, @Body GetSlotRequestModel getSlotRequestModel);

    @POST(ApiClass.GET_SLOT_BOOKING)
    Call<SlotBookingResponseModel> getSlotBookingPostCall(@Header(AppConstants.authorization)String auth, @Body SlotBookingRequestModel slotBookingRequestModel);

    @POST(ApiClass.POST_REVIEW)
    Call<ReviewResponseModel> reviewPostCall(@Header(AppConstants.authorization)String auth, @Body ReviewRequestModel reviewRequestModel);

    @POST
    Call<OtpVerificationResponseModel>otpVerificationPostCall(@Url String url, @Body OtpVerificationRequestModel otpVerificationRequestModel);

    @GET(ApiClass.GET_CUSTOMER_INFO_API)
    Call<GetCustomerInfoResponseModel>getcustomerinfogetcall(@Header(AppConstants.authorization)String auth);

    @GET(ApiClass.GET_EV_DETAILS_API)
    Call<List<GetEvDetailsResponseModel>>getEvDetailsCall(@Header(AppConstants.authorization)String auth);

    @PUT(ApiClass.UPDATE_CUSTOMER_INFO_API)
    Call<UpdateCustomerInfoResponseModel>updateCustomerInfoputCall(@Header(AppConstants.authorization)String auth, @Body UpdateCustomerInfoRequestModel updateCustomerInfoRequestModel);

    @PUT(ApiClass.UPDATE_FAVOURITE_API)
    Call<UpdateFavouriteResponseModel>updateFavouritePutCall(@Header(AppConstants.authorization)String auth, @Body UpdateFavouriteRequestModel updateFavouriteRequestModel);

    @POST(ApiClass.UPDATE_FAVOURITE_API)
    Call<GetfavouriteResponseModel>getFavouritePostCall(@Header(AppConstants.authorization)String auth, @Body GetFavouriteRequestModel getFavouriteRequestModel);

}
