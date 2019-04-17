package com.felece.hybris_network_sdk.data.network.services.CatalogServices;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogList;

public interface CatalogServices {

    void getCatalogs(Class object,String field, ServiceCallback<CatalogList> catalogListServiceCallback);
}
