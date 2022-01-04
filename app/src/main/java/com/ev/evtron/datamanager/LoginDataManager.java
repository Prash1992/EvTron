package com.ev.evtron.datamanager;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.ev.evtron.http.EvTronApiInterface;
import com.ev.evtron.interfaces.ResponseHandler;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonrequest.GenerateOtpRequestModel;
import com.ev.evtron.pojo.jsonresponse.GenerateOtpResponseModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ev.evtron.base.EvTronApp.getApp;

public class LoginDataManager {

    private final String TAG = LoginDataManager.class.getSimpleName();
    private EvTronApiInterface evTronApiInterface;

    public LoginDataManager() {
        this.evTronApiInterface = getApp().getRetrofitInterface();
    }

    public void callEnqueue( String url,GenerateOtpRequestModel generateOtpRequestModel, final ResponseHandler<GenerateOtpResponseModel> dataresponse) {
        Call<GenerateOtpResponseModel> userLoginCall = evTronApiInterface.generateotpPostCall(url,generateOtpRequestModel);
        userLoginCall.enqueue(new Callback<GenerateOtpResponseModel>() {
            @Override
            public void onResponse(Call<GenerateOtpResponseModel> call, Response<GenerateOtpResponseModel> response) {
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
            public void onFailure(Call<GenerateOtpResponseModel> call, Throwable t) {
                Log.d(TAG, "onTokenExpired: " + t.getMessage());
//                dataresponse.onTokenExpired(t.getMessage());
            }
        });
    }

}
