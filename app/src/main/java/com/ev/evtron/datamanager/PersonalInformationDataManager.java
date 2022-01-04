package com.ev.evtron.datamanager;

import android.util.Log;

import com.ev.evtron.pojo.jsonrequest.GetEvDetailsRequestModel;
import com.ev.evtron.pojo.jsonresponse.GetEvDetailsResponseModel;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.ev.evtron.http.EvTronApiInterface;
import com.ev.evtron.interfaces.ResponseHandler;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonrequest.GetCustomerInfoRequestModel;
import com.ev.evtron.pojo.jsonrequest.UpdateCustomerInfoRequestModel;
import com.ev.evtron.pojo.jsonresponse.GetCustomerInfoResponseModel;
import com.ev.evtron.pojo.jsonresponse.UpdateCustomerInfoResponseModel;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ev.evtron.base.EvTronApp.getApp;

public class PersonalInformationDataManager {
    private final String TAG = PersonalInformationDataManager.class.getSimpleName();
    private EvTronApiInterface evTronApiInterface;

    public PersonalInformationDataManager() {
        this.evTronApiInterface = getApp().getRetrofitInterface();
    }

    public void getpersonalinfocallEnqueue(GetCustomerInfoRequestModel getCustomerInfoRequestModel, final ResponseHandler<GetCustomerInfoResponseModel> dataresponse) {
        Call<GetCustomerInfoResponseModel> getcustomerinfoCall = evTronApiInterface.getcustomerinfogetcall(getCustomerInfoRequestModel.Authorization);
        getcustomerinfoCall.enqueue(new Callback<GetCustomerInfoResponseModel>() {
            @Override
            public void onResponse(Call<GetCustomerInfoResponseModel> call, Response<GetCustomerInfoResponseModel> response) {
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
            public void onFailure(Call<GetCustomerInfoResponseModel> call, Throwable t) {
                Log.d(TAG, "onTokenExpired: " + t.getMessage());
//                dataresponse.onTokenExpired(t.getMessage());
            }
        });
    }
    public void getEvDetailsCallEnqueue(GetEvDetailsRequestModel getEvDetailsRequestModel, final ResponseHandler<List<GetEvDetailsResponseModel>> dataresponse) {
        Call<List<GetEvDetailsResponseModel>> getEvDetailsCall = evTronApiInterface.getEvDetailsCall(getEvDetailsRequestModel.Authorization);
        getEvDetailsCall.enqueue(new Callback<List<GetEvDetailsResponseModel>>() {
            @Override
            public void onResponse(Call<List<GetEvDetailsResponseModel>> call, Response<List<GetEvDetailsResponseModel>> response) {
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
            public void onFailure(Call<List<GetEvDetailsResponseModel>> call, Throwable t) {
                Log.d(TAG, "onTokenExpired: " + t.getMessage());
//                dataresponse.onTokenExpired(t.getMessage());
            }
        });
    }
    public void updatepersonalinfocallEnqueue(UpdateCustomerInfoRequestModel updateCustomerInfoRequestModel, final ResponseHandler<UpdateCustomerInfoResponseModel> dataresponse) {
        Call<UpdateCustomerInfoResponseModel> updatecustomerinfoCall = evTronApiInterface.updateCustomerInfoputCall(updateCustomerInfoRequestModel.Authorization,updateCustomerInfoRequestModel);
        updatecustomerinfoCall.enqueue(new Callback<UpdateCustomerInfoResponseModel>() {
            @Override
            public void onResponse(Call<UpdateCustomerInfoResponseModel> call, Response<UpdateCustomerInfoResponseModel> response) {
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
            public void onFailure(Call<UpdateCustomerInfoResponseModel> call, Throwable t) {
                Log.d(TAG, "onTokenExpired: " + t.getMessage());
//                dataresponse.onTokenExpired(t.getMessage());
            }
        });
    }

}
