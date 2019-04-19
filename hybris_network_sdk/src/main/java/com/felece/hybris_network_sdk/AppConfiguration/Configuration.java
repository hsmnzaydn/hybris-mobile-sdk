package com.felece.hybris_network_sdk.AppConfiguration;

public class Configuration {
    public static String SERVER_BASE_URL="https://192.168.4.27:9002/";
    public static String PROJECT_NAME_URL="mobilewebservices/v2/electronics/";
    public static String SERVER_REQUEST_URL=SERVER_BASE_URL+PROJECT_NAME_URL;


    // Network Configuration
    public static int readTimeOut=0;
    public static int connectTimeOut=10;
}
