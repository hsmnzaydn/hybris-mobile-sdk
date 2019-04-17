package com.felece.hybris_network_sdk.data.network.services.CatalogServices;

import android.util.Log;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.NetworkError;
import com.felece.hybris_network_sdk.data.network.ApiClient;
import com.felece.hybris_network_sdk.data.network.entities.catalog.Catalog;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogList;
import com.felece.hybris_network_sdk.data.network.entities.error.ErrorList;
import com.felece.hybris_network_sdk.data.network.entities.user.CountryList;
import com.felece.hybris_network_sdk.data.network.services.BaseService;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class CatalogServicesImp extends BaseService implements CatalogServices {


    @Inject
    public CatalogServicesImp(ApiClient apiClient, Gson gson) {
        super(apiClient, gson);
    }


    @Override
    public void getCatalogs(final Class object, String field, final ServiceCallback<CatalogList> catalogListServiceCallback) {
        getApiInterface().getCatalogs(field)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        catalogListServiceCallback.onSuccess((CatalogList) getCastObject(o,object));

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("veri","veri");

                    }
                });
    }

    @Override
    public void getCatalog(final Class object, String catalogId, String field, final ServiceCallback<Catalog> catalogServiceCallback) {
        getApiInterface().getCatalog(catalogId,field)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        catalogServiceCallback.onSuccess((Catalog) getCastObject(o,object));

                    }

                    @Override
                    public void onError(Throwable e) {
                       catalogServiceCallback.onError(getErrorCastObject(e).getCode(),getErrorCastObject(e).getMessage());
                    }
                });
    }
}
