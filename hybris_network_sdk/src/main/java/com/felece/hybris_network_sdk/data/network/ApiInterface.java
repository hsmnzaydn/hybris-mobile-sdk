package com.felece.hybris_network_sdk.data.network;




import com.felece.hybris_network_sdk.data.network.entities.CommonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("secure/start-application")
    Call<CommonResponse> startApplication(@Query("pnsToken") String pnsToken);


}
