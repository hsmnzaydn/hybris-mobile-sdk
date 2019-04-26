package com.felece.hybris_network_sdk.data.network;

import android.content.Context;

import com.felece.hybris_network_sdk.AppConfiguration.Configuration;
import com.felece.hybris_network_sdk.data.pref.PrefHelper;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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
    private OkHttpClient okHttpClient;

    @Inject
    public ApiClient(PrefHelper prefHelper,Context context) {
        this.prefHelper = prefHelper;
        this.context=context;
    }

    public Retrofit getClient() {


        if (retrofit == null) {
            try {


            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            int cacheSize = 10 * 1024 * 1024;
            Cache cache = new Cache(context.getCacheDir(), cacheSize);


            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

             okHttpClient= new OkHttpClient.Builder()
                    .readTimeout(Configuration.readTimeOut, TimeUnit.SECONDS)
                    .connectTimeout(Configuration.connectTimeOut, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .cache(cache)
                    .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request().newBuilder()
                                    .addHeader("udid",prefHelper.getUdid())
                                    .addHeader("Authorization", "Bearer "+prefHelper.getAuthorizationKey())
                                    .build();
                            return chain.proceed(request);
                        }
                    }).hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .build();


            retrofit = new Retrofit.Builder()
                    .baseUrl(Configuration.SERVER_REQUEST_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }

        return retrofit;
    }





}
