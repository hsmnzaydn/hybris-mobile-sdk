package com.felece.hybris_network_sdk.data;



public interface DataManager  {
    void configurationApplication(String serverUrl, int networkReadTimeOut, int connectTimeOut);
    void startApplication();
}
