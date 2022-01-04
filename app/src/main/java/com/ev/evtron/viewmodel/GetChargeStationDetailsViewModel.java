package com.ev.evtron.viewmodel;

import android.content.Context;

import com.ev.evtron.datamanager.GetChargeStationDetailsDataManager;
import com.ev.evtron.interfaces.ResponseHandler;
import com.ev.evtron.interfaces.viewinterface.GetChargeStationDetailsViewInterface;
import com.ev.evtron.interfaces.viewresponseinterface.GetChargeStationDetailsViewResponseInterface;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonrequest.GetChargeStationDetailsRequestModel;
import com.ev.evtron.pojo.jsonresponse.GetChargeStationDetailsResponseModel;
import com.ev.evtron.utils.ApiClass;

import java.util.List;

public class GetChargeStationDetailsViewModel extends GetChargeStationDetailsBaseViewModel implements GetChargeStationDetailsViewInterface {
    private GetChargeStationDetailsDataManager getChargeStationDetailsDataManager;
    private GetChargeStationDetailsViewResponseInterface getChargeStationDetailsViewResponseInterface;
    private Context mContext;

    public GetChargeStationDetailsViewModel(Context mContext, GetChargeStationDetailsViewResponseInterface getChargeStationDetailsViewResponseInterface) {
        this.getChargeStationDetailsViewResponseInterface = getChargeStationDetailsViewResponseInterface;
        this.getChargeStationDetailsDataManager = new GetChargeStationDetailsDataManager();
        this.mContext = mContext;
    }


    @Override
    public void getchargestationdetails() {
        dogetchargestationdetails();
    }

    private void dogetchargestationdetails() {
        GetChargeStationDetailsRequestModel getChargeStationDetailsRequestModel = new GetChargeStationDetailsRequestModel();
        getChargeStationDetailsRequestModel.latitude = getLatitude();
        getChargeStationDetailsRequestModel.longitude = getLongitude();
        getChargeStationDetailsRequestModel.distance = getDistance();
        getChargeStationDetailsRequestModel.currentlat = getCurrentlat();
        getChargeStationDetailsRequestModel.currentlong = getCurrentlong();

        getChargeStationDetailsDataManager.callgetchargedetailsEnqueue(ApiClass.GET_CHARGESTATION_DETAILS, getChargeStationDetailsRequestModel, new ResponseHandler<GetChargeStationDetailsResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(GetChargeStationDetailsResponseModel item, String message) {
                getChargeStationDetailsViewResponseInterface.chargestationdetailsreceived(item);
            }

            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                getChargeStationDetailsViewResponseInterface.onFailure(errorBody, statusCode);
            }
        });
    }
}
