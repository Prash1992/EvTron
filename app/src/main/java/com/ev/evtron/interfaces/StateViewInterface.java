package com.ev.evtron.interfaces;

import com.ev.evtron.enums.MessageViewType;
import com.ev.evtron.enums.ViewType;

public interface StateViewInterface {

    void ShowErrorMessage(MessageViewType messageViewType, String errorMessage);
    void ShowErrorMessage(MessageViewType messageViewType, ViewType viewType, String errorMessage);
}
