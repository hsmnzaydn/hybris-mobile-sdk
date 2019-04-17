package com.felece.hybris_network_sdk.data.network.services.CatalogServices;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.catalog.Catalog;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogList;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogVersion;

public interface CatalogServices {

    void getCatalogs(Class object,String field, ServiceCallback<CatalogList> catalogListServiceCallback);
    void getCatalog(Class object, String catalogId,String field, ServiceCallback<Catalog> catalogServiceCallback);
    void getCatalogInformationOfCatalogVersion(Class object, String field, String catalogId, String catalogVersionId, ServiceCallback<CatalogVersion> catalogVersionServiceCallback);
    void getInformationCategoryOfCatalogVersion(Class object, String field, String catalogId, String catalogVersionId, String categoryId,ServiceCallback<CatalogVersion> catalogVersionServiceCallback);
}
