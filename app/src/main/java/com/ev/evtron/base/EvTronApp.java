package com.ev.evtron.base;

import android.app.Application;
import android.content.Context;

import com.ev.evtron.http.EvTronApiClient;
import com.ev.evtron.http.EvTronApiInterface;

public class EvTronApp extends Application {

    public static Context mContext;
    public static EvTronApp mInstance;
    public static EvTronApiClient evTronApiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = this;
        evTronApiClient = new EvTronApiClient();
    }

    public static synchronized Context getContext() {
        return mContext;
    }

    public static EvTronApp getApp() {
        if (mInstance != null && mInstance instanceof EvTronApp) {
            return mInstance;
        } else {
            mInstance = new EvTronApp();
            mInstance.onCreate();
            return mInstance;
        }
    }

    public EvTronApiInterface getRetrofitInterface() {
        return evTronApiClient.evtronApiInterface();
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
