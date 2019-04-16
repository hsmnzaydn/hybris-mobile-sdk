package com.felece.hybris;

import com.felece.hybris_network_sdk.data.network.entities.CommonResponse;

public class tt extends CommonResponse {
    private String aa;

    public tt(String aa) {
        this.aa = aa;
    }

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }
}
