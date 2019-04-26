package com.felece.hybris_network_sdk.data.pref;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

public class PrefHelperImp implements PrefHelper {
    private SharedPreferences mPrefs;
    private final String PREF_AUTH_KEY="authKey";
    private String prefFileName="";

    @Inject
    public PrefHelperImp(Context context) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public void saveAuthorizationKey(String authKey) {
        mPrefs.edit().putString(PREF_AUTH_KEY, authKey).apply();

    }

    @Override
    public String getAuthorizationKey() {
        return mPrefs.getString(PREF_AUTH_KEY,"");
    }

    @Override
    public void saveUdid(String udid) {
        mPrefs.edit().putString("UDID", udid).apply();

    }

    @Override
    public String getUdid() {
        return mPrefs.getString("UDID","");
    }

    @Override
    public void saveUserId(String userId) {
        mPrefs.edit().putString("USERID", userId).apply();

    }

    @Override
    public String getUserId() {
        return mPrefs.getString("USERID","");
    }


}
