package com.horoscopes.android.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.horoscopes.android.ApiClient;
import com.horoscopes.android.BuildConfig;
import com.horoscopes.android.Fragment.TodayFragment;
import com.horoscopes.android.Fragment.TomorrowFragment;
import com.horoscopes.android.Fragment.YesterdayFragment;
import com.horoscopes.android.Model.AppOpen;
import com.horoscopes.android.Model.TarotCardData;
import com.horoscopes.android.Model.ZodiacDetailsData;
import com.horoscopes.android.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TarotDetailActivity extends AppCompatActivity {
     String adapterPosition;
    List<TarotCardData> tarotCardData = new ArrayList<>();
    Toolbar toolbar_td;
    RelativeLayout banner_rl;
    ImageView ads_image;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarot_detail);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        toolbar_td=findViewById(R.id.toolbar);
        toolbar_td.setTitle("Tarot Card Reading Details");
        setSupportActionBar(toolbar_td);
        bar=findViewById(R.id.progressBar);
        toolbar_td.setNavigationIcon(R.drawable.ic_back);
        banner_rl=findViewById(R.id.banner_tc);
        ads_image=findViewById(R.id.ads_image);

        SharedPreferences sharedPreferences1 = getSharedPreferences("MySharedPreference", MODE_PRIVATE);
        String advertisement=sharedPreferences1.getString("bannerImage","");
        String advertisementLink=sharedPreferences1.getString("bannerLink","");
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
                customIntent.setToolbarColor(ContextCompat.getColor(TarotDetailActivity.this, R.color.purple_500));
                openCustomTab(TarotDetailActivity.this, customIntent.build(), Uri.parse(advertisementLink));
            }
        });
        toolbar_td.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TarotDetailActivity.this.onBackPressed();
            }
        });
        ImageView imageView = findViewById(R.id.card);
        String[] cards = {"https://horoscopee.herokuapp.com/ace_of_cups.PNG","https://horoscopee.herokuapp.com/two_of_cups.PNG","https://horoscopee.herokuapp.com/three_of_cups.PNG",
                "https://horoscopee.herokuapp.com/four_of_cups.PNG","https://horoscopee.herokuapp.com/five_of_cups.PNG",
                "https://horoscopee.herokuapp.com/six_of_cups.PNG","https://horoscopee.herokuapp.com/seven_of_cups.PNG",
                "https://horoscopee.herokuapp.com/six_of_cups.PNG","https://horoscopee.herokuapp.com/seven_of_cups.PNG",
                "https://horoscopee.herokuapp.com/eight_of_cups.PNG","https://horoscopee.herokuapp.com/nine_of_cups.PNG",
                "https://horoscopee.herokuapp.com/ten_of_cups.PNG","https://horoscopee.herokuapp.com/page_of_cups.PNG",
                "https://horoscopee.herokuapp.com/knight_of_cups.PNG","https://horoscopee.herokuapp.com/queen_of_cups.PNG",
                "https://horoscopee.herokuapp.com/king_of_cups.PNG","https://horoscopee.herokuapp.com/ace_of_pentacles.PNG",
                "https://horoscopee.herokuapp.com/two_of_pentacles.PNG","https://horoscopee.herokuapp.com/three_of_pentacles.PNG",
                "https://horoscopee.herokuapp.com/four_of_pentacles.PNG","https://horoscopee.herokuapp.com/five_of_pentacles.PNG",
                "https://horoscopee.herokuapp.com/six_of_pentacles", "https://horoscopee.herokuapp.com/seven_of_pentacles",
                "https://horoscopee.herokuapp.com/eight_of_pentacles", "https://horoscopee.herokuapp.com/nine_of_pentacles",
                "https://horoscopee.herokuapp.com/ten_of_pentacles", "https://horoscopee.herokuapp.com/page_of_pentacles",
                "https://horoscopee.herokuapp.com/knight_of_pentacles", "https://horoscopee.herokuapp.com/queen_of_pentacles",
                "https://horoscopee.herokuapp.com/king_of_pentacles","https://horoscopee.herokuapp.com/ace_of_swords",
                "https://horoscopee.herokuapp.com/two_of_swords", "https://horoscopee.herokuapp.com/three_of_swords",
                "https://horoscopee.herokuapp.com/four_of_swords", "https://horoscopee.herokuapp.com/five_of_swords",
                "https://horoscopee.herokuapp.com/six_of_swords", "https://horoscopee.herokuapp.com/seven_of_swords",
        };
        Intent intent = getIntent();
       if (intent != null) {
          adapterPosition = String.valueOf(intent.getIntExtra("position", 0));
        //    Log.e(TAG, "onCreate: "+adapterPosition );
        }
        Picasso.get().load(cards[Integer.parseInt(adapterPosition)]).placeholder(R.drawable.bg_card).into(imageView);
       // imageView.setImageResource(cards(adapterPosition));
        getJsonFileFromLocally(adapterPosition);
    }
    private void getJsonFileFromLocally(String adapterPosition) {
        try {

            JSONObject jsonObject = new JSONObject(loadJSONFromAssets(this));

            JSONArray jsonArray = jsonObject.getJSONArray("familyAndFriends");                  //TODO pass array object name
            //Log.e("keshav", "m_jArry -->" + jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++)
            {
                TarotCardData tarotCardModel = new TarotCardData();
                JSONObject jsonObjectUser = jsonArray.getJSONObject(i);
                String content = jsonObjectUser.getString("details");
                String name = jsonObjectUser.getString("name");
                tarotCardModel.setName(""+name);
                tarotCardModel.setContent(""+content);
                tarotCardData.add(tarotCardModel);
            }       // for
            if(tarotCardData!=null) {
                TextView tv_cardName = findViewById(R.id.cardName);
                TextView tv_restext = findViewById(R.id.res_text);
//                Log.e(TAG, "getJsonFileFromLocally:content "+zodiacModelList.get(0).getZodiacContent() );
                tv_restext.setText(tarotCardData.get(Integer.parseInt(adapterPosition)).getContent());
                tv_cardName.setText(tarotCardData.get(Integer.parseInt(adapterPosition)).getName());
//                employeeAdapter.dataChanged(employeeModelArrayList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }public static String loadJSONFromAssets(Context context) {
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
   @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent firstIntent = new Intent(this,MainActivity.class);
        firstIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(firstIntent);
        }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }


            private void openCustomTab(Context context, CustomTabsIntent customTabsIntent, Uri uri) {
                customTabsIntent.launchUrl(context, uri);
            }

        }
