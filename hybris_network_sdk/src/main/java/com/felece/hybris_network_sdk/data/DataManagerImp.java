package com.felece.hybris_network_sdk.data;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.ApiServices;
import com.felece.hybris_network_sdk.data.network.entities.UserInformation;
import com.felece.hybris_network_sdk.data.network.entities.catalog.Catalog;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogList;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogVersion;
import com.felece.hybris_network_sdk.data.network.entities.user.CountryList;
import com.felece.hybris_network_sdk.data.network.entities.user.User;
import com.felece.hybris_network_sdk.data.pref.PrefHelper;

import javax.inject.Inject;

public class DataManagerImp implements DataManager {
    private ApiServices apiServices;
    private PrefHelper prefHelper;


    @Inject
    public DataManagerImp(ApiServices apiServices, PrefHelper prefHelper) {
        this.apiServices = apiServices;
        this.prefHelper = prefHelper;
    }

    @Override
    public void getContries(String type, String fields, Class object, ServiceCallback<CountryList> serviceCallback) {
        if (object == null) {
            apiServices.getCountries(type, fields, CountryList.class, serviceCallback);
        } else {
            apiServices.getCountries(type, fields, object, serviceCallback);
        }
    }

    @Override
    public void getCatalogs(Class object, String fields, ServiceCallback<CatalogList> serviceCallback) {
        if (object == null) {
            apiServices.getCatalogs(CatalogList.class, fields, serviceCallback);
        } else {
            apiServices.getCatalogs(object, fields, serviceCallback);
        }
    }

    @Override
    public void getCatalog(Class object, String catalogId, String field, ServiceCallback<Catalog> catalogServiceCallback) {
        if (object == null) {
            apiServices.getCatalog(Catalog.class, catalogId, field, catalogServiceCallback);
        } else {
            apiServices.getCatalog(object, catalogId, field, catalogServiceCallback);
        }
    }

    @Override
    public void getCatalogInformationOfCatalogVersion(Class object, String field, String catalogId, String catalogVersionId, ServiceCallback<CatalogVersion> catalogVersionServiceCallback) {
        if (object == null) {
            apiServices.getCatalogInformationOfCatalogVersion(CatalogVersion.class, field, catalogId, catalogVersionId, catalogVersionServiceCallback);
        } else {
            apiServices.getCatalogInformationOfCatalogVersion(object, field, catalogId, catalogVersionId, catalogVersionServiceCallback);
        }
    }

    @Override
    public void getInformationCategoryOfCatalogVersion(Class object, String field, String catalogId, String catalogVersionId, String categoryId, ServiceCallback<CatalogVersion> catalogVersionServiceCallback) {
        if (object == null) {
            apiServices.getInformationCategoryOfCatalogVersion(CatalogVersion.class, field, catalogId, catalogVersionId, categoryId, catalogVersionServiceCallback);
        } else {
            apiServices.getInformationCategoryOfCatalogVersion(object, field, catalogId, catalogVersionId, categoryId, catalogVersionServiceCallback);
        }
    }

    @Override
    public void auth(Class object, String username, String password, final ServiceCallback<UserInformation> userInformationServiceCallback) {
        if (object == null) {
            apiServices.auth(UserInformation.class, username, password, new ServiceCallback<UserInformation>() {
                @Override
                public void onSuccess(UserInformation response) {
                    prefHelper.saveUserId(response.getUserId());
                    prefHelper.saveAuthorizationKey(response.getAccess_token());
                    userInformationServiceCallback.onSuccess(response);
                }

                @Override
                public void onError(int code, String errorResponse) {
                    userInformationServiceCallback.onError(code,errorResponse);
                }
            });
        } else {
            apiServices.auth(object, username, password, new ServiceCallback<UserInformation>() {
                @Override
                public void onSuccess(UserInformation response) {
                    prefHelper.saveUserId(response.getUserId());
                    prefHelper.saveAuthorizationKey(response.getAccess_token());
                    userInformationServiceCallback.onSuccess(response);
                }

                @Override
                public void onError(int code, String errorResponse) {
                    userInformationServiceCallback.onError(code,errorResponse);

                }
            });
        }
    }

    @Override
    public void getUserProfile(Class object, String username, ServiceCallback<User> userInformationServiceCallback) {
        if(object == null){
            apiServices.getUserProfile(User.class,username,userInformationServiceCallback);
        }else {
            apiServices.getUserProfile(object,username,userInformationServiceCallback);
        }
    }


}
