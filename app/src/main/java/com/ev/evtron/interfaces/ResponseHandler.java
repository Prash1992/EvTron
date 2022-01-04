package com.ev.evtron.interfaces;

import com.ev.evtron.pojo.ErrorBody;

public interface ResponseHandler<T> {
    void onSuccess(String message);

    void onSuccess(T item, String message);

    void onFailure(ErrorBody errorBody, int statusCode);
}
