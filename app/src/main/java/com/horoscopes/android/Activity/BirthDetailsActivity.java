package com.horoscopes.android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.horoscopes.android.ApiClient;
import com.horoscopes.android.BuildConfig;
import com.horoscopes.android.Model.UserProfileDetail;
import com.horoscopes.android.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BirthDetailsActivity extends AppCompatActivity {
    TextView user_name, birthDetail, birth_place, Gender, manglikName;
    TextView datePicker;
    TextView zodiacSign;
    ImageView backbtn_iv;
    ProgressBar bar;
    String birthPlace;
    private int month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birth_details);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        backbtn_iv = findViewById(R.id.backbtn_iv);
        backbtn_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BirthDetailsActivity.this, MainActivity.class));
                finish();
            }
        });
        bar = findViewById(R.id.progressBar);
        user_name = findViewById(R.id.name);
//        manglikName=findViewById(R.id.manglikName);
        Gender = findViewById(R.id.fetch_gender);
        zodiacSign = findViewById(R.id.zodicSigns);
        birth_place = findViewById(R.id.place);
        datePicker = findViewById(R.id.DOB);
        birthPlace = getIntent().getStringExtra("place");

       /* String genderName= getIntent().getStringExtra("gender_name");
        gender.setText(genderName);
        String signs=getIntent().getStringExtra("sign");
        zodiacSign.setText(signs);
       // Toast.makeText(BirthDetailsActivity.this, "Sign"+zodiacSign, Toast.LENGTH_SHORT).show();
        String name = getIntent().getStringExtra("name");
        user_name.setText(name);
        manglikName.setText(name);
        String dob=getIntent().getStringExtra("dob");
        datePicker.setText(dob);*/

        birthDetail = findViewById(R.id.editBirthDetails);
        birthDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BirthDetailsActivity.this, ProfileActivity.class));
            }
        });
        getProfile("GET");
    }

    private void getProfile(String actionType) {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String securityToken = sharedPreferences.getString("securityToken", "");
        long userId = sharedPreferences.getLong("user", 1);
        Call<UserProfileDetail> call = ApiClient.getMyInterface().getProfile(userId, securityToken,
                BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE, actionType);
        call.enqueue(new Callback<UserProfileDetail>() {
            @Override
            public void onResponse(Call<UserProfileDetail> call, Response<UserProfileDetail> response) {
                bar.setVisibility(View.INVISIBLE);
                if (response.isSuccessful()) {
                    UserProfileDetail data = response.body();
                    if (data != null) {
                        user_name.setText(data.getUserName());
                        datePicker.setText(data.getBirthDate());
//                            manglikName.setText(data.getUserName());
                        Gender.setText(data.getGender());
                        birth_place.setText(data.getLocation());
                      int numMnth=getMonthNumber(data.getBirthDate());
                        switch (numMnth) {
                            case 1: {
                                zodiacSign.setText("Capricorn");
                                break;
                            }
                            case 2: {
                                zodiacSign.setText("Aquarius");
                                break;
                            }
                            case 3: {
                                zodiacSign.setText("Pisces");
                                break;
                            }
                            case 4: {
                                zodiacSign.setText("Aries");
                                break;
                            }
                            case 5: {
                                zodiacSign.setText("Taurus");
                                break;
                            }
                            case 6: {
                                zodiacSign.setText("Gemini");
                                break;
                            }
                            case 7: {
                                zodiacSign.setText("Cancer");
                                break;
                            }
                            case 8:{
                                zodiacSign.setText("Leo");
                                break;
                            }
                            case 9:
                                zodiacSign.setText("Virgo");
                                break;
                            case 10:
                                zodiacSign.setText("Libra");
                                break;
                            case 11:
                                zodiacSign.setText("Scorpio");
                                break;
                            case 12:
                                zodiacSign.setText("Sagittarius");
                                break;
                            default:

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<UserProfileDetail> call, Throwable t) {
                Toast.makeText(BirthDetailsActivity.this, "Unable to process", Toast.LENGTH_SHORT).show();
            }
        });
    }

   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(BirthDetailsActivity.this,MainActivity.class));
        finish();
    }*/

    private int getMonthNumber(String date) {
        String[] d= date.split("-");
        String day=d[0];
        String month=d[1];
        String year=d[2];
        switch (month) {
            case "JAN":
                return 1;
            case "FEB":
                return 2;
            case "MAR":
                return 3;
            case "APR":
                return 4;
            case "MAY":
                return 5;
            case "JUN":
                return 6;
            case "JUL":
                return 7;
            case "AUG":
                return 8;
            case "SEP":
                return 9;
            case "OCT":
                return 10;
            case "NOV":
                return 11;
            case "DEC":
                return 12;
            default:
                return 0;
        }
    }
}