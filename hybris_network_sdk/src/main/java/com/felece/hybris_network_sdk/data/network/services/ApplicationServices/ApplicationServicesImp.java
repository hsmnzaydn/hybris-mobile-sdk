package com.felece.hybris_network_sdk.data.network.services.ApplicationServices;

import android.util.Log;

import com.felece.hybris_network_sdk.data.network.ApiClient;
import com.felece.hybris_network_sdk.data.network.ApiInterface;
import com.felece.hybris_network_sdk.data.network.entities.CommonResponse;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class ApplicationServicesImp implements ApplicationServices {

    ApiInterface apiInterface;

    @Inject
    public ApplicationServicesImp(ApiClient apiClient) {
        apiInterface = apiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void startApplication() {
        apiInterface.startApplication("sadas")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<CommonResponse>() {
                    @Override
                    public void onSuccess(CommonResponse notes) {
                        Log.d("veri", "veri");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("veri", "veri");
                    }
                });


    }
}
