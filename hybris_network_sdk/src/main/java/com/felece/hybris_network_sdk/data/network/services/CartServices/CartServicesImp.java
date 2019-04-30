package com.felece.hybris_network_sdk.data.network.services.CartServices;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.ApiClient;
import com.felece.hybris_network_sdk.data.network.entities.order.CartList;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;
import com.felece.hybris_network_sdk.data.network.services.BaseService;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

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
}
