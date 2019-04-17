package com.felece.hybris_network_sdk.data.network;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.CommonResponse;
import com.felece.hybris_network_sdk.data.network.entities.user.Country;
import com.felece.hybris_network_sdk.data.network.entities.user.CountryList;
import com.felece.hybris_network_sdk.data.network.services.ApplicationServices.ApplicationServices;

import java.util.List;

import javax.inject.Inject;

public class ApiServicesImp implements ApiServices {
    ApplicationServices applicationServices;

    @Inject
    public ApiServicesImp(ApplicationServices applicationServices){
        this.applicationServices=applicationServices;
    }



    @Override
    public void getCountries(Class object, ServiceCallback<CountryList> serviceCallback) {
        applicationServices.getCountries(object,serviceCallback);

    }


}
