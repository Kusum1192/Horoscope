package com.horoscopes.android.Activity;

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
import com.horoscopes.android.Adapter.DaysAdapter;
import com.horoscopes.android.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class HoroscopeDetailActivity extends AppCompatActivity {

    Toolbar toolbarWidget;
    ViewPager viewPager;
    DaysAdapter daysAdapter;
    TabLayout tb_lyt_days;
    TabItem tabYesterday, tabToday, tabTomorrow, tabWeek, tabMonth;
    ImageView zodiacSign;
    String sign,alias,startDate,endDate,content;
    TextView zodiacAlias, zodiacTitle,zodiacDOB,content_tv;
    String name;

    @SuppressLint({"SetTextI18n", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope_detail);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        zodiacSign=findViewById(R.id.zodiacSignImg);
        zodiacAlias=findViewById(R.id.zodiacSignAlias);
        zodiacTitle=findViewById(R.id.zodiacSignTitle);
        zodiacDOB=findViewById(R.id.zodiacSignDOB);
        content_tv=findViewById(R.id.contentId);
        toolbarWidget = findViewById(R.id.toolbar);
        tb_lyt_days = findViewById(R.id.tabLayout_days);
        tabYesterday = findViewById(R.id.yesterday_TI);
        tabToday = findViewById(R.id.today_TI);
        tabTomorrow = findViewById(R.id.tomorrow_TI);
        tabWeek = findViewById(R.id.week_TI);
        tabMonth = findViewById(R.id.month_TI);
        viewPager = findViewById(R.id.vp);
        toolbarWidget.setTitle("Horoscope Reading Details");
        setSupportActionBar(toolbarWidget);
        toolbarWidget.setNavigationIcon(R.drawable.ic_back);
        toolbarWidget.setNavigationOnClickListener(v -> onBackPressed());
        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        TextView tv = findViewById(R.id.date);
        tv.setText(currentDateTimeString);
        daysAdapter = new DaysAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tb_lyt_days.getTabCount());
        viewPager.setAdapter(daysAdapter);
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
                alias = zodiacList.getJSONObject(id).getString("alias");
                startDate = zodiacList.getJSONObject(id).getString("startDate");
                endDate = zodiacList.getJSONObject(id).getString("endDate");
                content = zodiacList.getJSONObject(id).getString("content");
        } catch (JSONException e) {
            e.printStackTrace();
        }
         int zodiacImag=getIntent().getIntExtra("image",0);
        Picasso.get().load(zodiacImag).into(zodiacSign);
        zodiacTitle.setText(sign);
        zodiacAlias.setText(alias);
        zodiacDOB.setText(startDate+" to "+endDate);
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
}