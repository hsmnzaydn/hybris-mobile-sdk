package com.felece.hybris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.felece.hybris_network_sdk.data.DataManager;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {


    @Inject
    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((HybrisApp) getApplication()).getActivityComponent().injectMainActivity(this);


        dataManager.startApplication();

    }
}
