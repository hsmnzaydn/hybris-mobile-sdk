package com.felece.hybris_network_sdk;

public interface ServiceCallback<T> {

    void onSuccess(T response);
    void onError(int code, String errorResponse);
}
