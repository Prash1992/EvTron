package com.ev.evtron.datamanager;

import android.util.Log;

import com.ev.evtron.http.EvTronApiInterface;
import com.ev.evtron.interfaces.ResponseHandler;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonrequest.GetChargeStationDetailsRequestModel;
import com.ev.evtron.pojo.jsonrequest.GetSlotRequestModel;
import com.ev.evtron.pojo.jsonresponse.GetChargeStationDetailsResponseModel;
import com.ev.evtron.pojo.jsonresponse.GetSlotResponseModel;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ev.evtron.base.EvTronApp.getApp;

public class GetSlotDataManager {
    private final String TAG = GetSlotDataManager.class.getSimpleName();
    private EvTronApiInterface evTronApiInterface;

    public GetSlotDataManager() {
        this.evTronApiInterface = getApp().getRetrofitInterface();
    }

    public void callgetslotEnqueue(GetSlotRequestModel getSlotRequestModel, final ResponseHandler<GetSlotResponseModel> dataresponse) {
        Call<GetSlotResponseModel> getslotCall = evTronApiInterface.getslotPostCall(getSlotRequestModel.Authorization,getSlotRequestModel);
        getslotCall.enqueue(new Callback<GetSlotResponseModel>() {
            @Override
            public void onResponse(Call<GetSlotResponseModel> call, Response<GetSlotResponseModel> response) {
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
            public void onFailure(Call<GetSlotResponseModel> call, Throwable t) {
                Log.d(TAG, "onTokenExpired: " + t.getMessage());
//                dataresponse.onTokenExpired(t.getMessage());
            }
        });
    }

}
