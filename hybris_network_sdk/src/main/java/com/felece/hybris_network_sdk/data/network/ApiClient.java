package com.felece.hybris_network_sdk.data.network;

import android.content.Context;

import com.felece.hybris_network_sdk.AppConfiguration.Configuration;
import com.felece.hybris_network_sdk.data.pref.PrefHelper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public Retrofit retrofit = null;
    private PrefHelper prefHelper;
    private Context context;

    @Inject
    public ApiClient(PrefHelper prefHelper,Context context) {
        this.prefHelper = prefHelper;
        this.context=context;
    }

    public Retrofit getClient() {


        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            int cacheSize = 10 * 1024 * 1024;
            Cache cache = new Cache(context.getCacheDir(), cacheSize);


            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(Configuration.readTimeOut, TimeUnit.SECONDS)
                    .connectTimeout(Configuration.connectTimeOut, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .cache(cache)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request().newBuilder()
                                    .addHeader("udid","B6700C37-6872-46F6-AEFC-B5FA5EFC26A1")
                                    .addHeader("AuthorizationKey", "5c8c2b89f154ed7ee4feaa38")
                                    .build();
                            return chain.proceed(request);
                        }
                    })
                    .build();


            retrofit = new Retrofit.Builder()
                    .baseUrl(Configuration.SERVER_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
