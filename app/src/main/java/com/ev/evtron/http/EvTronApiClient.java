package com.ev.evtron.http;

import com.ev.evtron.utils.ApiClass;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class EvTronApiClient {

    private static Retrofit retrofit = null;
    private static ApiClass apiClass;

    public EvTronApiClient() {

    }

    public EvTronApiInterface evtronApiInterface() {
        apiClass = new ApiClass();
        if (retrofit == null) {
            OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(apiClass.BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .client(defaultHttpClient)
                    .build();
        }
        return retrofit.create(EvTronApiInterface.class);
    }
}
