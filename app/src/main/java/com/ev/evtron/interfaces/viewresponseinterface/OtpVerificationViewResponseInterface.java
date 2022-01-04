package com.ev.evtron.interfaces.viewresponseinterface;

import com.ev.evtron.interfaces.StateViewInterface;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonresponse.OtpVerificationResponseModel;

public interface OtpVerificationViewResponseInterface extends StateViewInterface {
    void OtpVerificationProcessed(OtpVerificationResponseModel otpVerificationResponseModel);
    void onFailure(ErrorBody errorBody, int statuscode);
}
