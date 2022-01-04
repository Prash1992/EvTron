package com.ev.evtron.interfaces;

import com.ev.evtron.pojo.jsonresponse.ChargeStation;
import com.ev.evtron.pojo.jsonresponse.GetChargeStationDetailsResponseModel;

public interface SlotDetailInterface {
    void slotclicked(int position, ChargeStation chargeStation);
    void slotInstantClicked(int position, ChargeStation chargeStation);
}
