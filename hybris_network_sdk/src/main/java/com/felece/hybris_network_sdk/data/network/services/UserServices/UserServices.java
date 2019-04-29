package com.felece.hybris_network_sdk.data.network.services.UserServices;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.CommonResponse;
import com.felece.hybris_network_sdk.data.network.entities.UserInformation;
import com.felece.hybris_network_sdk.data.network.entities.user.User;

public interface UserServices {

    void auth(Class object, String userName, String password, ServiceCallback<UserInformation> userInformationServiceCallback);
    void getUserProfile(Class object, String userName, ServiceCallback<User> userInformationServiceCallback);
    void deleteUser(Class object,String userId, ServiceCallback<Integer> serviceCallback);

}
