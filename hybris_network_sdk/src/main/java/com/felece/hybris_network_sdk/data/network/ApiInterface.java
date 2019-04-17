package com.felece.hybris_network_sdk.data.network;




import com.felece.hybris_network_sdk.data.network.entities.CommonResponse;


import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("secure/start-application")
    Single<Object> startApplication(@Query("pnsToken") String pnsToken);

    @GET("mobilewebservices/v2/electronics/countries?fields=DEFAULT")
    Single<Object> getCountries();

}
