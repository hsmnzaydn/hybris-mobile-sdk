package com.felece.hybris_network_sdk.data.network.services.UserServices;

import com.felece.hybris_network_sdk.AppConfiguration.Configuration;
import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.ApiClient;
import com.felece.hybris_network_sdk.data.network.entities.UserInformation;
import com.felece.hybris_network_sdk.data.network.entities.user.CountryList;
import com.felece.hybris_network_sdk.data.network.services.BaseService;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class UserServicesImp extends BaseService implements UserServices {

    @Inject
    public UserServicesImp(ApiClient apiClient, Gson gson) {
        super(apiClient, gson);
    }


    @Override
    public void auth(final Class object, String userName, String password, final ServiceCallback<UserInformation> userInformationServiceCallback) {
        getApiInterface().authorization(Configuration.AUTH_REQUEST_URL,Configuration.CLIENT_ID,Configuration.CLIENT_SECRET,Configuration.GRANT_TYPE,userName,password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        userInformationServiceCallback.onSuccess((UserInformation) getCastObject(o,object));

                    }

                    @Override
                    public void onError(Throwable e) {
                        userInformationServiceCallback.onError(getErrorCastObject(e).getCode(),getErrorCastObject(e).getMessage());

                    }
                });
    }
}
