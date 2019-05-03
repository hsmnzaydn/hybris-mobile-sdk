package com.felece.hybris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;


import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.UserInformation;
import com.felece.hybris_network_sdk.data.network.entities.enums.FIELDS;
import com.felece.hybris_network_sdk.data.network.entities.order.Cart;
import com.felece.hybris_network_sdk.data.network.entities.order.CartList;
import com.felece.hybris_network_sdk.data.network.entities.order.DeliveryMode;
import com.felece.hybris_network_sdk.data.network.entities.order.DeliveryModeList;
import com.felece.hybris_network_sdk.data.network.entities.order.OrderEntryList;
import com.felece.hybris_network_sdk.data.network.entities.user.Address;
import com.felece.hybris_network_sdk.data.network.entities.user.AddressList;
import com.felece.hybris_network_sdk.data.network.entities.user.Country;
import com.felece.hybris_network_sdk.data.network.entities.user.User;
import com.felece.hybris_network_sdk.data.network.entities.user.UserSignUp;


import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {

    @Inject
    DataManager dataManager;


    private String userName="serkan.zaydn@gmail.com";
    private String password="123456";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((HybrisApp) getApplication()).getActivityComponent().injectMainActivity(this);

        UserSignUp user=new UserSignUp();
        user.setFirstName("Serkan");
        user.setLastName("Ser");
        user.setUid("sdddd@gmail.com");
        user.setPassword("Hasan1994!*");
        user.setTitleCode("ms");
        dataManager.register(null, FIELDS.FULL.getFieldType(), user, new ServiceCallback<User>() {
            @Override
            public void onSuccess(User response) {

            }

            @Override
            public void onError(int code, String errorResponse) {

            }
        });
    }
}
