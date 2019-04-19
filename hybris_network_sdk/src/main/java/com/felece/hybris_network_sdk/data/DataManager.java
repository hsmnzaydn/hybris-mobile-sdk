package com.felece.hybris_network_sdk.data;


import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.catalog.Catalog;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogList;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogVersion;
import com.felece.hybris_network_sdk.data.network.entities.user.CountryList;

public interface DataManager  {
    void getContries(String type, String fields,Class object, ServiceCallback<CountryList> serviceCallback);
    void getCatalogs(Class object, String fields, ServiceCallback<CatalogList> serviceCallback);
    void getCatalog(Class object, String catalogId,String field, ServiceCallback<Catalog> catalogServiceCallback);
    void getCatalogInformationOfCatalogVersion(Class object, String field, String catalogId, String catalogVersionId, ServiceCallback<CatalogVersion> catalogVersionServiceCallback);
    void getInformationCategoryOfCatalogVersion(Class object, String field, String catalogId, String catalogVersionId, String categoryId,ServiceCallback<CatalogVersion> catalogVersionServiceCallback);


}
