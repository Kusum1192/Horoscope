package com.horoscopes.android.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.horoscopes.android.Adapter.VPDaysAdapter;
import com.horoscopes.android.ApiClient;
import com.horoscopes.android.Model.ZodiacDetailsData;
import com.horoscopes.android.R;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HoroscopeDetailActivity extends AppCompatActivity {

    Toolbar toolbarWidget;
    ViewPager viewPager;
    VPDaysAdapter VPDaysAdapter;
    TabLayout tb_lyt_days;
    TabItem tabYesterday, tabToday, tabTomorrow;
    ImageView zodiacSign;
    String sign,alias,startDate,endDate,content;
    TextView zodiacAlias;
    TextView zodiacTitle;
    TextView zodiacDOB,current_date,lkyNumber,lkyTime,lkyColor,mood,content_tv;
    String name;
    @SuppressLint({"SetTextI18n", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope_detail);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        zodiacSign=findViewById(R.id.zodiacSignImg);
        zodiacTitle=findViewById(R.id.zodiacSignTitle);
        lkyNumber=findViewById(R.id.zodiacNumber);
        lkyTime=findViewById(R.id.zodiacTime);
        lkyColor=findViewById(R.id.colorName);
        mood=findViewById(R.id.Mood);
        zodiacDOB=findViewById(R.id.zodiacSignDOB);
        content_tv=findViewById(R.id.contentId);
        toolbarWidget = findViewById(R.id.toolbar);
        tb_lyt_days = findViewById(R.id.tabLayout_days);
        tabYesterday = findViewById(R.id.yesterday_TI);
        tabToday = findViewById(R.id.today_TI);
        tabTomorrow = findViewById(R.id.tomorrow_TI);
        viewPager = findViewById(R.id.vp);
        toolbarWidget.setTitle("Horoscope Reading Details");
        setSupportActionBar(toolbarWidget);
        toolbarWidget.setNavigationIcon(R.drawable.ic_back);
        toolbarWidget.setNavigationOnClickListener(v -> onBackPressed());
       //String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        current_date = findViewById(R.id.date);
       //date.setText(currentDateTimeString);
        VPDaysAdapter = new VPDaysAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tb_lyt_days.getTabCount());
        viewPager.setAdapter(VPDaysAdapter);
        tb_lyt_days.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tb_lyt_days));
        int id=getIntent().getIntExtra("id",-1);
        try {
            JSONObject jsonObject = new JSONObject(loadJSONFromAssets(this));
            JSONArray zodiacList = jsonObject.getJSONArray("zodiacList");
                String zodiacId = zodiacList.getJSONObject(id).getString("zodiacId");
                name=zodiacList.getJSONObject(id).getString("name");
                sign = zodiacList.getJSONObject(id).getString("sign");
                //alias = zodiacList.getJSONObject(id).getString("alias");
                startDate = zodiacList.getJSONObject(id).getString("startDate");
                endDate = zodiacList.getJSONObject(id).getString("endDate");
                content = zodiacList.getJSONObject(id).getString("content");
        } catch (JSONException e) {
            e.printStackTrace();
        }
         int zodiacImg=getIntent().getIntExtra("image",0);
        Picasso.get().load(zodiacImg).into(zodiacSign);
        zodiacTitle.setText(sign);
//        zodiacAlias.setText(alias);
        zodiacDOB.setText(startDate+" to "+endDate);
        // set the page viewer in tab layout
        getHoroscopeDetails();
    }
    public static String loadJSONFromAssets(Context context) {
        String jsonFile=null;
    try {
        InputStream is= context.getAssets().open("zodiac_2022.json");
        int size=is.available();
        byte[] buffer=new byte[size];
        is.read(buffer);
        is.close();
        jsonFile=new String(buffer,"UTF-8");
    }
    catch (IOException e) {
        e.printStackTrace();
    }
    return jsonFile;
    }
    public void getHoroscopeDetails() {
        Call<ZodiacDetailsData> call = ApiClient.getMyInterface().getSign("1","69a89336-0757-40cd-9daf-0e3073998723",
                "1.0,","1","Leo", "Today");
        call.enqueue(new Callback<ZodiacDetailsData>() {
            @Override
            public void onResponse(@NonNull Call<ZodiacDetailsData> call, @NonNull Response<ZodiacDetailsData> response) {
                ZodiacDetailsData model=response.body();
                if (model != null) {
                   String Sign=model.getCompatibility();
                   zodiacTitle.setText(Sign);
                   String date= model.getCurrentDate();
                   current_date.setText(date);
                   String luckyNumber=model.getLuckyNumber();
                   lkyNumber.setText(luckyNumber);
                   String lucky_time=model.getLuckyTime();
                   lkyTime.setText(lucky_time);
                   String lucky_color=model.getColor();
                   lkyColor.setText(lucky_color);
                   String mood_of_day=model.getMood();
                   mood.setText(mood_of_day);
                   String date_range=model.getDateRange();
                   zodiacDOB.setText(date_range);
                }
            }
            @Override
            public void onFailure(@NonNull Call<ZodiacDetailsData> call, @NonNull Throwable t) {
            }
        });
    }
}