package com.felece.hybris;

import android.app.Application;

import com.felece.hybris_network_sdk.di.modules.HybrisPresenterModules;

public class HybrisApp extends Application {

    private ViewComponents viewComponents;

    public ViewComponents getActivityComponent() {
        return viewComponents;
    }

    public void setActivityComponent(ViewComponents activityComponent) {
        this.viewComponents = activityComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        viewComponents= DaggerViewComponents.builder().hybrisPresenterModules(new HybrisPresenterModules(this)).build();


    }
}
