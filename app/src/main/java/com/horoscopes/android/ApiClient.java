package com.horoscopes.android;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    static String url="https://mobnews.app/api/v1/";

private static Retrofit getRetrofit(){
    HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient okHttpClient=new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    return new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build();
}
    public static com.horoscopes.android.ApiInterface getMyInterface(){
    return getRetrofit().create(com.horoscopes.android.ApiInterface.class);
    }
}
