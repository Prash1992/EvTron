package com.ev.evtron.interfaces.viewresponseinterface;

import com.ev.evtron.interfaces.StateViewInterface;
import com.ev.evtron.pojo.ErrorBody;
import com.ev.evtron.pojo.jsonresponse.SlotBookingResponseModel;

public interface BookingSlotViewResponseInterface extends StateViewInterface {
    void getBookedSlotReceived(SlotBookingResponseModel slotBookingResponseModel);

    void onFailure(ErrorBody errorBody, int statuscode);
}
