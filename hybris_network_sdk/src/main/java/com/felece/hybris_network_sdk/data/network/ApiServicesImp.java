package com.felece.hybris_network_sdk.data.network;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogList;
import com.felece.hybris_network_sdk.data.network.entities.user.CountryList;
import com.felece.hybris_network_sdk.data.network.services.CatalogServices.CatalogServices;
import com.felece.hybris_network_sdk.data.network.services.CountriesServices.CountriesServices;

import javax.inject.Inject;

public class ApiServicesImp implements ApiServices {
    CountriesServices countriesServices;
    CatalogServices catalogServices;

    @Inject
    public ApiServicesImp(CountriesServices countriesServices,CatalogServices catalogServices){
        this.countriesServices = countriesServices;
        this.catalogServices=catalogServices;
    }

    @Override
    public void getCountries(String type, String fields, Class object, ServiceCallback<CountryList> serviceCallback) {
        countriesServices.getCountries(type,fields,object,serviceCallback);
    }

    @Override
    public void getCatalogs(Class object, String field, ServiceCallback<CatalogList> catalogListServiceCallback) {
        catalogServices.getCatalogs(object,field,catalogListServiceCallback);
    }
}
