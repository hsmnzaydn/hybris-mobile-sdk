package com.felece.hybris;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
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

        dataManager.getInformationCategoryOfCatalogVersion(null, FIELDS.DEFAULT.getFieldType(), "electronicsProductCatalog", "Online", "brand_1", new ServiceCallback<CatalogVersion>() {
            @Override
            public void onSuccess(CatalogVersion response) {
                Log.d("veri","veri");
            }

            @Override
            public void onError(int code, String errorResponse) {

            }
        });
    }
}
