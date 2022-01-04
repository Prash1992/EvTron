package com.ev.evtron.viewmodel;

import android.content.Context;

import com.ev.evtron.datamanager.LoginDataManager;
import com.ev.evtron.interfaces.ResponseHandler;
import com.ev.evtron.interfaces.viewinterface.LoginViewInterface;
import com.ev.evtron.interfaces.viewresponseinterface.LoginViewResponseInterface;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonrequest.GenerateOtpRequestModel;
import com.ev.evtron.pojo.jsonresponse.GenerateOtpResponseModel;
import com.ev.evtron.utils.ApiClass;

public class LoginActivityViewModel extends LoginActivityBaseViewModel implements LoginViewInterface {

    private LoginDataManager loginDataManager;
    private LoginViewResponseInterface loginViewResponseInterface;
    private Context mContext;

    public LoginActivityViewModel(Context mContext, LoginViewResponseInterface loginViewResponseInterface) {
        this.loginViewResponseInterface = loginViewResponseInterface;
        this.loginDataManager = new LoginDataManager();
        this.mContext = mContext;
    }

    @Override
    public void generateotprequest() {
        goGenerateOtp();
    }

    private void goGenerateOtp() {

        GenerateOtpRequestModel generateOtpRequestModel = new GenerateOtpRequestModel();
        generateOtpRequestModel.flag = getFlag();
        generateOtpRequestModel.code = getCode();
        generateOtpRequestModel.mobile = getMobile();
        generateOtpRequestModel.email = getEmail();

        loginDataManager.callEnqueue(ApiClass.GENERATE_OTP_API,generateOtpRequestModel, new ResponseHandler<GenerateOtpResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(GenerateOtpResponseModel item, String message) {
                loginViewResponseInterface.generateOtpProcessed(item);
            }

            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                loginViewResponseInterface.onFailure(errorBody, statusCode);
            }
        });
    }
}
