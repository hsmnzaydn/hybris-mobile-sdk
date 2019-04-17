package com.felece.hybris_network_sdk.data;


import com.felece.hybris_network_sdk.AppConfiguration.Configuration;
import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.ApiServices;

import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogList;
import com.felece.hybris_network_sdk.data.network.entities.user.CountryList;
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

    @Override
    public void getContries(String type, String fields, Class object, ServiceCallback<CountryList> serviceCallback) {
        if(object== null){
            apiServices.getCountries(type,fields,CountryList.class,serviceCallback);
        }else {
            apiServices.getCountries(type,fields,object,serviceCallback);
        }
    }

    @Override
    public void getCatalogs(Class object, String fields, ServiceCallback<CatalogList> serviceCallback) {
        if(object== null){
            apiServices.getCatalogs(CatalogList.class,fields,serviceCallback);
        }else {
            apiServices.getCatalogs(object,fields,serviceCallback);
        }
    }


}
