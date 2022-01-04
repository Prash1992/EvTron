package com.ev.evtron.datamanager;

import android.util.Log;

import com.ev.evtron.http.EvTronApiInterface;
import com.ev.evtron.interfaces.ResponseHandler;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonrequest.SlotBookingRequestModel;
import com.ev.evtron.pojo.jsonresponse.SlotBookingResponseModel;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ev.evtron.base.EvTronApp.getApp;

public class SlotBookingDataManager {
    private final String TAG = SlotBookingDataManager.class.getSimpleName();
    private EvTronApiInterface evTronApiInterface;

    public SlotBookingDataManager() {
        this.evTronApiInterface = getApp().getRetrofitInterface();
    }

    public void callgetslotBookingEnqueue(SlotBookingRequestModel slotBookingRequestModel, final ResponseHandler<SlotBookingResponseModel> dataresponse) {
        Call<SlotBookingResponseModel> getslotBookingCall = evTronApiInterface.getSlotBookingPostCall(slotBookingRequestModel.Authorization,slotBookingRequestModel);
        getslotBookingCall.enqueue(new Callback<SlotBookingResponseModel>() {
            @Override
            public void onResponse(Call<SlotBookingResponseModel> call, Response<SlotBookingResponseModel> response) {
                /**
                 * Invoked for a received HTTP response.
                 * <p>
                 * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
                 * Call {@link Response#isSuccessful()} to determine if the response indicates success.
                 *
                 * @param call
                 * @param response
                 */
                int statusCode = response.code();
                if (response.isSuccessful()) {
                    dataresponse.onSuccess(response.body(), "SuccessModel");
                } else {
                    String serviceResponse = null;
                    try {
                        serviceResponse = response.errorBody().string();
                        ErrorBody errorBody = new Gson().fromJson(serviceResponse, ErrorBody.class);
                        dataresponse.onFailure(errorBody, statusCode);
                    } catch (JsonSyntaxException e) {
//                        dataresponse.onTokenExpired("Something went wrong - Error Code: " + statusCode);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<SlotBookingResponseModel> call, Throwable t) {
                Log.d(TAG, "onTokenExpired: " + t.getMessage());
//                dataresponse.onTokenExpired(t.getMessage());
            }
        });
    }

}
