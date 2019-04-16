package com.felece.hybris_network_sdk.data.network.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class CommonResponse {


    @Expose
    @SerializedName("message")
    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
