package com.felece.hybris_network_sdk.di.modules;

import android.content.Context;

import com.felece.hybris_network_sdk.data.DataManager;
import com.felece.hybris_network_sdk.data.DataManagerImp;
import com.felece.hybris_network_sdk.data.network.ApiClient;
import com.felece.hybris_network_sdk.data.network.ApiServices;
import com.felece.hybris_network_sdk.data.network.ApiServicesImp;
import com.felece.hybris_network_sdk.data.network.services.CartServices.CartServices;
import com.felece.hybris_network_sdk.data.network.services.CartServices.CartServicesImp;
import com.felece.hybris_network_sdk.data.network.services.CatalogServices.CatalogServices;
import com.felece.hybris_network_sdk.data.network.services.CatalogServices.CatalogServicesImp;
import com.felece.hybris_network_sdk.data.network.services.CountriesServices.CountriesServices;
import com.felece.hybris_network_sdk.data.network.services.CountriesServices.CountriesServicesImp;
import com.felece.hybris_network_sdk.data.network.services.BaseService;
import com.felece.hybris_network_sdk.data.network.services.ProductServices.ProductServices;
import com.felece.hybris_network_sdk.data.network.services.ProductServices.ProductServicesImp;
import com.felece.hybris_network_sdk.data.network.services.UserServices.UserServices;
import com.felece.hybris_network_sdk.data.network.services.UserServices.UserServicesImp;
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

    @Singleton
    @Provides
    ApiServices provideApiServices(CountriesServices countriesServices,
                                   CatalogServices catalogServices,
                                   UserServices userServices,
                                   CartServices cartServices,
                                   ProductServices productServices) {
        return new ApiServicesImp(countriesServices, catalogServices, userServices, cartServices, productServices);
    }

    @Singleton
    @Provides
    ProductServices provideProductServices(ApiClient apiClient, Gson gson) {
        return new ProductServicesImp(apiClient, gson);
    }

    @Singleton
    @Provides
    CartServices provideCartServices(ApiClient apiClient, Gson gson) {
        return new CartServicesImp(apiClient, gson);
    }

    @Singleton
    @Provides
    CatalogServices provideCatalogServices(ApiClient apiClient, Gson gson) {
        return new CatalogServicesImp(apiClient, gson);
    }

    @Provides
    @Singleton
    CountriesServices provideApplicationServices(ApiClient apiClient, Gson gson) {
        return new CountriesServicesImp(apiClient, gson);
    }

    @Provides
    @Singleton
    BaseService provideBaseServices(ApiClient apiClient, Gson gson) {
        return new BaseService(apiClient, gson);
    }

    @Provides
    @Singleton
    PrefHelper providePrefHelper(Context context) {
        return new PrefHelperImp(context);
    }

    @Provides
    @Singleton
    UserServices provideUserServices(ApiClient apiClient, Gson gson) {
        return new UserServicesImp(apiClient, gson);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    ApiClient provideApiClient(PrefHelper prefHelper, Context context) {
        return new ApiClient(prefHelper, context);
    }

}
