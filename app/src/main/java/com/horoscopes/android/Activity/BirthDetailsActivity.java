package com.horoscopes.android.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.horoscopes.android.R;

public class BirthDetailsActivity extends AppCompatActivity {
    TextView user_name,birthDetail,birth_place, gender;
    TextView datePicker;
    String male_user;
    String female_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birth_details);
        user_name=findViewById(R.id.name);
        gender=findViewById(R.id.fetch_gender);
        male_user=getIntent().getStringExtra("male");
        gender.setText(male_user);
        female_user=getIntent().getStringExtra("female");
        gender.setText(female_user);
        birth_place=findViewById(R.id.place);
        datePicker=findViewById(R.id.DOB);
        String name = getIntent().getStringExtra("name");
        user_name.setText(name);
        String dob=getIntent().getStringExtra("dob");
        datePicker.setText(dob);
        String birthPlace=getIntent().getStringExtra("place");
        birth_place.setText(birthPlace);
        birthDetail=findViewById(R.id.editBirthDetails);
        birthDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BirthDetailsActivity.this,ProfileActivity.class));
                onBackPressed();
            }
        });
        Bundle b = getIntent().getExtras();
        int checked = b.getInt("checked");
        if (checked==1){ // male
            gender.setText("male");
        } else {// female
            gender.setText("female");
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}