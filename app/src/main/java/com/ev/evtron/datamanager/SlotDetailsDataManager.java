package com.ev.evtron.datamanager;

import android.util.Log;

import com.ev.evtron.http.EvTronApiInterface;
import com.ev.evtron.interfaces.ResponseHandler;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonrequest.GetFavouriteRequestModel;
import com.ev.evtron.pojo.jsonrequest.UpdateFavouriteRequestModel;
import com.ev.evtron.pojo.jsonresponse.GetfavouriteResponseModel;
import com.ev.evtron.pojo.jsonresponse.UpdateFavouriteResponseModel;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ev.evtron.base.EvTronApp.getApp;

public class SlotDetailsDataManager {

    private final String TAG = SlotDetailsDataManager.class.getSimpleName();
    private EvTronApiInterface evTronApiInterface;

    public SlotDetailsDataManager() {
        this.evTronApiInterface = getApp().getRetrofitInterface();
    }

    public void updateFavouriteCallEnqueue(UpdateFavouriteRequestModel updateFavouriteRequestModel, final ResponseHandler<UpdateFavouriteResponseModel> dataresponse) {
        Call<UpdateFavouriteResponseModel> updateFavouriteinfoCall = evTronApiInterface.updateFavouritePutCall(updateFavouriteRequestModel.Authorization,updateFavouriteRequestModel);
        updateFavouriteinfoCall.enqueue(new Callback<UpdateFavouriteResponseModel>() {
            @Override
            public void onResponse(Call<UpdateFavouriteResponseModel> call, Response<UpdateFavouriteResponseModel> response) {
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
            public void onFailure(Call<UpdateFavouriteResponseModel> call, Throwable t) {
                Log.d(TAG, "onTokenExpired: " + t.getMessage());
//                dataresponse.onTokenExpired(t.getMessage());
            }
        });
    }


    public void getFavouriteCallEnqueue(GetFavouriteRequestModel getFavouriteRequestModel, final ResponseHandler<GetfavouriteResponseModel> dataresponse) {
        Call<GetfavouriteResponseModel> getFavouriteInfoCall = evTronApiInterface.getFavouritePostCall(getFavouriteRequestModel.Authorization,getFavouriteRequestModel);
        getFavouriteInfoCall.enqueue(new Callback<GetfavouriteResponseModel>() {
            @Override
            public void onResponse(Call<GetfavouriteResponseModel> call, Response<GetfavouriteResponseModel> response) {
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
            public void onFailure(Call<GetfavouriteResponseModel> call, Throwable t) {
                Log.d(TAG, "onTokenExpired: " + t.getMessage());
//                dataresponse.onTokenExpired(t.getMessage());
            }
        });
    }

}
