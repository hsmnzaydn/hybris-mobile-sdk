package com.felece.hybris_network_sdk.data.network.services.UserServices;

import android.util.Log;

import com.felece.hybris_network_sdk.AppConfiguration.Configuration;
import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.network.ApiClient;
import com.felece.hybris_network_sdk.data.network.entities.UserInformation;
import com.felece.hybris_network_sdk.data.network.entities.error.Error;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;
import com.felece.hybris_network_sdk.data.network.entities.user.AddressList;
import com.felece.hybris_network_sdk.data.network.entities.user.User;
import com.felece.hybris_network_sdk.data.network.services.BaseService;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserServicesImp extends BaseService implements UserServices {

    @Inject
    public UserServicesImp(ApiClient apiClient, Gson gson) {
        super(apiClient, gson);
    }


    @Override
    public void auth(final Class object, String userName, String password, final ServiceCallback<UserInformation> userInformationServiceCallback) {


        getApiInterface().authorization(Configuration.AUTH_REQUEST_URL, Configuration.CLIENT_ID, Configuration.CLIENT_SECRET, Configuration.GRANT_TYPE, userName, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        userInformationServiceCallback.onSuccess((UserInformation) getCastObject(o, object));

                    }

                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e,userInformationServiceCallback);
                    }
                });


    }

    @Override
    public void getUserProfile(final Class object, String userName, final ServiceCallback<User> userInformationServiceCallback) {
        getApiInterface().getUserProfile(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        userInformationServiceCallback.onSuccess((User) getCastObject(o, object));

                    }

                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e,userInformationServiceCallback);
                    }
                });


    }

    @Override
    public void deleteUser(final Class object, String userId, final ServiceCallback<Integer> serviceCallback) {
        getApiInterface().deleteUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        serviceCallback.onSuccess((Integer) getCastObject(o, object));

                    }

                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e,serviceCallback);
                    }
                });
    }

    @Override
    public void updateProfile(final Class object, String userId, User user, final ServiceCallback<User> userServiceCallback) {

        getApiInterface().updateProfile(userId, user).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                userServiceCallback.onSuccess((User) getCastObject(response, object));

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                getErrorCastObject(t,userServiceCallback);
            }
        });
        /*getApiInterface().updateProfile(userId,user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        Log.d("veri","veri");
                        //  userServiceCallback.onSuccess((User) getCastObject(o, object));

                    }

                    @Override

                    public void onError(Throwable e) {
                        userServiceCallback.onError(getErrorCastObject(e).getCode(), getErrorCastObject(e).getMessage());

                    }
                });*/
    }

    @Override
    public void getUserAdresses(final Class object, String userId, final ServiceCallback<AddressList> addressListServiceCallback) {
        getApiInterface().getUserAdresses(userId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        addressListServiceCallback.onSuccess((AddressList) getCastObject(o, object));

                    }

                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e,addressListServiceCallback);
                    }
                });
    }

    @Override
    public void createAdress(final Class object, Address address, String userId, final ServiceCallback<Address> addressServiceCallback) {
        getApiInterface().addNewAdress(userId,address).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        addressServiceCallback.onSuccess((Address) getCastObject(o, object));

                    }

                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e,addressServiceCallback);
                    }
                });
    }

    @Override
    public void deleteUserAdress(String userId, String adressId, final ServiceCallback<Address> addressServiceCallback) {
        getApiInterface().deleteUserAdress(userId,adressId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                addressServiceCallback.onSuccess((Address) getCastObject(response,Address.class));

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                getErrorCastObject(t,addressServiceCallback);
            }
        });

    }

    @Override
    public void getUserAdress(final Class object, String userId, String adressId, final ServiceCallback<Address> addressServiceCallback) {
        getApiInterface().getUserAdress(userId,adressId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        addressServiceCallback.onSuccess((Address) getCastObject(o, object));

                    }
                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e,addressServiceCallback);
                    }

                });
    }

    @Override
    public void updateUserAddress(final Class object, String userId, String addressId, Address address, final ServiceCallback<Address> addressServiceCallback) {
        getApiInterface().updateUserAdress(userId,addressId,address).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        addressServiceCallback.onSuccess((Address) getCastObject(o, object));

                    }
                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e,addressServiceCallback);

                    }

                });
    }

    @Override
    public void updateUserLoginName(String newUserId, String oldUserId, String password, final ServiceCallback<UserInformation> userInformationServiceCallback) {
        getApiInterface().updateUserId(oldUserId,newUserId,password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        if(((Response)o).isSuccessful()){
                            userInformationServiceCallback.onSuccess((UserInformation) getCastObject(o, UserInformation.class));
                        }else {
                            getErrorCastObject(((Response) o),userInformationServiceCallback);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e,userInformationServiceCallback);
                    }

                });
    }

    @Override
    public void updateUserPassword(String oldPassword, String newPassword, String userId, final ServiceCallback<UserInformation> userInformationServiceCallback) {
        getApiInterface().updateUserPassword(userId,oldPassword,newPassword).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver() {
                    @Override
                    public void onSuccess(Object o) {
                        if(((Response)o).isSuccessful()){
                            userInformationServiceCallback.onSuccess((UserInformation) getCastObject(o, UserInformation.class));
                        }else {
                            getErrorCastObject(((Response) o),userInformationServiceCallback);
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        getErrorCastObject(e,userInformationServiceCallback);
                    }

                });
    }
}
