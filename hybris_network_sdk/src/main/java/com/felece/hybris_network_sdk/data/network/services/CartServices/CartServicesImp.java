package com.felece.hybris_network_sdk.data.network.services.CartServices;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.ApiClient;
import com.felece.hybris_network_sdk.data.network.entities.UserInformation;
import com.felece.hybris_network_sdk.data.network.entities.order.Cart;
import com.felece.hybris_network_sdk.data.network.entities.order.CartList;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;
import com.felece.hybris_network_sdk.data.network.services.BaseService;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CartServicesImp extends BaseService implements CartServices {

    @Inject
    public CartServicesImp(ApiClient apiClient, Gson gson) {
        super(apiClient, gson);
    }


    @Override
    public void getCarts(final Class object, String field, Boolean savedCartsOnly, Integer currentPage, Integer pageSize, String sort, String userId, final ServiceCallback<CartList> cartListServiceCallback) {
        getApiInterface().getCartList(userId,field,savedCartsOnly,currentPage,pageSize,sort).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        cartListServiceCallback.onSuccess((CartList) getCastObject(o, object));

                    }
                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e,cartListServiceCallback);

                    }

                });
    }

    @Override
    public void createOrUpdateCart(final Class object,Cart cart, String field, String oldCartId, String toMergeCartGuid, String userId, final ServiceCallback<Cart> cartServiceCallback) {
        getApiInterface().createOrUpdateCart(userId,cart,field,oldCartId,toMergeCartGuid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        cartServiceCallback.onSuccess((Cart) getCastObject(o, object));

                    }
                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e,cartServiceCallback);

                    }

                });
    }

    @Override
    public void deleteCart(String userId, String cartId, final ServiceCallback<Cart> cartServiceCallback) {
        getApiInterface().deleteCart(userId,cartId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        if(((Response)o).isSuccessful()){
                            cartServiceCallback.onSuccess((Cart) getCastObject(o, Cart.class));
                        }else {
                            getErrorCastObject(((Response) o),cartServiceCallback);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e,cartServiceCallback);
                    }

                });
    }

    @Override
    public void getCart(final Class object, String field, String userId, String cartId, final ServiceCallback<Cart> cartServiceCallback) {
        getApiInterface().getCart(userId,cartId,field).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        cartServiceCallback.onSuccess((Cart) getCastObject(o, object));

                    }
                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e,cartServiceCallback);

                    }

                });
    }

    @Override
    public void deleteDeliveryAddresOfCart(String userId, String cartId, final ServiceCallback<Cart> cartServiceCallback) {
        getApiInterface().deleteDeliveryAddressOfCart(userId,cartId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        if(((Response)o).isSuccessful()){
                            cartServiceCallback.onSuccess((Cart) getCastObject(o, Cart.class));
                        }else {
                            getErrorCastObject(((Response) o),cartServiceCallback);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e,cartServiceCallback);

                    }

                });
    }

    @Override
    public void createDeliveryAddresForCart(Class object, Address address, String userId, String cartId, final ServiceCallback<Cart> cartServiceCallback) {
        getApiInterface().addDeliveryAddressOfCart(userId,cartId,address).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        cartServiceCallback.onSuccess((Cart) getCastObject(o, Cart.class));

                    }
                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e,cartServiceCallback);

                    }

                });
    }

    @Override
    public void setDeliveryAddresToCart(String userId, String cartId, String addressId, final ServiceCallback<Cart> cartServiceCallback) {
        getApiInterface().setDeliveryAddressToCart(userId,cartId,addressId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        if(((Response)o).isSuccessful()){
                            cartServiceCallback.onSuccess((Cart) getCastObject(o, Cart.class));
                        }else {
                            getErrorCastObject(((Response) o),cartServiceCallback);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e,cartServiceCallback);

                    }

                });
    }
}
