package com.felece.hybris_network_sdk.data;


import java.io.IOException;

import retrofit2.HttpException;

public class NetworkError extends Throwable {
    public static String DEFAULT_ERROR_MESSAGE = "Something went wrong! Please try again.";
    public static String NETWORK_ERROR_MESSAGE = "Can not connect to server";

    private final Throwable error;

    public NetworkError(Throwable e) {
        super(e);
        this.error = e;
    }

    public String getMessage() {
        return error.getMessage();
    }


    public String response( ) {
        if (this.error instanceof IOException) {
            return NETWORK_ERROR_MESSAGE;
            // serviceCallback.onError(NETWORK_ERROR_MESSAGE);
        }
        else if (!(this.error instanceof HttpException)) {
            return DEFAULT_ERROR_MESSAGE;
            //serviceCallback.onError((DEFAULT_ERROR_MESSAGE));
        }
        else {
            retrofit2.Response<?> response = ((HttpException) this.error).response();
            if (!response.isSuccessful()) {
                if(response.code() == 500){
                    return DEFAULT_ERROR_MESSAGE;
                }else if(response.code()== 500){
                    return DEFAULT_ERROR_MESSAGE;
                }
                else {

                    // serviceCallback.onResponse(Utils.errorHandler(response.errorBody()));
                }
            } else {
                return DEFAULT_ERROR_MESSAGE;
            }


        }
        return DEFAULT_ERROR_MESSAGE;

    }

    public Throwable getError() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NetworkError that = (NetworkError) o;

        return error != null ? error.equals(that.error) : that.error == null;

    }

    @Override
    public int hashCode() {
        return error != null ? error.hashCode() : 0;
    }
}
