package com.ev.evtron.viewmodel;

import android.content.Context;

import com.ev.evtron.datamanager.SlotBookingDataManager;
import com.ev.evtron.interfaces.AppConstants;
import com.ev.evtron.interfaces.ResponseHandler;
import com.ev.evtron.interfaces.viewinterface.BookingSlotViewInterface;
import com.ev.evtron.interfaces.viewresponseinterface.BookingSlotViewResponseInterface;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonrequest.SlotBookingRequestModel;
import com.ev.evtron.pojo.jsonresponse.SlotBookingResponseModel;
import com.ev.evtron.utils.PreferenceManager;

import static com.facebook.FacebookSdk.getApplicationContext;

public class SlotBookingViewModel extends SlotBookingBaseViewModel implements BookingSlotViewInterface {

    private SlotBookingDataManager slotBookingDataManager;
    private BookingSlotViewResponseInterface bookingSlotViewResponseInterface;
    private Context mContext;
    private PreferenceManager preferenceManager;

    public SlotBookingViewModel(Context context, BookingSlotViewResponseInterface bookingSlotViewResponseInterface) {
        this.mContext = context;
        this.bookingSlotViewResponseInterface = bookingSlotViewResponseInterface;
        this.slotBookingDataManager = new SlotBookingDataManager();
    }


    @Override
    public void bookSlots() {
        bookingSlot();
    }

    private void bookingSlot() {

        SlotBookingRequestModel slotBookingRequestModel = new SlotBookingRequestModel();
        slotBookingRequestModel.Authorization = AppConstants.bearer + " " + getPreferenceManager().getPrefJwtToken();
        slotBookingRequestModel.chargerBayId = getChargerbayId();
        slotBookingRequestModel.date1 = getDate1();
        slotBookingRequestModel.date2 = getDate2();
        slotBookingRequestModel.slotString1 = getSlotString1();
        slotBookingRequestModel.slotString2 = getSlotString2();

        slotBookingDataManager.callgetslotBookingEnqueue(slotBookingRequestModel, new ResponseHandler<SlotBookingResponseModel>() {
            @Override
            public void onSuccess(String message) {

            }

            @Override
            public void onSuccess(SlotBookingResponseModel item, String message) {
                bookingSlotViewResponseInterface.getBookedSlotReceived(item);
            }

            @Override
            public void onFailure(ErrorBody errorBody, int statusCode) {
                bookingSlotViewResponseInterface.onFailure(errorBody, statusCode);
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
