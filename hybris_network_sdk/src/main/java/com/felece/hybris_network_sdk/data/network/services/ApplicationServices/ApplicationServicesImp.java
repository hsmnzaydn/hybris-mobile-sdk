package com.felece.hybris_network_sdk.data.network.services.ApplicationServices;

import android.util.Log;

import com.felece.hybris_network_sdk.data.network.ApiClient;
import com.felece.hybris_network_sdk.data.network.ApiInterface;
import com.felece.hybris_network_sdk.data.network.entities.CommonResponse;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApplicationServicesImp implements ApplicationServices {

    ApiInterface apiInterface;

    @Inject
    public ApplicationServicesImp(ApiClient apiClient) {
        apiInterface = apiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void startApplication() {
        Call<CommonResponse> call=apiInterface.startApplication("sadasdas");

        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {

            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {


            }
        });
    }
}
