package com.felece.hybris_network_sdk.helper;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

public interface SSLHelper {

    SSLSocketFactory getSSLSocketFactory();

    HostnameVerifier getHostnameVerifier();
}
