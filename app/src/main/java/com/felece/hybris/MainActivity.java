package com.felece.hybris;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.UserInformation;
import com.felece.hybris_network_sdk.data.network.entities.user.User;


import javax.inject.Inject;



public class MainActivity extends AppCompatActivity {

    @Inject
    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((HybrisApp) getApplication()).getActivityComponent().injectMainActivity(this);
/*

        dataManager.auth(null, "canonlover@hybris.com", "123456", new ServiceCallback<UserInformation>() {
            @Override
            public void onSuccess(UserInformation response) {

            }

            @Override
            public void onError(int code, String errorResponse) {

            }
        });
*/




           User user=new User();
        user.setFirstName("Serkan");
        user.setLastName("sadasdsa");
        dataManager.updateProfile(null, "canonlover@hybris.com", user, new ServiceCallback<User>() {
            @Override
            public void onSuccess(User response) {

            }

            @Override
            public void onError(int code, String errorResponse) {

            }
        });


    }
}
