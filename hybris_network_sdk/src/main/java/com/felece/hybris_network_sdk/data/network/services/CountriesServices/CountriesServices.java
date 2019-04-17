package com.felece.hybris_network_sdk.data.network.services.CountriesServices;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.user.CountryList;

public interface CountriesServices {

    void getCountries(String type,String fields,Class object, ServiceCallback<CountryList> serviceCallback);

}
