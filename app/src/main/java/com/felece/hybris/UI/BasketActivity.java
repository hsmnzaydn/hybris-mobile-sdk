package com.felece.hybris.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.felece.hybris.HybrisApp;
import com.felece.hybris.R;
import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.user.AddressList;

import javax.inject.Inject;

public class BasketActivity extends BaseActivity {

    @Inject
    DataManager dataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        ((HybrisApp) getApplication()).getActivityComponent().injectBasketActivity(this);

        dataManager.getUserAdresses(null, new ServiceCallback<AddressList>() {
            @Override
            public void onSuccess(AddressList response) {

            }

            @Override
            public void onError(int code, String errorResponse) {

            }
        });

    }
}
