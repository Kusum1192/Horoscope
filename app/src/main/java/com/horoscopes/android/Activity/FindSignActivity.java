package com.horoscopes.android.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import com.horoscopes.android.Fragment.HoroscopeFragment;
import com.horoscopes.android.R;
import java.util.Calendar;
public class FindSignActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    MaterialButton dateBtn;
    Button forwardBtn;
    TextView fetch_zodiac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_sign);
        initDatePicker();
        fetch_zodiac=findViewById(R.id.fetch_zodiac);
        dateBtn = findViewById(R.id.datePicker);
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
        return getMonthFormat(month) + " " + day + " " + year;
    }
    private String getMonthFormat(int month) {
        fetch_zodiac.setText("You are Capricorn");
        if (month == 1)
            return "JAN";
        fetch_zodiac.setText("You are Aquarius");
        if (month == 2)
            return "FEB";
        fetch_zodiac.setText("You are Pisces");
        if (month == 3)
            return "MAR";
        fetch_zodiac.setText("You are Aries");
        if (month == 4)
            return "APR";
        fetch_zodiac.setText("You are Taurus");
        if (month == 5)
            return "MAY";
        fetch_zodiac.setText("You are Gemini");
        if (month == 6)
            return "JUN";
        fetch_zodiac.setText("You are Cancer");
        if (month == 7)
            return "JUL";
        fetch_zodiac.setText("You are Leo");
        if (month == 8)
            return "AUG";
        fetch_zodiac.setText("You are Virgo");
        if (month == 9)
            return "SEP";
        fetch_zodiac.setText("You are Libra");
        if (month == 10)
            return "OCT";
        fetch_zodiac.setText("You are Scorpio");
        if (month == 11)
            return "NOV";
        fetch_zodiac.setText("You are Sagittarius");
        if (month == 12)
            return "DEC";
        return "JAN";
    }
    public void openDatePicker(View view){
        datePickerDialog.show();
        datePickerDialog.getButton(R.color.white);
    }
}