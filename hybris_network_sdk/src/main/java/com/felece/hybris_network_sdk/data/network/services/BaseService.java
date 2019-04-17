package com.felece.hybris_network_sdk.data.network.services;

import com.felece.hybris_network_sdk.data.network.ApiClient;
import com.felece.hybris_network_sdk.data.network.ApiInterface;
import com.felece.hybris_network_sdk.data.network.entities.error.Error;
import com.felece.hybris_network_sdk.data.network.entities.error.ErrorList;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.HttpException;

public class BaseService {

    ApiInterface apiInterface;
    Gson gson;

    public static String DEFAULT_ERROR_MESSAGE = "Something went wrong! Please try again.";
    public static String NETWORK_ERROR_MESSAGE = "Can not connect to server";

    @Inject
    public BaseService(ApiClient apiClient, Gson gson) {
        apiInterface = apiClient.getClient().create(ApiInterface.class);
        this.gson = gson;
    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }

    public Gson getGson() {
        return gson;
    }

    public Object getCastObject(Object o, Class reverObject) {
        return gson.fromJson(gson.toJsonTree(o), reverObject);
    }

    public Error getErrorCastObject(Throwable e) {
        Error error = new Error();
       error.setCode(0);
        if (e instanceof IOException) {
            error.setMessage(NETWORK_ERROR_MESSAGE);
            return error;
        } else if (!(e instanceof HttpException)) {
            error.setMessage(DEFAULT_ERROR_MESSAGE);

            return error;
        } else {
            retrofit2.Response<?> response = ((HttpException) e).response();
            error.setCode(response.code());
            if (!response.isSuccessful()) {

                if (response.code() == 500) {
                    error.setMessage(DEFAULT_ERROR_MESSAGE);

                    return error;
                } else if (response.code() == 500) {
                    error.setMessage(DEFAULT_ERROR_MESSAGE);
                    return error;
                } else {

                }
            } else {
                return error;
            }


        }
        error.setMessage(DEFAULT_ERROR_MESSAGE);
        return error;

    }
}
