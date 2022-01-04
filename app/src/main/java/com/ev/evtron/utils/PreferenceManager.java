package com.ev.evtron.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context mContext;
    private int mPrivateMode = 0;
    /*Preference manager for login*/
    private static final String PREF_NAME = "PREF_EVTRON";
    private static final String PREF_CUSTOMER_ID = "PREF_CUSTOMER_ID";
    private static final String PREF_USER_FLAG = "PREF_USER_FLAG";
    private static final String PREF_JWT_TOKEN = "PREF_JWT_TOKEN";
    private static final String PREF_IS_INITIAL_DEPLOY = "PREF_IS_INITIAL_DEPLOY";
    private static final String PREF_IS_LOGGED_IN = "PREF_IS_LOGGED_IN";
    private static final String PREF_USER_NAME = "PREF_NAME";
    private static final String PREF_USER_MOBILE_NUMBER = "PREF_USER_MOBILE_NUMBER";
    private static final String PREF_USER_MAIL_ID = "PREF_USER_MAIL_ID";

    private static PreferenceManager mInstance;

    public static PreferenceManager getInstance() {
        if (mInstance == null)
            mInstance = new PreferenceManager();

        return mInstance;
    }

    private PreferenceManager() {
    }

    public void initialize(Context context) {
        this.mContext = context;
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, mPrivateMode);
        editor = sharedPreferences.edit();
    }

    public void clearPreference() {
        //editor.clear();
        editor.remove(PREF_CUSTOMER_ID);
        editor.remove(PREF_JWT_TOKEN);
        editor.remove(PREF_USER_FLAG);
        editor.apply();
    }

    public void setPrefCustomerId(Integer prefCustomerId) {
        editor.putInt(PREF_CUSTOMER_ID, prefCustomerId);
        editor.commit();
    }

    public Integer getPrefcustomerId() {
        return sharedPreferences.getInt(PREF_CUSTOMER_ID, 0);
    }

    public void setPrefJwtToken(String prefJwtToken) {
        editor.putString(PREF_JWT_TOKEN, prefJwtToken);
        editor.commit();
    }

    public String getPrefJwtToken() {
        return sharedPreferences.getString(PREF_JWT_TOKEN, "");
    }

    public void setPrefUserFlag(String prefUserFlag) {
        editor.putString(PREF_USER_FLAG, prefUserFlag);
        editor.commit();
    }

    public String getPrefUserFlag() {
        return sharedPreferences.getString(PREF_USER_FLAG, "");
    }

    public void setPrefUserName(String prefUserName) {
        editor.putString(PREF_USER_NAME, prefUserName);
        editor.commit();
    }

    public String getPrefUserName() {
        return sharedPreferences.getString(PREF_USER_NAME, "");
    }

    public void setPrefUserMobileNumber(String prefUserMobileNumber) {
        editor.putString(PREF_USER_MOBILE_NUMBER, prefUserMobileNumber);
        editor.commit();
    }

    public String getPrefUserMobileNumber() {
        return sharedPreferences.getString(PREF_USER_MOBILE_NUMBER, "");
    }

    public void setPrefUserMailId(String prefUserMailId) {
        editor.putString(PREF_USER_MAIL_ID, prefUserMailId);
        editor.commit();
    }

    public String getPrefUserMailId() {
        return sharedPreferences.getString(PREF_USER_MAIL_ID, "");
    }

    public void setPrefIsInitialDeploy(boolean isInitialDeploy) {
        editor.putBoolean(PREF_IS_INITIAL_DEPLOY, isInitialDeploy);
        editor.commit();
    }

    public boolean getPrefIsInitialDeploy() {
        return sharedPreferences.getBoolean(PREF_IS_INITIAL_DEPLOY, true);
    }

    public void setPrefIsLoggedIn(boolean prefIsLoggedIn) {
        editor.putBoolean(PREF_IS_LOGGED_IN, prefIsLoggedIn);
        editor.commit();
    }

    public boolean getPrefIsLoggedIn() {
        return sharedPreferences.getBoolean(PREF_IS_LOGGED_IN, false);
    }

}
