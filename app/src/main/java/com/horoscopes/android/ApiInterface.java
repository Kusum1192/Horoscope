package com.horoscopes.android;

import com.horoscopes.android.Model.AppOpen;
import com.horoscopes.android.Model.SignUp;
import com.horoscopes.android.Model.UserProfileDetail;
import com.horoscopes.android.Model.ZodiacDetailsData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("asappOpen")
    Call<AppOpen> getAppOpen(@Field("userId") long userId,
                             @Field("securityToken") String securityToken,
                             @Field("versionName") String versionName,
                             @Field("versionCode") int versionCode);

    @FormUrlEncoded
    @POST("asuserSignup")
    Call<SignUp> getSignUp(@Field("deviceId") String deviceId,
                           @Field("deviceName") String deviceName,
                           @Field("deviceType") String deviceType,
                           @Field("socialType") String socialType,
                           @Field("socialId") String socialId,
                           @Field("socialName") String socialName,
                           @Field("socialImgurl") String socialImgurl,
                           @Field("versionName") String versionName,
                           @Field("versionCode") int versionCode,
                           @Field("socialEmail") String socialEmail,
                           @Field("utmSource") String utmSource,
                           @Field("utmMedium") String utmMedium,
                           @Field("advertisingId") String advertisingId,
                           @Field("utmCampaign") String utmCampaign,
                           @Field("utmContent") String utmContent,
                           @Field("utmTerm") String utmTerm,
                           @Field("referralUrl") String referralUrl);

    @FormUrlEncoded
    @POST("getInfo")
    Call<ZodiacDetailsData> getSign(@Field("userId") long userId,
                                    @Field("securityToken") String securityToken,
                                    @Field("versionName") String versionName,
                                    @Field("versionCode") int versionCode,
                                    @Field("sign") String sign);

    @FormUrlEncoded
    @POST("asuserProfile")
    Call<UserProfileDetail> getProfile(@Field("userId") long userId,
                                       @Field("securityToken") String securityToken,
                                       @Field("versionName") String versionName,
                                       @Field("versionCode") int versionCode,
                                       @Field("actionType") String actionType
    );
    @FormUrlEncoded
    @POST("asuserProfile")
    Call<UserProfileDetail> postProfile(@Field("userId") long userId,
                                       @Field("securityToken") String securityToken,
                                       @Field("versionName") String versionName,
                                       @Field("versionCode") int versionCode,
                                        @Field("name") String name,
                                        @Field("date_of_birth") String date_of_birth,
                                        @Field("gender") String gender,
                                        @Field("location") String place,
                                        @Field("actionType") String actionType
    );

}
