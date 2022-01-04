package com.ev.evtron.viewmodel;

import android.content.Context;

import com.ev.evtron.datamanager.SlotDetailsDataManager;
import com.ev.evtron.interfaces.AppConstants;
import com.ev.evtron.interfaces.ResponseHandler;
import com.ev.evtron.interfaces.viewinterface.SlotDetailViewInterface;
import com.ev.evtron.interfaces.viewresponseinterface.SlotDetailViewResponseInterface;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonrequest.GetFavouriteRequestModel;
import com.ev.evtron.pojo.jsonrequest.UpdateFavouriteRequestModel;
import com.ev.evtron.pojo.jsonresponse.GetfavouriteResponseModel;
import com.ev.evtron.pojo.jsonresponse.UpdateFavouriteResponseModel;
import com.ev.evtron.utils.PreferenceManager;

import static com.facebook.FacebookSdk.getApplicationContext;

public class SlotDetailViewModel extends SlotDetailBaseViewModel implements SlotDetailViewInterface {

    private SlotDetailsDataManager slotDetailsDataManager;
    private SlotDetailViewResponseInterface slotDetailViewResponseInterface;
    private Context mContext;
    private PreferenceManager preferenceManager;

    public SlotDetailViewModel(Context mContext, SlotDetailViewResponseInterface slotDetailViewResponseInterface) {
        this.slotDetailViewResponseInterface = slotDetailViewResponseInterface;
        this.slotDetailsDataManager = new SlotDetailsDataManager();
        this.mContext = mContext;
    }

    @Override
    public void updateFavourite() {
        updateFavouriteIcon();
    }

    @Override
    public void getFavourite() {
        getFavourites();
    }

    private void getFavourites() {
        GetFavouriteRequestModel getFavouriteRequestModel = new GetFavouriteRequestModel();
        getFavouriteRequestModel.Authorization = AppConstants.bearer + " " + getPreferenceManager().getPrefJwtToken();
        getFavouriteRequestModel.latitude = getLatitude();
        getFavouriteRequestModel.longitude = getLongitude();

        slotDetailsDataManager.getFavouriteCallEnqueue(getFavouriteRequestModel, new ResponseHandler<GetfavouriteResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(GetfavouriteResponseModel item, String message) {
                slotDetailViewResponseInterface.getFavourite(item);
            }

            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                slotDetailViewResponseInterface.onFailureGetFavourite(errorBody, statusCode);
            }
        });
    }

    private void updateFavouriteIcon() {

        UpdateFavouriteRequestModel updateFavouriteRequestModel = new UpdateFavouriteRequestModel();
        updateFavouriteRequestModel.Authorization = AppConstants.bearer + " " + getPreferenceManager().getPrefJwtToken();
        updateFavouriteRequestModel.favStationId = getFavStationId();
        updateFavouriteRequestModel.flag = getFlag();

        slotDetailsDataManager.updateFavouriteCallEnqueue(updateFavouriteRequestModel, new ResponseHandler<UpdateFavouriteResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(UpdateFavouriteResponseModel item, String message) {
                slotDetailViewResponseInterface.getFavouriteStatus(item);
            }

            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                slotDetailViewResponseInterface.onFailureUpdateFavourite(errorBody, statusCode);
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
