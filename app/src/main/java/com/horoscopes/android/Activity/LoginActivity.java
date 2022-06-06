package com.horoscopes.android.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.horoscopes.android.ApiClient;
import com.horoscopes.android.BuildConfig;
import com.horoscopes.android.Model.SignUp;
import com.horoscopes.android.Model.ZodiacDetailsData;
import com.horoscopes.android.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView SignInBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        SignInBtn=findViewById(R.id.signIn_btn);
        SignInBtn.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, MainActivity.class)));
        getLogin();
    }
    public void getLogin() {
        Call<SignUp> call = ApiClient.getMyInterface().getSignUp("1","google","1",
                "1","69a89336-0757-40cd-9daf-0e3073998723",
                BuildConfig.VERSION_NAME,BuildConfig.VERSION_CODE);
        call.enqueue(new Callback<SignUp>() {
            @Override
            public void onResponse(@NonNull Call<SignUp> call, @NonNull Response<SignUp> response) {
                SignUp signUp=response.body();
                if (signUp != null) {

                }
                }
            @Override
            public void onFailure(@NonNull Call<SignUp> call, @NonNull Throwable t) {
            }
        });
    }
}