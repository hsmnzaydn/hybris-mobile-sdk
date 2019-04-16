package com.felece.hybris_network_sdk.data;


import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.CommonResponse;

public interface DataManager  {
    void configurationApplication(String serverUrl, int networkReadTimeOut, int connectTimeOut);
    void startApplication(Class object, ServiceCallback<CommonResponse> serviceCallback);
}
