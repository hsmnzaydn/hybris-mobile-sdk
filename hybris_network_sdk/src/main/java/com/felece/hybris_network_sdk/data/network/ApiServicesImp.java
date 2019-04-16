package com.felece.hybris_network_sdk.data.network;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.CommonResponse;
import com.felece.hybris_network_sdk.data.network.services.ApplicationServices.ApplicationServices;

import javax.inject.Inject;

public class ApiServicesImp implements ApiServices {
    ApplicationServices applicationServices;

    @Inject
    public ApiServicesImp(ApplicationServices applicationServices){
        this.applicationServices=applicationServices;
    }


    @Override
    public void startApplication(Class object, ServiceCallback<CommonResponse> serviceCallback) {
        applicationServices.startApplication(object,serviceCallback);

    }
}
