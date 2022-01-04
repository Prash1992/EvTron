package com.ev.evtron.interfaces.viewresponseinterface;

import com.ev.evtron.interfaces.StateViewInterface;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonresponse.GetSlotResponseModel;

import java.util.List;

public interface GetSlotViewResponseInterface extends StateViewInterface {
    void getslotreceived(GetSlotResponseModel getSlotResponseModels);

    void onFailure(ErrorBody errorBody, int statuscode);
}
