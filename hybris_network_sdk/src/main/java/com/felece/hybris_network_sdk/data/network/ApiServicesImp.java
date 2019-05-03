package com.felece.hybris_network_sdk.data.network;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.UserInformation;
import com.felece.hybris_network_sdk.data.network.entities.catalog.Catalog;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogList;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogVersion;
import com.felece.hybris_network_sdk.data.network.entities.order.Cart;
import com.felece.hybris_network_sdk.data.network.entities.order.CartList;
import com.felece.hybris_network_sdk.data.network.entities.order.DeliveryMode;
import com.felece.hybris_network_sdk.data.network.entities.order.DeliveryModeList;
import com.felece.hybris_network_sdk.data.network.entities.order.OrderEntryList;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;
import com.felece.hybris_network_sdk.data.network.entities.user.AddressList;
import com.felece.hybris_network_sdk.data.network.entities.user.CountryList;
import com.felece.hybris_network_sdk.data.network.entities.user.User;
import com.felece.hybris_network_sdk.data.network.entities.user.UserSignUp;
import com.felece.hybris_network_sdk.data.network.services.CartServices.CartServices;
import com.felece.hybris_network_sdk.data.network.services.CatalogServices.CatalogServices;
import com.felece.hybris_network_sdk.data.network.services.CountriesServices.CountriesServices;
import com.felece.hybris_network_sdk.data.network.services.UserServices.UserServices;

import javax.inject.Inject;

public class ApiServicesImp implements ApiServices {
    CountriesServices countriesServices;
    CatalogServices catalogServices;
    UserServices userServices;
    CartServices cartServices;

    @Inject
    public ApiServicesImp(CountriesServices countriesServices, CatalogServices catalogServices,UserServices userServices
    ,CartServices cartServices) {
        this.countriesServices = countriesServices;
        this.catalogServices = catalogServices;
        this.userServices=userServices;
        this.cartServices=cartServices;
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
    public void register(Class object, String field, UserSignUp user, ServiceCallback<User> userServiceCallback) {
        userServices.register(object,field,user,userServiceCallback);
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
    public void getUserAdresses(Class object, String userId, ServiceCallback<AddressList> addressListServiceCallback) {
        userServices.getUserAdresses(object,userId,addressListServiceCallback);
    }

    @Override
    public void createAdress(Class object, Address address, String userId, ServiceCallback<Address> addressServiceCallback) {
        userServices.createAdress(object,address,userId,addressServiceCallback);
    }

    @Override
    public void deleteUserAdress(String userId, String adressId, ServiceCallback<Address> addressServiceCallback) {
        userServices.deleteUserAdress(userId,adressId,addressServiceCallback);
    }

    @Override
    public void getUserAdress(Class object, String userId, String adressId, ServiceCallback<Address> addressServiceCallback) {
        userServices.getUserAdress(object,userId,adressId,addressServiceCallback);
    }

    @Override
    public void updateUserAddress(Class object, String userId, String addressId, Address address, ServiceCallback<Address> addressServiceCallback) {
        userServices.updateUserAddress(object,userId,addressId,address,addressServiceCallback);
    }

    @Override
    public void updateUserLoginName(String newUserId, String oldUserId, String password, ServiceCallback<UserInformation> userInformationServiceCallback) {
        userServices.updateUserLoginName(newUserId,oldUserId,password,userInformationServiceCallback);
    }

    @Override
    public void updateUserPassword(String oldPassword, String newPassword, String userId, ServiceCallback<UserInformation> userInformationServiceCallback) {
        userServices.updateUserPassword(oldPassword,newPassword,userId,userInformationServiceCallback);
    }

    @Override
    public void getCarts(Class object, String field, Boolean savedCartsOnly, Integer currentPage, Integer pageSize, String sort, String userId, ServiceCallback<CartList> cartListServiceCallback) {
        cartServices.getCarts(object,field,savedCartsOnly,currentPage,pageSize,sort,userId,cartListServiceCallback);
    }

    @Override
    public void createOrUpdateCart(Class object, Cart cart,String field, String oldCartId, String toMergeCartGuid, String userId, ServiceCallback<Cart> cartServiceCallback) {
        cartServices.createOrUpdateCart(object,cart,field,oldCartId,toMergeCartGuid,userId,cartServiceCallback);
    }

    @Override
    public void deleteCart(String userId, String cartId, ServiceCallback<Cart> cartServiceCallback) {
        cartServices.deleteCart(userId,cartId,cartServiceCallback);
    }

    @Override
    public void getCart(Class object, String field, String userId, String cartId, ServiceCallback<Cart> cartServiceCallback) {
        cartServices.getCart(object,field,userId,cartId,cartServiceCallback);
    }

    @Override
    public void deleteDeliveryAddresOfCart(String userId, String cartId, ServiceCallback<Cart> cartServiceCallback) {
        cartServices.deleteDeliveryAddresOfCart(userId,cartId,cartServiceCallback);
    }

    @Override
    public void createDeliveryAddresForCart(Class object, Address address, String userId, String cartId, ServiceCallback<Cart> cartServiceCallback) {
        cartServices.createDeliveryAddresForCart(object,address,userId,cartId,cartServiceCallback);
    }

    @Override
    public void setDeliveryAddresToCart(String userId, String cartId, String addressId, ServiceCallback<Cart> cartServiceCallback) {
        cartServices.setDeliveryAddresToCart(userId,cartId,addressId,cartServiceCallback);
    }

    @Override
    public void deleteDeliveryModeFromCart(String userId, String cartId, ServiceCallback<DeliveryMode> deliveryModeServiceCallback) {
        cartServices.deleteDeliveryModeFromCart(userId,cartId,deliveryModeServiceCallback);
    }

    @Override
    public void getDeliveryModeOfCart(Class object,String field, String userId, String cartId, ServiceCallback<DeliveryMode> deliveryModeServiceCallback) {
        cartServices.getDeliveryModeOfCart(object,field,userId,cartId,deliveryModeServiceCallback);
    }

    @Override
    public void getEntriesOfCart(Class object, String field, String userId, String cartId, ServiceCallback<OrderEntryList> orderEntryListServiceCallback) {
        cartServices.getEntriesOfCart(object,field,userId,cartId,orderEntryListServiceCallback);
    }

    @Override
    public void getDeliveryModesOfCart(Class object, String field, String userId, String cartId, ServiceCallback<DeliveryModeList> deliveryModeListServiceCallback) {
        cartServices.getDeliveryModesOfCart(object,field,userId,cartId,deliveryModeListServiceCallback);
    }
}
