package com.felece.hybris_network_sdk.data;


import android.app.Service;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.Entry;
import com.felece.hybris_network_sdk.data.network.entities.UserInformation;
import com.felece.hybris_network_sdk.data.network.entities.catalog.Catalog;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogList;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogVersion;
import com.felece.hybris_network_sdk.data.network.entities.order.Cart;
import com.felece.hybris_network_sdk.data.network.entities.order.CartList;
import com.felece.hybris_network_sdk.data.network.entities.order.CartModification;
import com.felece.hybris_network_sdk.data.network.entities.order.DeliveryMode;
import com.felece.hybris_network_sdk.data.network.entities.order.DeliveryModeList;
import com.felece.hybris_network_sdk.data.network.entities.order.OrderEntry;
import com.felece.hybris_network_sdk.data.network.entities.order.OrderEntryList;
import com.felece.hybris_network_sdk.data.network.entities.product.Product;
import com.felece.hybris_network_sdk.data.network.entities.product.ProductBase;
import com.felece.hybris_network_sdk.data.network.entities.search.facetdata.ProductSearchPage;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;
import com.felece.hybris_network_sdk.data.network.entities.user.AddressList;
import com.felece.hybris_network_sdk.data.network.entities.user.CountryList;
import com.felece.hybris_network_sdk.data.network.entities.user.User;
import com.felece.hybris_network_sdk.data.network.entities.user.UserSignUp;

public interface DataManager  {
    void getContries(String type, String fields,Class object, ServiceCallback<CountryList> serviceCallback);
    void getCatalogs(Class object, String fields, ServiceCallback<CatalogList> serviceCallback);
    void getCatalog(Class object, String catalogId,String field, ServiceCallback<Catalog> catalogServiceCallback);
    void getCatalogInformationOfCatalogVersion(Class object, String field, String catalogId, String catalogVersionId, ServiceCallback<CatalogVersion> catalogVersionServiceCallback);
    void getInformationCategoryOfCatalogVersion(Class object, String field, String catalogId, String catalogVersionId, String categoryId,ServiceCallback<CatalogVersion> catalogVersionServiceCallback);


    void auth(Class object, String username, String password, ServiceCallback<UserInformation> userInformationServiceCallback);
    void getUserProfile(Class object,ServiceCallback<User> userInformationServiceCallback);
    void register(Class object, String field, UserSignUp user, ServiceCallback<User> userServiceCallback);
    void getUserId(ServiceCallback<String> getUserIdServiceCallback);
    void deleteUser(Class object,ServiceCallback<Integer> serviceCallback);
    void updateProfile(Class object, User user,ServiceCallback<User> userServiceCallback);
    void getUserAdresses(Class object, ServiceCallback<AddressList> addressListServiceCallback);
    void createNewUserAdress(Class object, Address address,ServiceCallback<Address> addressServiceCallback);
    void deleteUserAdress(String adressId,ServiceCallback<Address> addressServiceCallback);
    void getUserAdress(Class object, String adressId, ServiceCallback<Address> addressServiceCallback);
    void updateUserAddress(Class object,String addressId,Address address,ServiceCallback<Address> addressServiceCallback);
    void updateUserLoginId(String newUserId,  String password, ServiceCallback<UserInformation> userInformationServiceCallback);
    void updateUserPassword(String oldPassword,String newPassword,ServiceCallback<UserInformation> userInformationServiceCallback);


    void getCarts(Class object, String field, Boolean savedCartsOnly, Integer currentPage, Integer pageSize, String sort,  ServiceCallback<CartList> cartListServiceCallback);
    void createOrUpdateCart(Class object, String field, Cart cart,String oldCartId, String toMergeCartGuid,  ServiceCallback<Cart> cartServiceCallback);
    void deleteCart(String cartId,ServiceCallback<Cart> cartServiceCallback);
    void addEntryToCart(Class object, OrderEntry product, String cartId,  ServiceCallback<CartModification> productServiceCallback);
    void getCart(Class object,String field,String cartId,ServiceCallback<Cart> cartServiceCallback);
    void deleteDeliveryAddresOfCart(String cartId,ServiceCallback<Cart> cartServiceCallback);
    void createDeliveryAddresForCart(Class object, Address address, String cartId, ServiceCallback<Cart> cartServiceCallback);
    void setDeliveryAddresToCart(String cartId,String addressId,ServiceCallback<Cart> cartServiceCallback);
    void getEntriesOfCart(Class object, String field,  String cartId, ServiceCallback<OrderEntryList> orderEntryListServiceCallback);
    void deleteDeliveryModeFromCart( String cartId, ServiceCallback<DeliveryMode> deliveryModeServiceCallback);
    void getDeliveryModeOfCart(Class object,String field,String cartId,ServiceCallback<DeliveryMode> deliveryModeServiceCallback);
    void getDeliveryModesOfCart(Class object, String field, String cartId, ServiceCallback<DeliveryModeList> deliveryModeListServiceCallback);
    void deleteEntryFromCart( String cartId, int entryId, ServiceCallback<Entry> entryServiceCallback);




    void searchProduct(Class object, String query,
                       Integer currentPage,
                       Integer pageSize,
                       String sort,
                       String fields,
                       String searchQueryContext, ServiceCallback<ProductSearchPage> productSearchPageServiceCallback);
        void getProductDetail(Class object, String productId, String fields,ServiceCallback<ProductBase> productBaseServiceCallback);




}
