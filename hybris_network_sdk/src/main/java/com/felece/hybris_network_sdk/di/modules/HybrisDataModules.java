package com.felece.hybris_network_sdk.di.modules;




import android.content.Context;

import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.DataManagerImp;
import com.felece.hybris_network_sdk.data.network.ApiClient;
import com.felece.hybris_network_sdk.data.network.ApiServices;
import com.felece.hybris_network_sdk.data.network.ApiServicesImp;
import com.felece.hybris_network_sdk.data.network.services.ApplicationServices.ApplicationServices;
import com.felece.hybris_network_sdk.data.network.services.ApplicationServices.ApplicationServicesImp;
import com.felece.hybris_network_sdk.data.pref.PrefHelper;
import com.felece.hybris_network_sdk.data.pref.PrefHelperImp;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class HybrisDataModules {

    @Provides
    @Singleton
    DataManager provideDataManager(ApiServices apiServices, PrefHelper prefHelper) {
        return new DataManagerImp(apiServices, prefHelper);
    }

    @Provides
    @Singleton
    ApiServices provideApiServices(ApplicationServices applicationServices){
        return new ApiServicesImp(applicationServices);
    }


    @Provides
    @Singleton
    ApplicationServices provideApplicationServices(ApiClient apiClient,Gson gson){
        return new ApplicationServicesImp(apiClient,gson);
    }

    @Provides
    @Singleton
    PrefHelper providePrefHelper(Context context){
        return new PrefHelperImp(context);
    }

    @Provides
    @Singleton
    Gson provideGson(){
        return new Gson();
    }

}
