package com.felece.hybris;







import com.felece.hybris_network_sdk.di.modules.DataModules;
import com.felece.hybris_network_sdk.di.modules.PresenterModules;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {PresenterModules.class,DataModules.class})
public interface ViewComponents {
    void injectMainActivity(MainActivity mainActivity);


}
