package com.ev.evtron.interfaces.viewresponseinterface;

import com.ev.evtron.interfaces.StateViewInterface;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonresponse.GenerateOtpResponseModel;

public interface LoginViewResponseInterface extends StateViewInterface {
    void generateOtpProcessed(GenerateOtpResponseModel generateOtpResponseModel);

    void onFailure(ErrorBody errorBody, int statuscode);

}
