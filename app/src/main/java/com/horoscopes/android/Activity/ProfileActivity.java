package com.horoscopes.android.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.button.MaterialButton;
import com.horoscopes.android.R;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {
    DatePickerDialog datePickerDialog;
    MaterialButton dateButton,Submit;
    EditText username,birthPlace;
    RadioGroup userGender;
    RadioButton female, male;
    Toolbar toolbar_pd;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        toolbar_pd=findViewById(R.id.ud_toolbar);
        toolbar_pd.setTitle("User Details");
        setSupportActionBar(toolbar_pd);
        toolbar_pd.setNavigationIcon(R.drawable.ic_back);
        toolbar_pd.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileActivity.this.onBackPressed();
            }
        });
        username=findViewById(R.id.user_name);
        userGender=findViewById(R.id.user_gender);
        birthPlace=findViewById(R.id.user_place);
        Submit=findViewById(R.id.submitBtn);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodayDate());
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final Intent intent=new Intent(ProfileActivity.this, BirthDetailsActivity.class);
                final Bundle b = new Bundle();

                userGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        if(checkedId == R.id.male){
                            String Male = getResources().getString(R.string.male);
                            b.putString("gender", Male);
                        } else {
                            String Female = getResources().getString(R.string.female);
                            b.putString("gender", Female);
                        }
                    }
                });
                String user_name = username.getText().toString();
                String date_picker= dateButton.getText().toString();
                String birth_place= birthPlace.getText().toString();
                int checked = userGender.getCheckedRadioButtonId();
                intent.putExtra("name", user_name);
                intent.putExtra("dob", date_picker);
                intent.putExtra("place", birth_place);
                b.putString("checked", String.valueOf(checked));
                intent.putExtras(b);
                startActivity(intent);
            }
        });

    }

   private String getTodayDate(){
        Calendar cal=Calendar.getInstance();
       int day=cal.get(Calendar.DAY_OF_MONTH);
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        month=month+1;
        return makeDateString(day,month,year);
    }
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, day, month, year) -> {
            month = month + 1;
            String date = makeDateString(day, month, year);
            dateButton.setText(date);
        };
        Calendar cal=Calendar.getInstance();
        int day=cal.get(Calendar.DAY_OF_MONTH);
        int month=cal.get(Calendar.MONTH);
        int year=cal.get(Calendar.YEAR);
        int style = AlertDialog.THEME_HOLO_LIGHT;
        long minYear=60;
        datePickerDialog=new DatePickerDialog(this, style, dateSetListener, day, month, year);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.getDatePicker().setMinDate(minYear);
        //datePickerDialog.getDatePicker().getFirstDayOfWeek();

    }
    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
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
    public void openDatePicker(View view){
        datePickerDialog.show();
        datePickerDialog.getButton(R.color.white);
    }
    /*public String getBirthMonthText(LocalDate dateOfBirth) {
        return dateOfBirth.monthOfYear().getAsText(Locale.ENGLISH);
    }*/
}