package com.felece.hybris_network_sdk.data;


import com.felece.hybris_network_sdk.AppConfiguration.Configuration;
import com.felece.hybris_network_sdk.data.network.ApiServices;
import com.felece.hybris_network_sdk.data.pref.PrefHelper;
import javax.inject.Inject;

public class DataManagerImp implements DataManager {
    private ApiServices apiServices;
    private PrefHelper prefHelper;


    @Inject
    public DataManagerImp(ApiServices apiServices, PrefHelper prefHelper) {
        this.apiServices = apiServices;
        this.prefHelper = prefHelper;
    }


    @Override
    public void configurationApplication(String serverUrl, int networkReadTimeOut, int connectTimeOut) {
        Configuration.SERVER_URL=serverUrl;
        Configuration.connectTimeOut=connectTimeOut;
        Configuration.readTimeOut=networkReadTimeOut;
    }
}
