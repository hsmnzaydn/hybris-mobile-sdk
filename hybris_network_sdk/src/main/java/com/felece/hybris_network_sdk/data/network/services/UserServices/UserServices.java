package com.felece.hybris_network_sdk.data.network.services.UserServices;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.UserInformation;

public interface UserServices {

    void auth(Class object, String userName, String password, ServiceCallback<UserInformation> userInformationServiceCallback);

}
