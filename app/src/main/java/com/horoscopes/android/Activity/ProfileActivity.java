package com.horoscopes.android.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.horoscopes.android.ApiClient;
import com.horoscopes.android.BuildConfig;
import com.horoscopes.android.Model.UserProfileDetail;
import com.horoscopes.android.R;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    static int PROFILE_UPDATE_PURPOSE_LOCATION = 1001;
    DatePickerDialog datePickerDialog;
    MaterialButton dateButton, Submit;
    EditText username;
    RadioGroup radioGroup;
    Toolbar toolbar_pd;
    ImageView pics;
    static int MonthSign = 0;
    private FirebaseAuth mAuth;
    TextView tv_select_city;
    String date;
    String Username;
    String name, DOB, city, gender;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE);
        boolean isSubmit = sharedPreferences.getBoolean("isSubmitted",false);
        if (isSubmit) {
            getUserdetail("GET");
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        toolbar_pd = findViewById(R.id.ud_toolbar);
        toolbar_pd.setTitle("User Details");
        setSupportActionBar(toolbar_pd);
        toolbar_pd.setNavigationIcon(R.drawable.ic_back);
        toolbar_pd.setNavigationOnClickListener(v -> ProfileActivity.this.onBackPressed());
        username = findViewById(R.id.et_username);
        radioGroup = findViewById(R.id.userGender);

        tv_select_city = findViewById(R.id.tv_select_city);
        Submit = findViewById(R.id.submitBtn);
        mAuth = FirebaseAuth.getInstance();
        pics = findViewById(R.id.user_profile);
        Picasso.get().load(mAuth.getCurrentUser().getPhotoUrl()).into(pics);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodayDate());
        tv_select_city.setOnClickListener(view -> {
            Intent intentSearch = new Intent(ProfileActivity.this, SearchCityActivity.class);
            startActivityForResult(intentSearch, PROFILE_UPDATE_PURPOSE_LOCATION);
        });

        Submit.setOnClickListener(view -> {

            Username = username.getText().toString();
            String select_city = tv_select_city.getText().toString();
            if (Username.isEmpty()) {
                username.setError("Enter full Name*");
                username.requestFocus();
            }
          /*  else if (date.isEmpty()) {
                dateButton.setError("Choose date of birth*");
                dateButton.requestFocus();
                username.clearFocus();
            }*/
            else if (select_city.isEmpty()) {
                tv_select_city.setError("Choose city*");
               // tv_select_city.requestFocus();
                tv_select_city.clearFocus();
            } else {
                postProfile("POST");
            }
        });

    }

    private String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        month = month + 1;
        MonthSign = month;
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, day, month, year) -> {
            month = month + 1;
            date = makeDateString(day, month, year);
            dateButton.setText(date);
        };
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        long minYear = 90;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, day, month, year);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.getDatePicker().setMinDate(minYear);
        datePickerDialog.getDatePicker().getFirstDayOfWeek();
    }

    private String makeDateString(int day, int month, int year) {
        return " " + year + "-" + getMonthFormat(month) + "-" + day;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";
        return "JAN";
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
        datePickerDialog.getButton(R.color.white);
    }
   /* public void checkedButton(View view) {
        int radioId=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
        radioButton.getText();
       // Toast.makeText(this, "Your Choice" +radioButton.getText(), Toast.LENGTH_SHORT).show();
    }*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PROFILE_UPDATE_PURPOSE_LOCATION && resultCode == RESULT_OK && null != data) {
            String uricity = data.getStringExtra("city");
            String uristate = data.getStringExtra("state");
            tv_select_city.setText(uricity + ", " + uristate);
            tv_select_city.setError("Choose city*");
        }
    }
    private void getUserdetail(String actionType) {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String securityToken = sharedPreferences.getString("securityToken", "");
        long userId = sharedPreferences.getLong("user", 1);
        Call<UserProfileDetail> call = ApiClient.getMyInterface().getProfile(userId, securityToken,
                BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE, actionType);
        call.enqueue(new Callback<UserProfileDetail>() {
            @Override
            public void onResponse(Call<UserProfileDetail> call, Response<UserProfileDetail> response) {
                if (response.isSuccessful()) {
                    UserProfileDetail data = response.body();
                    if (data != null) {
                        if (data.getMessage().matches("Success")) {
                            username.setText(data.getUserName());
                            dateButton.setText(data.getBirthDate());
                            gender =((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                public void onCheckedChanged(RadioGroup group, int checkedId) {
                                    data.setGender(String.valueOf(checkedId));
                                }
                            });
                           tv_select_city.setText(data.getLocation());
                        } else {
                            Toast.makeText(ProfileActivity.this, "Unable to process", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<UserProfileDetail> call, Throwable t) {
            }
        });
    }
    private void postProfile(String actionType) {
        name=username.getText().toString();
        gender=((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
        DOB=dateButton.getText().toString();
        city=tv_select_city.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String securityToken = sharedPreferences.getString("securityToken", "");
        long userId = sharedPreferences.getLong("user", 1);
        Call<UserProfileDetail> call = ApiClient.getMyInterface().postProfile(userId, securityToken,
                BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE
                ,name, DOB, gender, city,
                actionType);
        call.enqueue(new Callback<UserProfileDetail>() {
            @Override
            public void onResponse(Call<UserProfileDetail> call, Response<UserProfileDetail> response) {
                if (response.isSuccessful()) {
                    UserProfileDetail data = response.body();
                    if (data != null) {
                        data.setUserName(name);
                        data.setGender(gender);
                        data.setBirthDate(DOB);
                        data.setLocation(city);
                        if (data.getMessage().matches("Success")) {
                            //Toast.makeText(ProfileActivity.this, "" + data.getMessage(), Toast.LENGTH_SHORT).show();
                            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE);
                            SharedPreferences.Editor edit = sharedPreferences.edit();
                            edit.putBoolean("isSubmitted", true);
                            edit.apply();
                            edit.commit();
                            startActivity(new Intent(ProfileActivity.this,BirthDetailsActivity.class));
                            finish();
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<UserProfileDetail> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Unable to process", Toast.LENGTH_SHORT).show();
            }
        });
    }
}