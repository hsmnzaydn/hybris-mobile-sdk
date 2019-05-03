package com.felece.hybris_network_sdk.data.network.services;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.ApiClient;
import com.felece.hybris_network_sdk.data.network.ApiInterface;
import com.felece.hybris_network_sdk.data.network.entities.error.Error;
import com.felece.hybris_network_sdk.data.network.entities.error.ErrorList;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Inject;

import retrofit2.HttpException;

public class BaseService {

    ApiInterface apiInterface;
    Gson gson;
    ApiClient apiClient;

    public static String DEFAULT_ERROR_MESSAGE = "Something went wrong! Please try again.";
    public static String NETWORK_ERROR_MESSAGE = "Can not connect to server";

    @Inject
    public BaseService(ApiClient apiClient, Gson gson) {
        this.apiClient=apiClient;
        apiInterface = apiClient.getClient(true).create(ApiInterface.class);
        this.gson = gson;
    }

    public ApiInterface getApiInterfaceWithOutHeader(){
        return apiClient.getClient(false).create(ApiInterface.class);
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
            BufferedReader reader = null;
            StringBuilder sb = new StringBuilder();
            try {
                reader = new BufferedReader(new InputStreamReader(response.errorBody().byteStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException a) {
                a.printStackTrace();
            }
            String finallyError = sb.toString();
            ErrorList errorList=gson.fromJson(finallyError,ErrorList.class);
            if(errorList.getErrors() == null){
                error.setMessage(errorList.getErrorDescription());
            }else {
                error.setMessage(errorList.getErrors().get(0).getMessage());
            }        }
        return error;
    }


    public void getErrorCastObject(Throwable e, ServiceCallback serviceCallback) {
        Error error = new Error();
        error.setCode(0);
        if (e instanceof IOException) {
            error.setMessage(NETWORK_ERROR_MESSAGE);
        } else if (!(e instanceof HttpException)) {
            error.setMessage(DEFAULT_ERROR_MESSAGE);
        } else {
            retrofit2.Response<?> response = ((HttpException) e).response();
            error.setCode(response.code());
            BufferedReader reader = null;
            StringBuilder sb = new StringBuilder();
            try {
                reader = new BufferedReader(new InputStreamReader(response.errorBody().byteStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException a) {
                a.printStackTrace();
            }
            String finallyError = sb.toString();

            ErrorList errorList = gson.fromJson(finallyError, ErrorList.class);
            if (errorList.getErrors() == null) {
                error.setMessage(errorList.getErrorDescription());
            } else {
                error.setMessage(errorList.getErrors().get(0).getMessage());
            }
        }

        serviceCallback.onError(error.getCode(), error.getMessage());
    }

    public void getErrorCastObject(retrofit2.Response<?> response, ServiceCallback serviceCallback) {
        Error error = new Error();
        error.setCode(0);

        error.setCode(response.code());
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(response.errorBody().byteStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException a) {
            a.printStackTrace();
        }
        String finallyError = sb.toString();
        ErrorList errorList = gson.fromJson(finallyError, ErrorList.class);
        if (errorList.getErrors() == null) {
            error.setMessage(errorList.getErrorDescription());
        } else {
            error.setMessage(errorList.getErrors().get(0).getMessage());
        }
        serviceCallback.onError(error.getCode(), error.getMessage());
    }

}
