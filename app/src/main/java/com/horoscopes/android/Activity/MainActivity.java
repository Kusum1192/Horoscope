package com.horoscopes.android.Activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.horoscopes.android.Adapter.FragmentAdapter;
import com.horoscopes.android.BuildConfig;
import com.horoscopes.android.R;

public class MainActivity extends AppCompatActivity {
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TabLayout tabLayout;
    TabItem tab1, tab2;
    ViewPager viewPager;
    FragmentAdapter fragmentManager;
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setTitle("Horoscopes");
        toolbar=findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        tabLayout=findViewById(R.id.tabLayout);
        tab1=findViewById(R.id.tab_horoscope);
        tab2=findViewById(R.id.tab_tarot);
        viewPager=findViewById(R.id.vp_holder);
        navigationView=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawer);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        fragmentManager=new FragmentAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabLayout.getTabCount());
        viewPager.setAdapter(fragmentManager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_horoscope:
                    Toast.makeText(MainActivity.this, "Home Panel is Open", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menu_tarot_card:
                    Toast.makeText(MainActivity.this, "Tarot card is open", Toast.LENGTH_SHORT).show();
                    break;

                case R.id.menu_profile:
                    startActivity(new Intent(this,ProfileActivity.class));
                    //Toast.makeText(MainActivity.this, "Profile page is Open", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menu_share:
                    shareAppOther();
                    break;
                case R.id.menu_rate_us:
                    Toast.makeText(MainActivity.this, "Rate us is open", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menu_pp:
                    Toast.makeText(MainActivity.this, "Privacy Policy is open", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menu_tns:
                    Toast.makeText(MainActivity.this, "Terms and Services is open", Toast.LENGTH_SHORT).show();
                    break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });


    }
    public void shareAppOther() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
            String shareMessage= "\nHey, I just found out my future using Horoscope. You can find yours too at this amazing app try out !!!\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
         //   System.out.println(e);
        }
    }

}