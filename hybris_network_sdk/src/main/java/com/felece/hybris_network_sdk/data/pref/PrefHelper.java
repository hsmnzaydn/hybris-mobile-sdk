package com.felece.hybris_network_sdk.data.pref;

public interface PrefHelper {

    void saveAuthorizationKey(String authKey);
    String getAuthorizationKey();

    void saveUdid(String udid);
    String getUdid();

}
