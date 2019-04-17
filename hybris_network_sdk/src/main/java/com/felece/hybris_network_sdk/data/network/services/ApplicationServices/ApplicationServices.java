package com.felece.hybris_network_sdk.data.network.services.ApplicationServices;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.CommonResponse;
import com.felece.hybris_network_sdk.data.network.entities.user.Country;
import com.felece.hybris_network_sdk.data.network.entities.user.CountryList;

import java.util.List;

public interface ApplicationServices {

    void getCountries(Class object, ServiceCallback<CountryList> serviceCallback);
}
