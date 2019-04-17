package com.felece.hybris_network_sdk.data.network.entities.enums;

public enum FIELDS {
     BASIC("BASIC"),
     DEFAULT("DEFAULT"),
     FULL("FULL");

     private String fieldType;
    FIELDS(String field) {
        this.fieldType=field;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
}
