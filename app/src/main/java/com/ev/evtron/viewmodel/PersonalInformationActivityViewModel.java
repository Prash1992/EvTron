package com.ev.evtron.viewmodel;

import android.content.Context;

import com.ev.evtron.datamanager.PersonalInformationDataManager;
import com.ev.evtron.interfaces.AppConstants;
import com.ev.evtron.interfaces.ResponseHandler;
import com.ev.evtron.interfaces.viewinterface.PersonalInformationViewInterface;
import com.ev.evtron.interfaces.viewresponseinterface.PersonalInformationViewResponseInterface;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonrequest.GetCustomerInfoRequestModel;
import com.ev.evtron.pojo.jsonrequest.GetEvDetailsRequestModel;
import com.ev.evtron.pojo.jsonrequest.UpdateCustomerInfoRequestModel;
import com.ev.evtron.pojo.jsonresponse.GetCustomerInfoResponseModel;
import com.ev.evtron.pojo.jsonresponse.GetEvDetailsResponseModel;
import com.ev.evtron.pojo.jsonresponse.UpdateCustomerInfoResponseModel;
import com.ev.evtron.utils.PreferenceManager;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class PersonalInformationActivityViewModel extends PersonalInformationActivityBaseViewModel implements PersonalInformationViewInterface {
    private PersonalInformationDataManager personalInformationDataManager;
    private PersonalInformationViewResponseInterface personalInformationViewResponseInterface;
    private Context mContext;
    private PreferenceManager preferenceManager;

    public PersonalInformationActivityViewModel(Context mContext, PersonalInformationViewResponseInterface personalInformationViewResponseInterface) {
        this.personalInformationViewResponseInterface = personalInformationViewResponseInterface;
        this.personalInformationDataManager = new PersonalInformationDataManager();
        this.mContext = mContext;
    }

    @Override
    public void getCustomerDetail() {
        dogetcustomerdetail();
    }

    @Override
    public void updateCustomerInfo() {
        doupdatecustomerinfo();
    }

    @Override
    public void evDetails() {
        doGetEvDetails();
    }

    private void doGetEvDetails() {
        GetEvDetailsRequestModel getEvDetailsRequestModel = new GetEvDetailsRequestModel();
        getEvDetailsRequestModel.Authorization = AppConstants.bearer + " " + getPreferenceManager().getPrefJwtToken();

        personalInformationDataManager.getEvDetailsCallEnqueue(getEvDetailsRequestModel, new ResponseHandler<List<GetEvDetailsResponseModel>>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(List<GetEvDetailsResponseModel> item, String message) {
                personalInformationViewResponseInterface.getEvDetailsReceived(item);
            }

            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                personalInformationViewResponseInterface.getEvDetailsErrorReceived(errorBody, statusCode);
            }
        });
    }

    private void doupdatecustomerinfo() {
        UpdateCustomerInfoRequestModel updateCustomerInfoRequestModel = new UpdateCustomerInfoRequestModel();
        updateCustomerInfoRequestModel.Authorization = AppConstants.bearer + " " + getPreferenceManager().getPrefJwtToken();
//        updateCustomerInfoRequestModel.id = getId();
        updateCustomerInfoRequestModel.code = getCode();
        updateCustomerInfoRequestModel.role = getRole();
        updateCustomerInfoRequestModel.firstName = getFirstName();
        updateCustomerInfoRequestModel.lastName = getLastName();
        updateCustomerInfoRequestModel.carModel = getCarModel();
        updateCustomerInfoRequestModel.carManufacturer = getCarManufacturer();
        updateCustomerInfoRequestModel.email = getEmail();
        updateCustomerInfoRequestModel.mobile = getMobile();
        personalInformationDataManager.updatepersonalinfocallEnqueue(updateCustomerInfoRequestModel, new ResponseHandler<UpdateCustomerInfoResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(UpdateCustomerInfoResponseModel item, String message) {
                personalInformationViewResponseInterface.customerdetailupdated(item);
            }

            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                personalInformationViewResponseInterface.updateCustomerdetailFailure(errorBody, statusCode);
            }
        });


    }

    private void dogetcustomerdetail() {
        GetCustomerInfoRequestModel getCustomerInfoRequestModel = new GetCustomerInfoRequestModel();
        getCustomerInfoRequestModel.Authorization = AppConstants.bearer + " " + getPreferenceManager().getPrefJwtToken();
        // getCustomerInfoRequestModel.id = getPreferenceManager().getPrefcustomerId();


        personalInformationDataManager.getpersonalinfocallEnqueue(getCustomerInfoRequestModel, new ResponseHandler<GetCustomerInfoResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(GetCustomerInfoResponseModel item, String message) {
                personalInformationViewResponseInterface.getCustomerdetailProcessed(item);
            }

            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                personalInformationViewResponseInterface.ongetCustomerdetailFailure(errorBody, statusCode);
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
