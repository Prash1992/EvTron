package com.ev.evtron.viewmodel;

import android.content.Context;

import com.ev.evtron.datamanager.OtpVerificationDataManager;
import com.ev.evtron.interfaces.ResponseHandler;
import com.ev.evtron.interfaces.viewinterface.OtpVerificationViewInterface;
import com.ev.evtron.interfaces.viewresponseinterface.OtpVerificationViewResponseInterface;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonrequest.OtpVerificationRequestModel;
import com.ev.evtron.pojo.jsonresponse.OtpVerificationResponseModel;
import com.ev.evtron.utils.ApiClass;

public class OtpVerificationActivityViewModel extends OtpVerificationBaseViewModel implements OtpVerificationViewInterface {
    private OtpVerificationDataManager otpVerificationDataManager;
    private OtpVerificationViewResponseInterface otpVerificationViewResponseInterface;
    private Context mContext;

    public OtpVerificationActivityViewModel(Context mContext, OtpVerificationViewResponseInterface otpVerificationViewResponseInterface) {
        this.otpVerificationViewResponseInterface = otpVerificationViewResponseInterface;
        this.otpVerificationDataManager = new OtpVerificationDataManager();
        this.mContext = mContext;
    }


    @Override
    public void validateOtp() {
        doOtpValidation();
    }

    private void doOtpValidation() {
        OtpVerificationRequestModel otpVerificationRequestModel = new OtpVerificationRequestModel();
        otpVerificationRequestModel.flag = getFlag();
        otpVerificationRequestModel.code = getCode();
        otpVerificationRequestModel.mobile = getMobile();
        otpVerificationRequestModel.email = getEmail();
        otpVerificationRequestModel.otp = getOtp();

        otpVerificationDataManager.otpverificationcallEnqueue(ApiClass.VALIDATE_OTP_API, otpVerificationRequestModel, new ResponseHandler<OtpVerificationResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(OtpVerificationResponseModel item, String message) {
                otpVerificationViewResponseInterface.OtpVerificationProcessed(item);
            }

            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                otpVerificationViewResponseInterface.onFailure(errorBody, statusCode);
            }
        });

    }
}
