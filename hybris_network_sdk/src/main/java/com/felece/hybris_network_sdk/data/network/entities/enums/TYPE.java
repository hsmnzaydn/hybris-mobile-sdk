package com.felece.hybris_network_sdk.data.network.entities.enums;

public enum TYPE {
    SHIPPING("SHIPPING"),
    BILLING("BILLING");

    private String type;
    TYPE(String type) {
        this.type=type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
