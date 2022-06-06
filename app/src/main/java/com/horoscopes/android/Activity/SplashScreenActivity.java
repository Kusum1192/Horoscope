package com.horoscopes.android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.horoscopes.android.ApiClient;
import com.horoscopes.android.Model.AppOpen;
import com.horoscopes.android.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        ImageView splashScreen_iv = findViewById(R.id.splashScreen_iv);
        Glide.with(this).load(R.drawable.splash_screen)
                .thumbnail(Glide.with(this).load(R.drawable.splash_screen))
                .fitCenter()
                .into(splashScreen_iv);
        new Handler().postDelayed(() -> {
            getAppopen();
            },2000);

    }

    private void getAppopen() {
        Call<AppOpen> call= ApiClient.getMyInterface().getAppOpen("1","69a89336-0757-40cd-9daf-0e3073998723",
                "1","1");
        call.enqueue(new Callback<AppOpen>() {
            @Override
            public void onResponse(Call<AppOpen> call, Response<AppOpen> response) {
                AppOpen appOpen=response.body();
                if (appOpen != null) {
                    startActivity(new Intent(SplashScreenActivity.this,LoginActivity.class));
                    finish();
                }
            }
            @Override
            public void onFailure(Call<AppOpen> call, Throwable t) {

            }
        });
    }

}