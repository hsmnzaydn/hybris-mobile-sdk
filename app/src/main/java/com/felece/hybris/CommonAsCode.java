package com.felece.hybris;

import com.felece.hybris_network_sdk.data.network.entities.CommonResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonAsCode extends CommonResponse {
    @Expose
    @SerializedName("code")
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
