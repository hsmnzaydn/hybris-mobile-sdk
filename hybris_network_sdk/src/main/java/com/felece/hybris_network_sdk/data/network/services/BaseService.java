package com.felece.hybris_network_sdk.data.network.services;

import com.felece.hybris_network_sdk.data.network.ApiClient;
import com.felece.hybris_network_sdk.data.network.ApiInterface;
import com.felece.hybris_network_sdk.data.network.entities.CommonResponse;
import com.google.gson.Gson;

import javax.inject.Inject;

public class BaseService {

    ApiInterface apiInterface;
    Gson gson;

    @Inject
    public BaseService(ApiClient apiClient, Gson gson) {
        apiInterface = apiClient.getClient().create(ApiInterface.class);
        this.gson = gson;
    }

    public ApiInterface getApiInterface(){
        return apiInterface;
    }

    public Gson getGson() {
        return gson;
    }

    public Object getCastObject(Object o,Class reverObject){
       return gson.fromJson(gson.toJsonTree(o),reverObject);
    }
}
