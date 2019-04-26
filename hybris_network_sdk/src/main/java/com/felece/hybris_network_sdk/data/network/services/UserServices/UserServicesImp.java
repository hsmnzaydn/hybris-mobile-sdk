package com.felece.hybris_network_sdk.data.network.services.UserServices;

import com.felece.hybris_network_sdk.AppConfiguration.Configuration;
import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.ApiClient;
import com.felece.hybris_network_sdk.data.network.entities.UserInformation;
import com.felece.hybris_network_sdk.data.network.entities.user.CountryList;
import com.felece.hybris_network_sdk.data.network.entities.user.User;
import com.felece.hybris_network_sdk.data.network.services.BaseService;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserServicesImp extends BaseService implements UserServices {

    @Inject
    public UserServicesImp(ApiClient apiClient, Gson gson) {
        super(apiClient, gson);
    }


    @Override
    public void auth(final Class object, String userName, String password, final ServiceCallback<UserInformation> userInformationServiceCallback) {
        getApiInterface().authorization(Configuration.AUTH_REQUEST_URL,Configuration.CLIENT_ID,Configuration.CLIENT_SECRET,Configuration.GRANT_TYPE,userName,password).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                userInformationServiceCallback.onSuccess((UserInformation) getCastObject(response.body(),object));

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                userInformationServiceCallback.onError(getErrorCastObject(t).getCode(),getErrorCastObject(t).getMessage());

            }
        });

    }

    @Override
    public void getUserProfile(final Class object, String userName, final ServiceCallback<User> userInformationServiceCallback) {
        getApiInterface().getUserProfile(userName).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                userInformationServiceCallback.onSuccess((User) getCastObject(response.body(),object));

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                userInformationServiceCallback.onError(getErrorCastObject(t).getCode(),getErrorCastObject(t).getMessage());

            }
        });
    }
}
