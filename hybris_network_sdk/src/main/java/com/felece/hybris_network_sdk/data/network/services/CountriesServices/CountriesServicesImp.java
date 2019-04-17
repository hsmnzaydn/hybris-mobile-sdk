package com.felece.hybris_network_sdk.data.network.services.CountriesServices;

import android.util.Log;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.ApiClient;
import com.felece.hybris_network_sdk.data.network.entities.user.CountryList;
import com.felece.hybris_network_sdk.data.network.services.BaseService;
import com.google.gson.Gson;

import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CountriesServicesImp extends BaseService implements CountriesServices {


    @Inject
    public CountriesServicesImp(ApiClient apiClient, Gson gson) {
        super(apiClient, gson);
    }


    @Override
    public void getCountries(String type,String fields,final Class object, final ServiceCallback<CountryList> serviceCallback) {
        getApiInterface().getCountries(type,fields)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        serviceCallback.onSuccess((CountryList) getCastObject(o,object));

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("veri","veri");

                    }
                });
    }


}
