package com.felece.hybris_network_sdk.data.network.services.ApplicationServices;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.ApiClient;
import com.felece.hybris_network_sdk.data.network.entities.CommonResponse;
import com.felece.hybris_network_sdk.data.network.services.BaseService;
import com.google.gson.Gson;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ApplicationServicesImp extends BaseService implements ApplicationServices {


    @Inject
    public ApplicationServicesImp(ApiClient apiClient, Gson gson) {
        super(apiClient, gson);
    }

    @Override
    public void startApplication(final Class revertObject, final ServiceCallback<CommonResponse> serviceCallback) {

        getApiInterface().startApplication("sadas")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        serviceCallback.onSuccess((CommonResponse) getCastObject(o,revertObject));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }
}
