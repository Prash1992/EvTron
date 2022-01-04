package com.ev.evtron.interfaces.viewresponseinterface;

import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonresponse.ReviewResponseModel;

public interface ReviewViewResponseInterface {
    void getReviewStatus(ReviewResponseModel reviewResponseModel);

    void onFailure(ErrorBody errorBody, int statuscode);

}
