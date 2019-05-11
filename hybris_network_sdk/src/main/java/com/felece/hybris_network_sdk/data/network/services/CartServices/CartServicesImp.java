package com.felece.hybris_network_sdk.data.network.services.CartServices;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.ApiClient;
import com.felece.hybris_network_sdk.data.network.entities.Entry;
import com.felece.hybris_network_sdk.data.network.entities.UserInformation;
import com.felece.hybris_network_sdk.data.network.entities.order.Cart;
import com.felece.hybris_network_sdk.data.network.entities.order.CartList;
import com.felece.hybris_network_sdk.data.network.entities.order.CartModification;
import com.felece.hybris_network_sdk.data.network.entities.order.DeliveryMode;
import com.felece.hybris_network_sdk.data.network.entities.order.DeliveryModeList;
import com.felece.hybris_network_sdk.data.network.entities.order.OrderEntry;
import com.felece.hybris_network_sdk.data.network.entities.order.OrderEntryList;
import com.felece.hybris_network_sdk.data.network.entities.product.Product;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;
import com.felece.hybris_network_sdk.data.network.entities.voucher.Voucher;
import com.felece.hybris_network_sdk.data.network.entities.voucher.VoucherList;
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
        getApiInterface().getCartList(userId, field, savedCartsOnly, currentPage, pageSize, sort).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        cartListServiceCallback.onSuccess((CartList) getCastObject(o, object));

                    }

                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e, cartListServiceCallback);

                    }

                });
    }

    @Override
    public void createOrUpdateCart(final Class object, Cart cart, String field, String oldCartId, String toMergeCartGuid, String userId, final ServiceCallback<Cart> cartServiceCallback) {
        getApiInterface().createOrUpdateCart(userId, cart, field, oldCartId, toMergeCartGuid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        cartServiceCallback.onSuccess((Cart) getCastObject(o, object));

                    }

                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e, cartServiceCallback);

                    }

                });
    }

    @Override
    public void addEntryToCart(final Class object, OrderEntry product, String cartId, String userName, final ServiceCallback<CartModification> productServiceCallback) {
        getApiInterface().addEntryToCart(userName,cartId,product).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        productServiceCallback.onSuccess((CartModification) getCastObject(o, object));

                    }

                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e, productServiceCallback);

                    }

                });
    }

    @Override
    public void deleteCart(String userId, String cartId, final ServiceCallback<Cart> cartServiceCallback) {
        getApiInterface().deleteCart(userId, cartId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        if (((Response) o).isSuccessful()) {
                            cartServiceCallback.onSuccess((Cart) getCastObject(o, Cart.class));
                        } else {
                            getErrorCastObject(((Response) o), cartServiceCallback);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e, cartServiceCallback);
                    }

                });
    }

    @Override
    public void getCart(final Class object, String field, String userId, String cartId, final ServiceCallback<Cart> cartServiceCallback) {
        getApiInterface().getCart(userId, cartId, field).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        cartServiceCallback.onSuccess((Cart) getCastObject(o, object));

                    }

                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e, cartServiceCallback);

                    }

                });
    }

    @Override
    public void deleteDeliveryAddresOfCart(String userId, String cartId, final ServiceCallback<Cart> cartServiceCallback) {
        getApiInterface().deleteDeliveryAddressOfCart(userId, cartId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        if (((Response) o).isSuccessful()) {
                            cartServiceCallback.onSuccess((Cart) getCastObject(o, Cart.class));
                        } else {
                            getErrorCastObject(((Response) o), cartServiceCallback);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e, cartServiceCallback);

                    }

                });
    }

    @Override
    public void createDeliveryAddresForCart(Class object, Address address, String userId, String cartId, final ServiceCallback<Cart> cartServiceCallback) {
        getApiInterface().addDeliveryAddressOfCart(userId, cartId, address).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        cartServiceCallback.onSuccess((Cart) getCastObject(o, Cart.class));

                    }

                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e, cartServiceCallback);

                    }

                });
    }

    @Override
    public void setDeliveryAddresToCart(String userId, String cartId, String addressId, final ServiceCallback<Cart> cartServiceCallback) {
        getApiInterface().setDeliveryAddressToCart(userId, cartId, addressId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        if (((Response) o).isSuccessful()) {
                            cartServiceCallback.onSuccess((Cart) getCastObject(o, Cart.class));
                        } else {
                            getErrorCastObject(((Response) o), cartServiceCallback);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e, cartServiceCallback);

                    }

                });
    }

    @Override
    public void deleteDeliveryModeFromCart(String userId, String cartId, final ServiceCallback<DeliveryMode> deliveryModeServiceCallback) {
        getApiInterface().deleteDeliveryAddressOfCart(userId, cartId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        if (((Response) o).isSuccessful()) {
                            deliveryModeServiceCallback.onSuccess((DeliveryMode) getCastObject(o, DeliveryMode.class));
                        } else {
                            getErrorCastObject(((Response) o), deliveryModeServiceCallback);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e, deliveryModeServiceCallback);

                    }

                });
    }

    @Override
    public void getDeliveryModeOfCart(final Class object, String field, String userId, String cartId, final ServiceCallback<DeliveryMode> deliveryModeServiceCallback) {
        getApiInterface().getDeliveryModeOfCart(userId, cartId, field).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        if (((Response) o).isSuccessful()) {
                            deliveryModeServiceCallback.onSuccess((DeliveryMode) getCastObject(o, object));
                        } else {
                            getErrorCastObject(((Response) o), deliveryModeServiceCallback);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e, deliveryModeServiceCallback);

                    }

                });
    }

    @Override
    public void getEntriesOfCart(Class object, String field, String userId, String cartId, final ServiceCallback<OrderEntryList> orderEntryListServiceCallback) {
        getApiInterface().getEntriesOfCart(userId, cartId, field).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        orderEntryListServiceCallback.onSuccess((OrderEntryList) getCastObject(o, OrderEntryList.class));

                    }

                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e, orderEntryListServiceCallback);

                    }

                });
    }

    @Override
    public void deleteEntryFromCart(String userName, String cartId, int entryId, final ServiceCallback<Entry> entryServiceCallback) {
        getApiInterface().deleteEntryFromCart(userName, cartId, entryId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        if (((Response) o).isSuccessful()) {
                            entryServiceCallback.onSuccess((Entry) getCastObject(o, Entry.class));
                        } else {
                            getErrorCastObject(((Response) o), entryServiceCallback);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e, entryServiceCallback);

                    }

                });
    }

    @Override
    public void getDeliveryModesOfCart(Class object, String field, String userId, String cartId, final ServiceCallback<DeliveryModeList> deliveryModeListServiceCallback) {
        getApiInterface().getDeliveryModesOfCart(userId, cartId, field).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        deliveryModeListServiceCallback.onSuccess((DeliveryModeList) getCastObject(o, DeliveryModeList.class));

                    }

                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e, deliveryModeListServiceCallback);

                    }

                });
    }

    @Override
    public void getVouchersOfCart(Class object, String field, String userId, String cartId, final ServiceCallback<VoucherList> voucherListServiceCallback) {
        getApiInterface().getVouchersOfCart(userId,cartId,field).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        voucherListServiceCallback.onSuccess((VoucherList) getCastObject(o, VoucherList.class));

                    }

                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e, voucherListServiceCallback);

                    }

                });
    }

    @Override
    public void addVoucherToCart(String userId, String cartId, String voucherId, final ServiceCallback<Voucher> voucherServiceCallback) {
        getApiInterface().addVoucherToCart(userId, cartId, voucherId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        if (((Response) o).isSuccessful()) {
                            voucherServiceCallback.onSuccess((Voucher) getCastObject(o, Voucher.class));
                        } else {
                            getErrorCastObject(((Response) o), voucherServiceCallback);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e, voucherServiceCallback);

                    }

                });
    }
}
