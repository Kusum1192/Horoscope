package com.horoscopes.android.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.horoscopes.android.Adapter.VPDaysAdapter;
import com.horoscopes.android.ApiClient;
import com.horoscopes.android.BuildConfig;
import com.horoscopes.android.Fragment.TodayFragment;
import com.horoscopes.android.Fragment.TomorrowFragment;
import com.horoscopes.android.Fragment.YesterdayFragment;
import com.horoscopes.android.Model.ZodiacDetailsData;
import com.horoscopes.android.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HoroscopeDetailActivity extends AppCompatActivity {
    String lkyNum_tdy, lkyTim_tdy, lkyClr_tdy, mood_tdy;
    String lkyNum_tmr, lkyTim_tmr, lkyClr_tmr, mood_tmr;
    String lkyNum_yes, lkyTim_yes, lkyClr_yes, mood_yes;
    Toolbar toolbarWidget;
    ViewPager viewPager;
    VPDaysAdapter VPDaysAdapter;
    TabLayout tb_lyt_days;
    TabItem tabYesterday, tabToday, tabTomorrow;
    ImageView zodiacSign_iv;
    TextView zodiacTitle_tv;
    TextView current_date_tv, lkyNumber_tv, lkyTime_tv, lkyColor_tv, mood_tv;
    private ProgressBar bar;
    private ImageView ads_image;
    private RelativeLayout banner_rl;
    CoordinatorLayout coordinatorLayout;
    String currentDateTimeString;
    static int DaySign = 0;


    @SuppressLint({"SetTextI18n", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope_detail);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //zodiac details
        bar = findViewById(R.id.progressBar);
        banner_rl = findViewById(R.id.banner);
        ads_image = findViewById(R.id.ads_image);
        zodiacSign_iv = findViewById(R.id.zodiacSignImg);
        zodiacTitle_tv = findViewById(R.id.zodiacSignTitle);
        lkyNumber_tv = findViewById(R.id.zodiacNumber);
        lkyTime_tv = findViewById(R.id.zodiacTime);
        lkyColor_tv = findViewById(R.id.colorName);
        mood_tv = findViewById(R.id.Mood);
        current_date_tv = findViewById(R.id.date);
        coordinatorLayout = findViewById(R.id.appbar_container);

        /* <-----------------toolbarWidget------------->*/
        toolbarWidget = findViewById(R.id.toolbar);
        toolbarWidget.setTitle("Horoscope Reading Details");
        setSupportActionBar(toolbarWidget);
        toolbarWidget.setNavigationIcon(R.drawable.ic_back);
        toolbarWidget.setNavigationOnClickListener(v -> onBackPressed());

        /*<-------------------Tab Layout---------------->*/
        tb_lyt_days = findViewById(R.id.tabLayout_days);
        tabYesterday = findViewById(R.id.yesterday_TI);
        tabToday = findViewById(R.id.today_TI);
        tabTomorrow = findViewById(R.id.tomorrow_TI);
        viewPager = findViewById(R.id.vp);
//        viewPager.setSelected(true);
        //  tb_lyt_days.selectTab();

        currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        VPDaysAdapter = new VPDaysAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tb_lyt_days.getTabCount());

        getHoroscopeDetails();
    }

    private String makeDateString(int day, int month, int year) {
        return  day + "-" + month + "-" + year;
    }

    private String getMonthFormat(int day, int month, int year) {
        return " " + year + " " + month + " " + day;
    }
    private String getDate(int i) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day;
        //next day
        if (i == 1) {
            SimpleDateFormat date= new SimpleDateFormat("dd-MM-yyyy");
            Calendar calendar=Calendar.getInstance();
            calendar.add(Calendar.DATE,+1);
            String tomorrow=date.format(calendar.getTime());
            return tomorrow;
        }
        else if (i == 2) {
            // pre day
            SimpleDateFormat date= new SimpleDateFormat("dd-MM-yyyy");
            Calendar calendar=Calendar.getInstance();
            calendar.add(Calendar.DATE,-1);
            String yesterday=date.format(calendar.getTime());
            return yesterday;
           // return makeDateString(day, month, year);
        }
        else {   //today
            day = cal.get(Calendar.DAY_OF_MONTH);
            return makeDateString(day, month, year);
        }
    }
    public void getHoroscopeDetails() {
        String name = getIntent().getStringExtra("name");
        SharedPreferences sharedPreferences1 = getSharedPreferences("MySharedPreference", MODE_PRIVATE);
        String advertisement=sharedPreferences1.getString("bannerImage","");
        String advertisementLink=sharedPreferences1.getString("bannerLink","");
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String securityToken = sharedPreferences.getString("securityToken", "");
        long userId = sharedPreferences.getLong("user", 1);
        Call<ZodiacDetailsData> call = ApiClient.getMyInterface().getSign(userId, securityToken,
                BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE, name);
        call.enqueue(new Callback<ZodiacDetailsData>() {
            @Override
            public void onResponse(@NonNull Call<ZodiacDetailsData> call, @NonNull Response<ZodiacDetailsData> response) {
                bar.setVisibility(View.INVISIBLE);
                ZodiacDetailsData model = response.body();
                coordinatorLayout.setVisibility(View.VISIBLE);
                if (model != null && model.getMessage().matches("Success")) {
                    String zodiacImg = getIntent().getStringExtra("icon");
                    Picasso.get().load(zodiacImg).into(zodiacSign_iv);
                    zodiacTitle_tv.setText(name);
                    if (advertisement != null && !advertisement.isEmpty()) {
                        banner_rl.setVisibility(View.VISIBLE);
                        Picasso.get().load(advertisement).into(ads_image);
                    } else {
                        banner_rl.setVisibility(View.GONE);
                    }
                    ads_image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CustomTabsIntent.Builder customIntent = new CustomTabsIntent.Builder();
                            customIntent.setToolbarColor(ContextCompat.getColor(HoroscopeDetailActivity.this, R.color.purple_500));
                            openCustomTab(HoroscopeDetailActivity.this, customIntent.build(), Uri.parse(advertisementLink));
                        }
                    });
                    ZodiacDetailsData.Yesterday Ydata = model.getYesterday();
                    lkyColor_tv.setText(Ydata.getColor());
                    lkyTime_tv.setText(Ydata.getLuckyTime());
                    lkyNumber_tv.setText(Ydata.getLuckyNumber());
                    mood_tv.setText(Ydata.getMood());
                    lkyClr_yes = Ydata.getColor();
                    lkyNum_yes = Ydata.getLuckyNumber();
                    lkyTim_yes = Ydata.getLuckyTime();
                    mood_yes = Ydata.getMood();
                    VPDaysAdapter.addFrag(YesterdayFragment.newInstance(Ydata.getDescription()));
                    ZodiacDetailsData.Today data = model.getToday();
                    lkyClr_tdy = data.getColor();
                    lkyNum_tdy = data.getLuckyNumber();
                    lkyTim_tdy = data.getLuckyTime();
                    mood_tdy = data.getMood();
                    VPDaysAdapter.addFrag(TodayFragment.newInstance(data.getDescription()));
                    ZodiacDetailsData.Tomorrow tmrData = model.getTomorrow();
                    lkyClr_tmr = tmrData.getColor();
                    lkyNum_tmr = tmrData.getLuckyNumber();
                    lkyTim_tmr = tmrData.getLuckyTime();
                    mood_tmr = tmrData.getMood();
                    VPDaysAdapter.addFrag(TomorrowFragment.newInstance(tmrData.getDescription()));
                    viewPager.setAdapter(VPDaysAdapter);
                    tb_lyt_days.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            viewPager.setCurrentItem(tab.getPosition());
                            int pos = tab.getPosition();
                            if (pos == 0) {
                                lkyColor_tv.setText(lkyClr_yes);
                                lkyNumber_tv.setText(lkyNum_yes);
                                lkyTime_tv.setText(lkyTim_yes);
                                mood_tv.setText(mood_yes);
                                current_date_tv.setText(getDate(2));
                            }
                            if (pos == 1) {
                                lkyColor_tv.setText(lkyClr_tdy);
                                lkyNumber_tv.setText(lkyNum_tdy);
                                lkyTime_tv.setText(lkyTim_tdy);
                                mood_tv.setText(mood_tdy);
                                current_date_tv.setText(getDate(3));
                            }
                            if (pos == 2) {
                                lkyColor_tv.setText(lkyClr_tmr);
                                lkyNumber_tv.setText(lkyNum_tmr);
                                lkyTime_tv.setText(lkyTim_tmr);
                                mood_tv.setText(mood_tmr);
                                current_date_tv.setText(getDate(1));
                            }
                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {
                        }
                    });
                    viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tb_lyt_days));
                    viewPager.setCurrentItem(1);

                }
            }

            @Override
            public void onFailure(@NonNull Call<ZodiacDetailsData> call, @NonNull Throwable t) {
            }
        });
    }
    private void openCustomTab(Context context, CustomTabsIntent customTabsIntent, Uri uri) {
        customTabsIntent.launchUrl(context, uri);
    }
}
