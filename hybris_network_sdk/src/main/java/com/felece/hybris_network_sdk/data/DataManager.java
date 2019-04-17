package com.felece.hybris_network_sdk.data;


import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.CommonResponse;
import com.felece.hybris_network_sdk.data.network.entities.Count;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogList;
import com.felece.hybris_network_sdk.data.network.entities.user.Country;
import com.felece.hybris_network_sdk.data.network.entities.user.CountryList;

import java.util.List;

public interface DataManager  {
    void configurationApplication(String serverUrl, int networkReadTimeOut, int connectTimeOut);
    void getContries(String type, String fields,Class object, ServiceCallback<CountryList> serviceCallback);
    void getCatalogs(Class object, String fields, ServiceCallback<CatalogList> serviceCallback);
}
