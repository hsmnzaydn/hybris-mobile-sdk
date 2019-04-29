package com.felece.hybris_network_sdk.data.network;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.UserInformation;
import com.felece.hybris_network_sdk.data.network.entities.catalog.Catalog;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogList;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogVersion;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;
import com.felece.hybris_network_sdk.data.network.entities.user.AddressList;
import com.felece.hybris_network_sdk.data.network.entities.user.CountryList;
import com.felece.hybris_network_sdk.data.network.entities.user.User;
import com.felece.hybris_network_sdk.data.network.services.CatalogServices.CatalogServices;
import com.felece.hybris_network_sdk.data.network.services.CountriesServices.CountriesServices;
import com.felece.hybris_network_sdk.data.network.services.UserServices.UserServices;

import javax.inject.Inject;

public class ApiServicesImp implements ApiServices {
    CountriesServices countriesServices;
    CatalogServices catalogServices;
    UserServices userServices;

    @Inject
    public ApiServicesImp(CountriesServices countriesServices, CatalogServices catalogServices,UserServices userServices) {
        this.countriesServices = countriesServices;
        this.catalogServices = catalogServices;
        this.userServices=userServices;
    }

    @Override
    public void getCountries(String type, String fields, Class object, ServiceCallback<CountryList> serviceCallback) {
        countriesServices.getCountries(type, fields, object, serviceCallback);
    }

    @Override
    public void getCatalogs(Class object, String field, ServiceCallback<CatalogList> catalogListServiceCallback) {
        catalogServices.getCatalogs(object, field, catalogListServiceCallback);
    }

    @Override
    public void getCatalog(Class object, String catalogId, String field, ServiceCallback<Catalog> catalogServiceCallback) {
        catalogServices.getCatalog(object, catalogId, field, catalogServiceCallback);
    }

    @Override
    public void getCatalogInformationOfCatalogVersion(Class object, String field, String catalogId, String catalogVersionId, ServiceCallback<CatalogVersion> catalogVersionServiceCallback) {
        catalogServices.getCatalogInformationOfCatalogVersion(object, field, catalogId, catalogVersionId, catalogVersionServiceCallback);
    }

    @Override
    public void getInformationCategoryOfCatalogVersion(Class object, String field, String catalogId, String catalogVersionId, String categoryId, ServiceCallback<CatalogVersion> catalogVersionServiceCallback) {
        catalogServices.getInformationCategoryOfCatalogVersion(object,field,catalogId,catalogVersionId,categoryId,catalogVersionServiceCallback);
    }

    @Override
    public void auth(Class object, String userName, String password, ServiceCallback<UserInformation> userInformationServiceCallback) {
        userServices.auth(object,userName,password,userInformationServiceCallback);
    }

    @Override
    public void getUserProfile(Class object, String userName, ServiceCallback<User> userInformationServiceCallback) {
        userServices.getUserProfile(object,userName,userInformationServiceCallback);
    }

    @Override
    public void deleteUser(Class object, String userId, ServiceCallback<Integer> serviceCallback) {
        userServices.deleteUser(object,userId,serviceCallback);
    }

    @Override
    public void updateProfile(Class object, String userId, User user, ServiceCallback<User> userServiceCallback) {
        userServices.updateProfile(object,userId,user,userServiceCallback);
    }

    @Override
    public void getUserAdress(Class object, String userId, ServiceCallback<AddressList> addressListServiceCallback) {
        userServices.getUserAdress(object,userId,addressListServiceCallback);
    }

    @Override
    public void createAdress(Class object, Address address, String userId, ServiceCallback<Address> addressServiceCallback) {
        userServices.createAdress(object,address,userId,addressServiceCallback);
    }

    @Override
    public void deleteUserAdress(String userId, String adressId, ServiceCallback<Address> addressServiceCallback) {
        userServices.deleteUserAdress(userId,adressId,addressServiceCallback);
    }
}
