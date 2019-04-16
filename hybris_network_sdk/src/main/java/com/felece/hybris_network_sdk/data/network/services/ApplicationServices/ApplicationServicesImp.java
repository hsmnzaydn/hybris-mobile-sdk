package com.felece.hybris_network_sdk.data.network.services.ApplicationServices;

import android.util.Log;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.ApiClient;
import com.felece.hybris_network_sdk.data.network.ApiInterface;
import com.felece.hybris_network_sdk.data.network.entities.CommonResponse;
import com.google.gson.Gson;

import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class ApplicationServicesImp implements ApplicationServices {

    ApiInterface apiInterface;
    Gson gson;

    @Inject
    public ApplicationServicesImp(ApiClient apiClient,Gson gson) {
        apiInterface = apiClient.getClient().create(ApiInterface.class);
    }



    @Override
    public void startApplication(final Class revertObject, final ServiceCallback<CommonResponse> serviceCallback) {

        apiInterface.startApplication("sadas")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        serviceCallback.onSuccess((CommonResponse) gson.fromJson(gson.toJsonTree(o),revertObject));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }
}
