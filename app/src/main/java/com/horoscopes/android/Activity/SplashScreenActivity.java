package com.horoscopes.android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.horoscopes.android.ApiClient;
import com.horoscopes.android.BuildConfig;
import com.horoscopes.android.Model.AppOpen;
import com.horoscopes.android.Model.ZodiacDetailsData;
import com.horoscopes.android.R;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {
    private static final String TAG ="testing" ;
    private FirebaseAuth mAuth;
    private RelativeLayout banner_rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_splash_screen);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            Window window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            mAuth = FirebaseAuth.getInstance();
            ImageView splashScreen_iv = findViewById(R.id.splashScreen_iv);
          /*  Glide.with(this).load(R.drawable.splash_screen)
                    .thumbnail(Glide.with(this).load(R.drawable.splash_screen))
                    .fitCenter()
                    .into(splashScreen_iv);*/
            new Handler().postDelayed(() -> {
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                String securityToken = sharedPreferences.getString("securityToken", "");
                long userId = sharedPreferences.getLong("user", 0);
                if (userId > 0 && !securityToken.trim().isEmpty()) {
                    getAppopen();
                }
                else {
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                    finish();
                }
               /* FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser == null) {
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }*/
            }, 3000);
        }catch (Exception e){
            Log.e(TAG, "onCreate: Splash"+e );
        }
        }
    private void getAppopen() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String securityToken = sharedPreferences.getString("securityToken", "");
        long userId = sharedPreferences.getLong("user", 1);
        Call<AppOpen> call= ApiClient.getMyInterface().getAppOpen(userId,securityToken,
                BuildConfig.VERSION_NAME,BuildConfig.VERSION_CODE);
        call.enqueue(new Callback<AppOpen>() {
            @Override
            public void onResponse(Call<AppOpen> call, Response<AppOpen> response) {
                if (response.isSuccessful()) {
                    AppOpen appOpen = response.body();
                    if (appOpen != null) {
                        AppOpen.CustomAd caData = appOpen.getCustomAd();
                       // caData.getActionUrl();
                       // caData.getImageUrl();
                        if (appOpen.getMessage().matches("Success")) {
                           SharedPreferences sharedPreferences1 = getSharedPreferences("MySharedPreference", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences1.edit();
                            editor.putString("invite",appOpen.getAppUrl());
                            editor.putString("bannerImage", caData.getImageUrl());
                            editor.putString("bannerLink", caData.getActionUrl());
                            editor.apply();
                            editor.commit();
                        }
                    }
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                    finish();
                }
            }
            @Override
            public void onFailure(Call<AppOpen> call, Throwable t) {
            }
        });
    }

}