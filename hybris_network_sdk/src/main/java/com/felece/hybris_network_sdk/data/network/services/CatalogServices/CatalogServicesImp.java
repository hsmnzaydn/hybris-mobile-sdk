package com.felece.hybris_network_sdk.data.network.services.CatalogServices;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.ApiClient;
import com.felece.hybris_network_sdk.data.network.entities.catalog.Catalog;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogList;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogVersion;
import com.felece.hybris_network_sdk.data.network.services.BaseService;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

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
                        catalogListServiceCallback.onError(getErrorCastObject(e).getCode(),getErrorCastObject(e).getMessage());

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

    @Override
    public void getCatalogInformationOfCatalogVersion(final Class object, String field, String catalogId, String catalogVersionId, final ServiceCallback<CatalogVersion> catalogVersionServiceCallback) {
        getApiInterface().getInformationOfCatalogId(catalogId,catalogVersionId,field)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        catalogVersionServiceCallback.onSuccess((CatalogVersion) getCastObject(o,object));

                    }

                    @Override
                    public void onError(Throwable e) {
                        catalogVersionServiceCallback.onError(getErrorCastObject(e).getCode(),getErrorCastObject(e).getMessage());
                    }
                });
    }

    @Override
    public void getInformationCategoryOfCatalogVersion(final Class object, String field, String catalogId, String catalogVersionId, String categoryId, final ServiceCallback<CatalogVersion> catalogVersionServiceCallback) {
        getApiInterface().getInformationCategoryOfCatalogVersion(catalogId,catalogVersionId,categoryId,field)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        catalogVersionServiceCallback.onSuccess((CatalogVersion) getCastObject(o,object));

                    }

                    @Override
                    public void onError(Throwable e) {
                        catalogVersionServiceCallback.onError(getErrorCastObject(e).getCode(),getErrorCastObject(e).getMessage());
                    }
                });
    }
}
