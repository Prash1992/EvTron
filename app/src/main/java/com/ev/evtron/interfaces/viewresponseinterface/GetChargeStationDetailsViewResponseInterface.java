package com.ev.evtron.interfaces.viewresponseinterface;

import com.ev.evtron.interfaces.StateViewInterface;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonresponse.GetChargeStationDetailsResponseModel;

import java.util.List;

public interface GetChargeStationDetailsViewResponseInterface extends StateViewInterface {

    void chargestationdetailsreceived(GetChargeStationDetailsResponseModel getChargeStationDetailsResponseModel);

    void onFailure(ErrorBody errorBody, int statuscode);
}
