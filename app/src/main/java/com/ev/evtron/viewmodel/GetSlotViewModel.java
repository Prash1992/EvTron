package com.ev.evtron.viewmodel;

import android.content.Context;

import com.ev.evtron.datamanager.GetChargeStationDetailsDataManager;
import com.ev.evtron.datamanager.GetSlotDataManager;
import com.ev.evtron.interfaces.AppConstants;
import com.ev.evtron.interfaces.ResponseHandler;
import com.ev.evtron.interfaces.viewinterface.GetSlotViewInterface;
import com.ev.evtron.interfaces.viewresponseinterface.GetChargeStationDetailsViewResponseInterface;
import com.ev.evtron.interfaces.viewresponseinterface.GetSlotViewResponseInterface;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonrequest.GetSlotRequestModel;
import com.ev.evtron.pojo.jsonresponse.GetChargeStationDetailsResponseModel;
import com.ev.evtron.pojo.jsonresponse.GetSlotResponseModel;
import com.ev.evtron.utils.ApiClass;
import com.ev.evtron.utils.PreferenceManager;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class GetSlotViewModel extends GetSlotBaseViewModel implements GetSlotViewInterface {

    private GetSlotDataManager getSlotDataManager;
    private GetSlotViewResponseInterface getSlotViewResponseInterface;
    private Context mContext;
    private PreferenceManager preferenceManager;

    public GetSlotViewModel(Context mContext, GetSlotViewResponseInterface getSlotViewResponseInterface) {
        this.getSlotViewResponseInterface = getSlotViewResponseInterface;
        this.getSlotDataManager = new GetSlotDataManager();
        this.mContext = mContext;
    }

    @Override
    public void getslots() {
        dogetslot();
    }

    private void dogetslot() {
        GetSlotRequestModel getSlotRequestModel = new GetSlotRequestModel();
        getSlotRequestModel.Authorization = AppConstants.bearer + " " + getPreferenceManager().getPrefJwtToken();
       // getSlotRequestModel.chargerbayId = getChargerbayId();
        getSlotRequestModel.time = getTime();
        getSlotRequestModel.chargertype = getChargerType();
        getSlotRequestModel.stationId = getStationId();


        getSlotDataManager.callgetslotEnqueue(getSlotRequestModel, new ResponseHandler<GetSlotResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(GetSlotResponseModel item, String message) {
                getSlotViewResponseInterface.getslotreceived(item);
            }

            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                getSlotViewResponseInterface.onFailure(errorBody, statusCode);
            }
        });
    }

    public PreferenceManager getPreferenceManager() {
        if (preferenceManager == null) {
            preferenceManager = PreferenceManager.getInstance();
            preferenceManager.initialize(getApplicationContext());
        }
        return preferenceManager;
    }

}
