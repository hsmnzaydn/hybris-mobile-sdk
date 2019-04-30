package com.felece.hybris_network_sdk.data.network.services.UserServices;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.CommonResponse;
import com.felece.hybris_network_sdk.data.network.entities.UserInformation;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;
import com.felece.hybris_network_sdk.data.network.entities.user.AddressList;
import com.felece.hybris_network_sdk.data.network.entities.user.User;

public interface UserServices {

    void auth(Class object, String userName, String password, ServiceCallback<UserInformation> userInformationServiceCallback);
    void getUserProfile(Class object, String userName, ServiceCallback<User> userInformationServiceCallback);
    void deleteUser(Class object,String userId, ServiceCallback<Integer> serviceCallback);
    void updateProfile(Class object,String userId,User user,ServiceCallback<User> userServiceCallback);
    void getUserAdresses(Class object, String userId, ServiceCallback<AddressList> addressListServiceCallback);
    void createAdress(Class object, Address address,String userId, ServiceCallback<Address> addressServiceCallback);
    void deleteUserAdress(String userId,String adressId,ServiceCallback<Address> addressServiceCallback);
    void getUserAdress(Class object,String userId,String adressId,ServiceCallback<Address> addressServiceCallback);
    void updateUserAddress(Class object,String userId,String addressId,Address address,ServiceCallback<Address> addressServiceCallback);
}
