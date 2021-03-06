package com.felece.hybris_network_sdk.data.network.services.UserServices;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.entities.CommonResponse;
import com.felece.hybris_network_sdk.data.network.entities.UserInformation;
import com.felece.hybris_network_sdk.data.network.entities.order.OrderHistoryList;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;
import com.felece.hybris_network_sdk.data.network.entities.user.AddressList;
import com.felece.hybris_network_sdk.data.network.entities.user.User;
import com.felece.hybris_network_sdk.data.network.entities.user.UserSignUp;

public interface UserServices {

    void auth(Class object, String userName, String password, ServiceCallback<UserInformation> userInformationServiceCallback);
    void register(Class object, String field, UserSignUp user, ServiceCallback<User> userServiceCallback);
    void getUserProfile(Class object, String userName, ServiceCallback<User> userInformationServiceCallback);
    void deleteUser(Class object,String userId, ServiceCallback<Integer> serviceCallback);
    void updateProfile(Class object,String userId,User user,ServiceCallback<User> userServiceCallback);
    void getUserAdresses(Class object, String userId,String fields, ServiceCallback<AddressList> addressListServiceCallback);
    void createAdress(Class object, Address address,String userId, ServiceCallback<Address> addressServiceCallback);
    void deleteUserAdress(String userId,String adressId,ServiceCallback<Address> addressServiceCallback);
    void getUserAdress(Class object,String userId,String adressId,ServiceCallback<Address> addressServiceCallback);
    void updateUserAddress(Class object,String userId,String addressId,Address address,ServiceCallback<Address> addressServiceCallback);
    void updateUserLoginName(String newUserId,String oldUserId,String password,ServiceCallback<UserInformation> userInformationServiceCallback);
    void updateUserPassword(String oldPassword,String newPassword,String userId,ServiceCallback<UserInformation> userInformationServiceCallback);
    void getHistoryOrdersOfUser(Class object, String userId, ServiceCallback<OrderHistoryList> orderHistoryListServiceCallback);
}
