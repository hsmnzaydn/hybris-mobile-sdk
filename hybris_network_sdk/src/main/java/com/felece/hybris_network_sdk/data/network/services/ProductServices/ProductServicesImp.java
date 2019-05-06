package com.felece.hybris_network_sdk.data.network.services.ProductServices;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.ApiClient;
import com.felece.hybris_network_sdk.data.network.entities.search.facetdata.ProductSearchPage;
import com.felece.hybris_network_sdk.data.network.entities.user.CountryList;
import com.felece.hybris_network_sdk.data.network.services.BaseService;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ProductServicesImp extends BaseService implements ProductServices {

    @Inject
    public ProductServicesImp(ApiClient apiClient, Gson gson) {
        super(apiClient, gson);
    }

    @Override
    public void searchProduct(final Class object, String query, Integer currentPage, Integer pageSize, String sort, String fields, String searchQueryContext, final ServiceCallback<ProductSearchPage> productSearchPageServiceCallback) {
        getApiInterface().searchProducts(query,currentPage,pageSize,sort,fields,searchQueryContext).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        productSearchPageServiceCallback.onSuccess((ProductSearchPage) getCastObject(o,object));

                    }

                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e,productSearchPageServiceCallback);

                    }
                });
    }
}
