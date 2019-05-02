package com.felece.hybris_network_sdk.data;


import android.app.Service;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.UserInformation;
import com.felece.hybris_network_sdk.data.network.entities.catalog.Catalog;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogList;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogVersion;
import com.felece.hybris_network_sdk.data.network.entities.order.Cart;
import com.felece.hybris_network_sdk.data.network.entities.order.CartList;
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
    void getUserAdresses(Class object, String userId, ServiceCallback<AddressList> addressListServiceCallback);
    void createNewUserAdress(Class object, String userId, Address address,ServiceCallback<Address> addressServiceCallback);
    void deleteUserAdress(String userId,String adressId,ServiceCallback<Address> addressServiceCallback);
    void getUserAdress(Class object, String userId, String adressId, ServiceCallback<Address> addressServiceCallback);
    void updateUserAddress(Class object,String userId,String addressId,Address address,ServiceCallback<Address> addressServiceCallback);
    void updateUserLoginId(String newUserId, String oldUserId, String password, ServiceCallback<UserInformation> userInformationServiceCallback);
    void updateUserPassword(String oldPassword,String newPassword,String userId,ServiceCallback<UserInformation> userInformationServiceCallback);


    void getCarts(Class object, String field, Boolean savedCartsOnly, Integer currentPage, Integer pageSize, String sort, String userId, ServiceCallback<CartList> cartListServiceCallback);
    void createOrUpdateCart(Class object, String field, Cart cart,String oldCartId, String toMergeCartGuid, String userId, ServiceCallback<Cart> cartServiceCallback);
    void deleteCart(String userId,String cartId,ServiceCallback<Cart> cartServiceCallback);
    void getCart(Class object,String field,String userId,String cartId,ServiceCallback<Cart> cartServiceCallback);
    void deleteDeliveryAddresOfCart(String userId,String cartId,ServiceCallback<Cart> cartServiceCallback);
    void createDeliveryAddresForCart(Class object, Address address,String userId, String cartId, ServiceCallback<Cart> cartServiceCallback);
    void setDeliveryAddresToCart(String userId,String cartId,String addressId,ServiceCallback<Cart> cartServiceCallback);


}
