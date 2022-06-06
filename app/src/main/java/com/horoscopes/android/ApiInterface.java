package com.horoscopes.android;

import com.horoscopes.android.Model.AppOpen;
import com.horoscopes.android.Model.SignUp;
import com.horoscopes.android.Model.ZodiacDetailsData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("appOpen")
    Call<AppOpen> getAppOpen(@Field("userId") String userId,
                             @Field("securityToken") String securityToken,
                             @Field("versionName") String versionName,
                             @Field("versionCode") String versionCode);

    @FormUrlEncoded
    @POST("userSignup")
    Call<SignUp> getSignUp(@Field("deviceId") String deviceId,
                           @Field("socialType") String socialType,
                           @Field("socialId") String socialId,
                           @Field("userId") String userId,
                           @Field("securityToken") String securityToken,
                           @Field("versionName") String versionName,
                           @Field("versionCode") int versionCode);

    @FormUrlEncoded
    @POST("getInfo")
    Call<ZodiacDetailsData> getSign(@Field("userId") String userId,
                                    @Field("securityToken") String securityToken,
                                    @Field("versionName") String versionName,
                                    @Field("versionCode") String versionCode,
                                    @Field("sign") String sign,
                                    @Field("day") String day);

}
