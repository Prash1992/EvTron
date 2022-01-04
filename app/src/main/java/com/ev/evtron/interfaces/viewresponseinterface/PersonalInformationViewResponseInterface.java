package com.ev.evtron.interfaces.viewresponseinterface;

import com.ev.evtron.interfaces.StateViewInterface;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonresponse.GetCustomerInfoResponseModel;
import com.ev.evtron.pojo.jsonresponse.GetEvDetailsResponseModel;
import com.ev.evtron.pojo.jsonresponse.UpdateCustomerInfoResponseModel;

import java.util.List;

public interface PersonalInformationViewResponseInterface extends StateViewInterface {
    void getCustomerdetailProcessed(GetCustomerInfoResponseModel getCustomerInfoResponseModel);
    void ongetCustomerdetailFailure(ErrorBody errorBody, int statuscode);
    void customerdetailupdated(UpdateCustomerInfoResponseModel updateCustomerInfoResponseModel);
    void updateCustomerdetailFailure(ErrorBody errorBody, int statuscode);
    void getEvDetailsReceived(List<GetEvDetailsResponseModel> evDetailsResponseModel);
    void getEvDetailsErrorReceived(ErrorBody errorBody,int statusCode);

}
