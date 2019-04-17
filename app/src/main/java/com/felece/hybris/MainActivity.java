package com.felece.hybris;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.catalog.CatalogList;
import com.felece.hybris_network_sdk.data.network.entities.enums.FIELDS;
import com.felece.hybris_network_sdk.data.network.entities.enums.TYPE;
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


        dataManager.getContries(null, null, null, new ServiceCallback<CountryList>() {
            @Override
            public void onSuccess(CountryList response) {

            }

            @Override
            public void onError(int code, String errorResponse) {

            }
        });

        dataManager.getCatalogs(null, FIELDS.BASIC.getFieldType(), new ServiceCallback<CatalogList>() {
            @Override
            public void onSuccess(CatalogList response) {
                Log.d("veri","veri");
            }

            @Override
            public void onError(int code, String errorResponse) {

            }
        });
    }
}
