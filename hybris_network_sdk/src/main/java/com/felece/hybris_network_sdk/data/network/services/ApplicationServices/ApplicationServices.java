package com.felece.hybris_network_sdk.data.network.services.ApplicationServices;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.CommonResponse;

public interface ApplicationServices {

    void startApplication(Class object, ServiceCallback<CommonResponse> serviceCallback);
}
