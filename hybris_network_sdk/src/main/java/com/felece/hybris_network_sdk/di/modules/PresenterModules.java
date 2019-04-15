package com.felece.hybris_network_sdk.di.modules;

import android.app.Application;
import android.content.Context;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModules {
    private Context context;

    public PresenterModules(Application app){
        this.context=app;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return context;
    }

}
