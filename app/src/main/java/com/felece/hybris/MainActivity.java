package com.felece.hybris;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.user.CountryList;
import javax.inject.Inject;



public class MainActivity extends AppCompatActivity {

    @Inject
    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((HybrisApp) getApplication()).getActivityComponent().injectMainActivity(this);

        dataManager.configurationApplication("https://192.168.4.27:9002/",10,10);


        dataManager.getContries(null, new ServiceCallback<CountryList>() {
            @Override
            public void onSuccess(CountryList response) {

            }

            @Override
            public void onError(int code, String errorResponse) {

            }
        });
    }
}
