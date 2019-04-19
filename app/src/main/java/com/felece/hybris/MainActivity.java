package com.felece.hybris;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.felece.hybris_network_sdk.AppConfiguration.Configuration;
import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogList;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogVersion;
import com.felece.hybris_network_sdk.data.network.entities.enums.FIELDS;

import javax.inject.Inject;



public class MainActivity extends AppCompatActivity {

    @Inject
    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((HybrisApp) getApplication()).getActivityComponent().injectMainActivity(this);

        dataManager.configurationApplication("","",10,10);

        dataManager.getCatalogs(null, FIELDS.DEFAULT.getFieldType(), new ServiceCallback<CatalogList>() {
            @Override
            public void onSuccess(CatalogList response) {

            }

            @Override
            public void onError(int code, String errorResponse) {

            }
        });
    }
}
