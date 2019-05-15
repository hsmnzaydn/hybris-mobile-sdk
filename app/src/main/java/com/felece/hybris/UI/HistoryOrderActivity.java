package com.felece.hybris.UI;


import android.os.Bundle;

import com.felece.hybris.HybrisApp;
import com.felece.hybris.R;
import com.felece.hybris_network_sdk.data.DataManager;

import javax.inject.Inject;

public class HistoryOrderActivity extends BaseActivity {

    @Inject
    DataManager dataManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order);

        ((HybrisApp) getApplication()).getActivityComponent().injectHistoryOrderActivity(this);



    }
}
