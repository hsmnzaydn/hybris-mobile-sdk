package com.felece.hybris_network_sdk.data;


import android.app.Service;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.UserInformation;
import com.felece.hybris_network_sdk.data.network.entities.catalog.Catalog;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogList;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogVersion;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;
import com.felece.hybris_network_sdk.data.network.entities.user.AddressList;
import com.felece.hybris_network_sdk.data.network.entities.user.CountryList;
import com.felece.hybris_network_sdk.data.network.entities.user.User;

public interface DataManager  {
    void getContries(String type, String fields,Class object, ServiceCallback<CountryList> serviceCallback);
    void getCatalogs(Class object, String fields, ServiceCallback<CatalogList> serviceCallback);
    void getCatalog(Class object, String catalogId,String field, ServiceCallback<Catalog> catalogServiceCallback);
    void getCatalogInformationOfCatalogVersion(Class object, String field, String catalogId, String catalogVersionId, ServiceCallback<CatalogVersion> catalogVersionServiceCallback);
    void getInformationCategoryOfCatalogVersion(Class object, String field, String catalogId, String catalogVersionId, String categoryId,ServiceCallback<CatalogVersion> catalogVersionServiceCallback);


    void auth(Class object, String username, String password, ServiceCallback<UserInformation> userInformationServiceCallback);
    void getUserProfile(Class object,String username,ServiceCallback<User> userInformationServiceCallback);

    void getUserId(ServiceCallback<String> getUserIdServiceCallback);
    void deleteUser(Class object,String userId,ServiceCallback<Integer> serviceCallback);
    void updateProfile(Class object,String userId, User user,ServiceCallback<User> userServiceCallback);
    void getUserAdress(Class object, String userId, ServiceCallback<AddressList> addressListServiceCallback);
    void createNewUserAdress(Class object, String userId, Address address,ServiceCallback<Address> addressServiceCallback);
    void deleteUserAdress(String userId,String adressId,ServiceCallback<Address> addressServiceCallback);
}
