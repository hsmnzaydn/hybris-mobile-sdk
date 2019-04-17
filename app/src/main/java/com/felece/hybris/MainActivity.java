package com.felece.hybris;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.felece.hybris_network_sdk.ServiceCallback;
import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.network.entities.catalog.Catalog;
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



        dataManager.getCatalog(null, "Online", FIELDS.BASIC.getFieldType(), new ServiceCallback<Catalog>() {
            @Override
            public void onSuccess(Catalog response) {
                Log.d("veri","veri");

            }

            @Override
            public void onError(int code, String errorResponse) {
                Log.d("veri","veri");

            }
        });
    }
}
