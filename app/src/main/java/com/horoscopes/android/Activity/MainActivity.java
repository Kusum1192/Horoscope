package com.horoscopes.android.Activity;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.horoscopes.android.Adapter.VPHomeAdapter;
import com.horoscopes.android.BuildConfig;
import com.horoscopes.android.R;
import com.squareup.picasso.Picasso;

import java.text.BreakIterator;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TabLayout tabLayout;
    TabItem tab1, tab2;
    ViewPager viewPager;
    VPHomeAdapter fragmentManager;
    private FirebaseAuth mAuth;
    private AlertDialog.Builder alertDialog;
    TextView username_tv,Email_tv;
    private ImageView profile_image;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setTitle("Daily Horoscopes");
        toolbar=findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        username_tv=findViewById(R.id.user_name);
        //username_tv.setText(mAuth.getCurrentUser().getDisplayName());
        Email_tv=findViewById(R.id.user_email_id);
        //Email_tv.setText(mAuth.getCurrentUser().getEmail());
        tabLayout=findViewById(R.id.tabLayout);
        tab1=findViewById(R.id.tab_horoscope);
        tab2=findViewById(R.id.tab_tarot);
        viewPager=findViewById(R.id.vp_holder);
        navigationView=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawer);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
       TextView name = navigationView.getHeaderView(0).findViewById(R.id.user_name);
       name.setText((mAuth.getCurrentUser()).getDisplayName());
        TextView email = navigationView.getHeaderView(0).findViewById(R.id.user_email_id);
        email.setText(mAuth.getCurrentUser().getEmail());
       profile_image = navigationView.getHeaderView(0).findViewById(R.id.user_img);
       Picasso.get().load(mAuth.getCurrentUser().getPhotoUrl()).into(profile_image);
        fragmentManager=new VPHomeAdapter(getSupportFragmentManager(),
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
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
       // throw new RuntimeException("Test Crash"); // Force a crash
    }
    public void shareAppOther() {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            SharedPreferences sharedPreferences1 = getSharedPreferences("MySharedPreference", MODE_PRIVATE);
            String invite = sharedPreferences1.getString("invite", "");
            shareIntent.setType("text/plain");
           shareIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
          //  String shareMessage = invite + BuildConfig.APPLICATION_ID +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, invite);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
           System.out.println(e);
        }
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_profile:
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE);
                boolean isSubmit = sharedPreferences.getBoolean("isSubmitted",false);
                if (!isSubmit){
                    startActivity(new Intent(this, ProfileActivity.class));
                }else
                    startActivity(new Intent(this,BirthDetailsActivity.class));

                //Toast.makeText(MainActivity.this, "Profile page is Open", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_share:
                shareAppOther();
                break;
            case R.id.menu_rate_us:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + this.getPackageName())));
                break;
            case R.id.menu_pp:
                    CustomTabsIntent.Builder customIntent = new CustomTabsIntent.Builder();
                    customIntent.setToolbarColor(ContextCompat.getColor(MainActivity.this, R.color.purple_500));
                    openCustomTab(MainActivity.this, customIntent.build(), Uri.parse("https://mobnews.app/astroapp/privacy_policy"));
                break;
            case R.id.menu_tns:
                CustomTabsIntent.Builder custIntent=new CustomTabsIntent.Builder();
                custIntent.setToolbarColor(ContextCompat.getColor(MainActivity.this, R.color.purple_500));
                openCustomTab(MainActivity.this,custIntent.build(),Uri.parse("https://mobnews.app/astroapp/terms_conditions\n"));
                break;
            case R.id.about_us:
                alertDialog=new AlertDialog.Builder(MainActivity.this);
                alertDialog.setIcon(R.mipmap.ic_launcher);
                alertDialog.setTitle("Horoscope");
                alertDialog.setMessage("\t\t\t\t\t\tVersion 1.2\n"
                        +"MINDGEEKS TECH LLP\n"
                        +"H.NO-9FF, HB COLONY, SECTOR-48,\n"
                        +"GURUGRAM, PIN-122001\n\n"
                        +"Email: apphelpmg@gmail.com\n"
                        +"Phone: +91-7027002011\n"
                        +"Copyright@ 2022 MindGeeks  Developer.\n"
                        +"\t\t\t\t\t\tAll right reserved"
                );
                alertDialog.setPositiveButton("Ok", (dialogInterface, i) -> alertDialog.setOnDismissListener(DialogInterface::dismiss));
                AlertDialog dialog=alertDialog.create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.setOnShowListener(dialogInterface -> {
                    dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setTextColor(getColor(R.color.purple_700));
                });
                dialog.show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void openCustomTab(Context activity, CustomTabsIntent customTabsIntent, Uri uri) {
        customTabsIntent.launchUrl(activity, uri);
    }
    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(R.mipmap.ic_launcher);
        //builder.setIconAttribute(android.R.attr.alertDialogIcon);
        builder.setMessage("Do you want to exit ?");
        builder.setTitle("Horoscope");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (dialog, which) -> finish());
        builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(dialogInterface -> {
            alertDialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(getColor(R.color.purple_500));
            alertDialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setTextColor(getColor(R.color.purple_500));
        });
        alertDialog.show();
    }
}