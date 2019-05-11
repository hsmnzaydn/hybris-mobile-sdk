package com.felece.hybris_network_sdk.AppConfiguration;

public class Configuration {
    public static final String SERVER_BASE_URL="https://192.168.0.30:9002/";
   // public static final String SERVER_BASE_URL="https://192.168.4.207:9002/";
    public static final String PROJECT_NAME_URL="mobilewebservices/v2/electronics/";
    public static final String SERVER_REQUEST_URL=SERVER_BASE_URL+PROJECT_NAME_URL;

    public static final String AUTH_URL="authorizationserver/oauth/token";
    public static final String AUTH_REQUEST_URL=SERVER_BASE_URL+AUTH_URL;


    public static String CLIENT_ID="asm";
    public static String CLIENT_SECRET="secret";
    public static String GRANT_TYPE="password";

    // Network Configuration
    public static int readTimeOut=0;
    public static int connectTimeOut=10;
}
