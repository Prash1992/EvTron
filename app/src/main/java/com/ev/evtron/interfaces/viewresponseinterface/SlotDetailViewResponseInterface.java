package com.ev.evtron.interfaces.viewresponseinterface;

import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonresponse.GetfavouriteResponseModel;
import com.ev.evtron.pojo.jsonresponse.ReviewResponseModel;
import com.ev.evtron.pojo.jsonresponse.UpdateFavouriteResponseModel;

public interface SlotDetailViewResponseInterface {
    void getFavouriteStatus(UpdateFavouriteResponseModel updateFavouriteResponseModel);
    void getFavourite(GetfavouriteResponseModel getfavouriteResponseModel);

    void onFailureUpdateFavourite(ErrorBody errorBody, int statuscode);
    void onFailureGetFavourite(ErrorBody errorBody, int statuscode);

}
