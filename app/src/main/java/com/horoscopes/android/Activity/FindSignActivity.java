package com.horoscopes.android.Activity;
import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import com.horoscopes.android.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FindSignActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    MaterialButton dateBtn;
    Button forwardBtn;
    TextView fetch_zodiac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_sign);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        initDatePicker();
        fetch_zodiac=findViewById(R.id.fetch_zodiac);
        dateBtn = findViewById(R.id.datePicker);
        try {
            Date date= new SimpleDateFormat("dd MM yyyy").parse(getTodayDate());
            date.getYear();
            Log.e(TAG, "onCreate: date" +date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateBtn.setText(getTodayDate());
        forwardBtn =findViewById(R.id.continue_btn);

        forwardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindSignActivity.this, MainActivity.class));
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
            dateBtn.setText(date);
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
        return " " + day+"- "+getMonthFormat(month) + "- " + year;
    }
    private String getMonthFormat(int month) {
        switch (month) {
            case 1: {
                fetch_zodiac.setText("You are Capricorn");
                return "Jan";
            }
            case 2: {
                fetch_zodiac.setText("You are Aquarius");
                return "Feb";
            }
            case 3: {
                fetch_zodiac.setText("You are Pisces");
                return "Mar";
            }
            case 4: {
                fetch_zodiac.setText("You are Aries");
                return "Apr";
            }
            case 5: {
                fetch_zodiac.setText("You are Taurus");
                return "May";
            }
            case 6: {
                fetch_zodiac.setText("You are Gemini");
                return "Jun";
            }
            case 7:
                fetch_zodiac.setText("You are Cancer");
                return "Jul";
            case 8:
                fetch_zodiac.setText("You are Leo");
                return "Aug";
            case 9:
                fetch_zodiac.setText("You are Virgo");
                return "Sep";
            case 10:
                fetch_zodiac.setText("You are Libra");
                return "OCt";
            case 11:
                fetch_zodiac.setText("You are Scorpio");
                return "Nov";
            case 12:
                fetch_zodiac.setText("You are Sagittarius");
                return "Dec";
            default:

        }
        return "JAN";

    }
    public void openDatePicker(View view){
        datePickerDialog.show();
        datePickerDialog.getButton(R.color.white);
    }
}