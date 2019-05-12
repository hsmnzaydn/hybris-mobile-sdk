package com.felece.hybris_network_sdk.data;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.ApiServices;
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
import com.felece.hybris_network_sdk.data.network.entities.order.OrderHistoryList;
import com.felece.hybris_network_sdk.data.network.entities.order.PaymentDetails;
import com.felece.hybris_network_sdk.data.network.entities.product.Product;
import com.felece.hybris_network_sdk.data.network.entities.product.ProductBase;
import com.felece.hybris_network_sdk.data.network.entities.search.facetdata.ProductSearchPage;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;
import com.felece.hybris_network_sdk.data.network.entities.user.AddressList;
import com.felece.hybris_network_sdk.data.network.entities.user.CountryList;
import com.felece.hybris_network_sdk.data.network.entities.user.User;
import com.felece.hybris_network_sdk.data.network.entities.user.UserSignUp;
import com.felece.hybris_network_sdk.data.network.entities.voucher.Voucher;
import com.felece.hybris_network_sdk.data.network.entities.voucher.VoucherList;
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
    public void auth(Class object, final String username, String password, final ServiceCallback<UserInformation> userInformationServiceCallback) {
        if (object == null) {
            apiServices.auth(UserInformation.class, username, password, new ServiceCallback<UserInformation>() {
                @Override
                public void onSuccess(UserInformation response) {
                    prefHelper.saveUserId(username);
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
    public void getUserProfile(Class object,  ServiceCallback<User> userInformationServiceCallback) {
        if(object == null){
            apiServices.getUserProfile(User.class,prefHelper.getUserId(),userInformationServiceCallback);
        }else {
            apiServices.getUserProfile(object,prefHelper.getUserId(),userInformationServiceCallback);
        }
    }

    @Override
    public void register(Class object, String field, UserSignUp user, ServiceCallback<User> userServiceCallback) {
        if(object == null){
            apiServices.register(User.class,field,user,userServiceCallback);
        }else {
            apiServices.register(object,field,user,userServiceCallback);
        }
    }

    @Override
    public void getUserId(ServiceCallback<String> getUserIdServiceCallback) {
        getUserIdServiceCallback.onSuccess(prefHelper.getUserId());
    }

    @Override
    public void deleteUser(Class object,  ServiceCallback<Integer> serviceCallback) {
        apiServices.deleteUser(object,prefHelper.getUserId(),serviceCallback);
    }

    @Override
    public void updateProfile(Class object,  User user, ServiceCallback<User> userServiceCallback) {
        if(object == null){
            apiServices.updateProfile(User.class,prefHelper.getUserId(),user,userServiceCallback);
        }else {
            apiServices.updateProfile(object,prefHelper.getUserId(),user,userServiceCallback);
        }
    }

    @Override
    public void getUserAdresses(Class object, String fields,ServiceCallback<AddressList> addressListServiceCallback) {
        if(object == null){
            apiServices.getUserAdresses(AddressList.class,prefHelper.getUserId(),fields,addressListServiceCallback);
        }else {
            apiServices.getUserAdresses(object,prefHelper.getUserId(),fields,addressListServiceCallback);
        }
    }

    @Override
    public void createNewUserAdress(Class object, Address address, ServiceCallback<Address> addressServiceCallback) {
        if(object == null){
            apiServices.createAdress(Address.class,address,prefHelper.getUserId(),addressServiceCallback);
        }else {
            apiServices.createAdress(null,address,prefHelper.getUserId(),addressServiceCallback);
        }
    }

    @Override
    public void deleteUserAdress( String adressId, ServiceCallback<Address> addressServiceCallback) {
        apiServices.deleteUserAdress(prefHelper.getUserId(),adressId,addressServiceCallback);
    }

    @Override
    public void getUserAdress(Class object,  String adressId, ServiceCallback<Address> addressServiceCallback) {
        if(object == null){
            apiServices.getUserAdress(Address.class,prefHelper.getUserId(),adressId,addressServiceCallback);
        }else {
            apiServices.getUserAdress(null,prefHelper.getUserId(),adressId,addressServiceCallback);
        }
    }

    @Override
    public void updateUserAddress(Class object, String addressId, Address address, ServiceCallback<Address> addressServiceCallback) {
        if(object == null){
            apiServices.updateUserAddress(Address.class,prefHelper.getUserId(),addressId,address,addressServiceCallback);
        }else {
            apiServices.updateUserAddress(null,prefHelper.getUserId(),addressId,address,addressServiceCallback);
        }
    }

    @Override
    public void updateUserLoginId(String newUserId,  String password, ServiceCallback<UserInformation> userInformationServiceCallback) {
        apiServices.updateUserLoginName(newUserId,prefHelper.getUserId(),password,userInformationServiceCallback);
    }

    @Override
    public void updateUserPassword(String oldPassword, String newPassword,  ServiceCallback<UserInformation> userInformationServiceCallback) {
        apiServices.updateUserPassword(oldPassword,newPassword,prefHelper.getUserId(),userInformationServiceCallback);
    }

    @Override
    public void getHistoryOrdersOfUser(Class object,  ServiceCallback<OrderHistoryList> orderHistoryListServiceCallback) {
        if(object == null){
            apiServices.getHistoryOrdersOfUser(OrderHistoryList.class,prefHelper.getUserId(),orderHistoryListServiceCallback);
        }else {
            apiServices.getHistoryOrdersOfUser(object,prefHelper.getUserId(),orderHistoryListServiceCallback);
        }
    }

    @Override
    public void getCarts(Class object, String field, Boolean savedCartsOnly, Integer currentPage, Integer pageSize, String sort,  ServiceCallback<CartList> cartListServiceCallback) {
            if(object== null){
                apiServices.getCarts(CartList.class,field,savedCartsOnly,currentPage,pageSize,sort,prefHelper.getUserId(),cartListServiceCallback);
            }else {
                apiServices.getCarts(object,field,savedCartsOnly,currentPage,pageSize,sort,prefHelper.getUserId(),cartListServiceCallback);
            }
    }

    @Override
    public void createOrUpdateCart(Class object, String field, Cart cart,String oldCartId, String toMergeCartGuid,  ServiceCallback<Cart> cartServiceCallback) {
        if(object == null){
            apiServices.createOrUpdateCart(Cart.class,cart,field,oldCartId,toMergeCartGuid,prefHelper.getUserId(),cartServiceCallback);
        }else {
            apiServices.createOrUpdateCart(object,cart,field,oldCartId,toMergeCartGuid,prefHelper.getUserId(),cartServiceCallback);
        }
    }

    @Override
    public void deleteCart( String cartId, ServiceCallback<Cart> cartServiceCallback) {
        apiServices.deleteCart(prefHelper.getUserId(),cartId,cartServiceCallback);
    }

    @Override
    public void addEntryToCart(Class object, OrderEntry product, String cartId,  ServiceCallback<CartModification> productServiceCallback) {
        if(object == null){
            apiServices.addEntryToCart(CartModification.class,product,cartId,prefHelper.getUserId(),productServiceCallback);
        }else {
            apiServices.addEntryToCart(object,product,cartId,prefHelper.getUserId(),productServiceCallback);
        }
    }

    @Override
    public void getCart(Class object, String field,  String cartId, ServiceCallback<Cart> cartServiceCallback) {
        if(object==null){
            apiServices.getCart(Cart.class,field,prefHelper.getUserId(),cartId,cartServiceCallback);
        }else {
            apiServices.getCart(object,field,prefHelper.getUserId(),cartId,cartServiceCallback);
        }
    }

    @Override
    public void deleteDeliveryAddresOfCart( String cartId, ServiceCallback<Cart> cartServiceCallback) {
        apiServices.deleteDeliveryAddresOfCart(prefHelper.getUserId(),cartId,cartServiceCallback);
    }

    @Override
    public void createDeliveryAddresForCart(Class object, Address address, String cartId, ServiceCallback<Cart> cartServiceCallback) {
        if(object== null){
            apiServices.createDeliveryAddresForCart(Cart.class,address,prefHelper.getUserId(),cartId,cartServiceCallback);
        }else {
            apiServices.createDeliveryAddresForCart(object,address,prefHelper.getUserId(),cartId,cartServiceCallback);
        }
    }

    @Override
    public void setDeliveryAddresToCart( String cartId, String addressId, ServiceCallback<Cart> cartServiceCallback) {
        apiServices.setDeliveryAddresToCart(prefHelper.getUserId(),cartId,addressId,cartServiceCallback);
    }

    @Override
    public void getEntriesOfCart(Class object, String field,  String cartId, ServiceCallback<OrderEntryList> orderEntryListServiceCallback) {
        if(object == null){
            apiServices.getEntriesOfCart(OrderEntryList.class,field,prefHelper.getUserId(),cartId,orderEntryListServiceCallback);
        }else {
            apiServices.getEntriesOfCart(object,field,prefHelper.getUserId(),cartId,orderEntryListServiceCallback);
        }
    }

    @Override
    public void deleteDeliveryModeFromCart( String cartId, ServiceCallback<DeliveryMode> deliveryModeServiceCallback) {
        apiServices.deleteDeliveryModeFromCart(prefHelper.getUserId(),cartId,deliveryModeServiceCallback);
    }

    @Override
    public void getDeliveryModeOfCart(Class object,String field,  String cartId, ServiceCallback<DeliveryMode> deliveryModeServiceCallback) {
        if(object==null){
            apiServices.getDeliveryModeOfCart(DeliveryMode.class,field,prefHelper.getUserId(),cartId,deliveryModeServiceCallback);
        }else {
            apiServices.getDeliveryModeOfCart(object,field,prefHelper.getUserId(),cartId,deliveryModeServiceCallback);
        }
    }

    @Override
    public void getDeliveryModesOfCart(Class object, String field,  String cartId, ServiceCallback<DeliveryModeList> deliveryModeListServiceCallback) {
        if(object==null){
            apiServices.getDeliveryModesOfCart(DeliveryModeList.class,field,prefHelper.getUserId(),cartId,deliveryModeListServiceCallback);
        }else {
            apiServices.getDeliveryModesOfCart(object,field,prefHelper.getUserId(),cartId,deliveryModeListServiceCallback);
        }
    }

    @Override
    public void deleteEntryFromCart( String cartId, int entryId, ServiceCallback<Entry> entryServiceCallback) {
        apiServices.deleteEntryFromCart(prefHelper.getUserId(),cartId,entryId,entryServiceCallback);
    }

    @Override
    public void getVouchersOfCart(Class object, String field, String cartId, ServiceCallback<VoucherList> voucherListServiceCallback) {
        if(object == null){
            apiServices.getVouchersOfCart(VoucherList.class,field,prefHelper.getUserId(),cartId,voucherListServiceCallback);
        }else {
            apiServices.getVouchersOfCart(object,field,prefHelper.getUserId(),cartId,voucherListServiceCallback);

        }
    }

    @Override
    public void addVoucherToCart(String cartId, String voucherId, ServiceCallback<Voucher> voucherServiceCallback) {
        apiServices.addVoucherToCart(prefHelper.getUserId(),cartId,voucherId,voucherServiceCallback);
    }

    @Override
    public void addPaymentDetailToCart(Class object, String field, String cartId, PaymentDetails paymentDetails, ServiceCallback<PaymentDetails> paymentDetailsServiceCallback) {
        if(object == null){
            apiServices.addPaymentDetailToCart(PaymentDetails.class,field,prefHelper.getUserId(),cartId,paymentDetails,paymentDetailsServiceCallback);
        }else {
            apiServices.addPaymentDetailToCart(object,field,prefHelper.getUserId(),cartId,paymentDetails,paymentDetailsServiceCallback);
        }
    }

    @Override
    public void searchProduct(Class object, String query, Integer currentPage, Integer pageSize, String sort, String fields, String searchQueryContext, ServiceCallback<ProductSearchPage> productSearchPageServiceCallback) {
        if(object==null){
            apiServices.searchProduct(ProductSearchPage.class,query,currentPage,pageSize,sort,fields,searchQueryContext,productSearchPageServiceCallback);
        }else {
            apiServices.searchProduct(object,query,currentPage,pageSize,sort,fields,searchQueryContext,productSearchPageServiceCallback);
        }
    }

    @Override
    public void getProductDetail(Class object, String productId, String fields, ServiceCallback<ProductBase> productBaseServiceCallback) {
        if(object == null){
            apiServices.getProductDetail(ProductBase.class,productId,fields,productBaseServiceCallback);
        }else {
            apiServices.getProductDetail(object,productId,fields,productBaseServiceCallback);
        }
    }


}
