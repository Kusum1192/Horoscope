package com.horoscopes.android.Activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.horoscopes.android.Model.TarotCardData;
import com.horoscopes.android.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class TarotDetailActivity extends AppCompatActivity {
     String adapterPosition;
     String name,content;
     int Position;
    List<TarotCardData> tarotCardData = new ArrayList<>();
    Toolbar toolbar_td;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarot_detail);
        toolbar_td=findViewById(R.id.toolbar);
        toolbar_td.setTitle("Tarot Card Reading Details");
        setSupportActionBar(toolbar_td);
        toolbar_td.setNavigationIcon(R.drawable.ic_back);
        toolbar_td.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TarotDetailActivity.this.onBackPressed();
            }
        });
        ImageView imageView = findViewById(R.id.card);
        int[] cards = {R.drawable.ace_of_cups, R.drawable.two_of_cups, R.drawable.three_of_cups, R.drawable.four_of_cups, R.drawable.five_of_cups,
                R.drawable.six_of_cups, R.drawable.seven_of_cups, R.drawable.six_of_cups, R.drawable.seven_of_cups, R.drawable.eight_of_cups,
                R.drawable.nine_of_cups, R.drawable.ten_of_cups, R.drawable.page_of_cups, R.drawable.knight_of_cups, R.drawable.queen_of_cups,
                R.drawable.king_of_cups, R.drawable.ace_of_pentacles, R.drawable.two_of_pentacles, R.drawable.three_of_pentacles, R.drawable.four_of_pentacles,
                R.drawable.five_of_pentacles, R.drawable.six_of_pentacles, R.drawable.seven_of_pentacles, R.drawable.eight_of_pentacles, R.drawable.nine_of_pentacles,
                R.drawable.ten_of_pentacles, R.drawable.page_of_pentacles, R.drawable.knight_of_pentacles, R.drawable.queen_of_pentacles, R.drawable.king_of_pentacles,
                R.drawable.ace_of_swords, R.drawable.two_of_swords, R.drawable.three_of_swords, R.drawable.four_of_swords, R.drawable.five_of_swords,
                R.drawable.six_of_swords, R.drawable.seven_of_swords, R.drawable.eight_of_swords, R.drawable.nine_of_swords, R.drawable.ten_of_swords,
                R.drawable.page_of_swords, R.drawable.knight_of_swords, R.drawable.queen_of_swords, R.drawable.king_of_swords, R.drawable.ace_of_wands,
                R.drawable.two_of_wands, R.drawable.three_of_wands, R.drawable.four_of_wands, R.drawable.five_of_wands, R.drawable.six_of_wands,
                R.drawable.seven_of_wands, R.drawable.eight_of_wands, R.drawable.nine_of_wands, R.drawable.ten_of_wands, R.drawable.page_of_wands,
                R.drawable.knight_of_wands, R.drawable.queen_of_wands, R.drawable.king_of_wands, R.drawable.themagician, R.drawable.thehighpreiestess,
                R.drawable.theempress, R.drawable.theemperor, R.drawable.thehierophant, R.drawable.thelovers, R.drawable.thechariot,
                R.drawable.strength, R.drawable.thehermit, R.drawable.wheel_of_fortune, R.drawable.justice, R.drawable.the_hanged_man,
                R.drawable.death, R.drawable.temperance, R.drawable.the_devil, R.drawable.the_tower, R.drawable.the_star,
                R.drawable.the_moon, R.drawable.the_sun, R.drawable.judgement, R.drawable.the_world, R.drawable.thefool,};
        Intent intent = getIntent();
       if (intent != null) {
          adapterPosition = String.valueOf(intent.getIntExtra("position", 0));
        //    Log.e(TAG, "onCreate: "+adapterPosition );
        }
        imageView.setImageResource(cards[Integer.parseInt(adapterPosition)]);
        getJsonFileFromLocally(adapterPosition);
    }
    private void getJsonFileFromLocally(String adapterPosition) {
        try {

            JSONObject jsonObject = new JSONObject(HoroscopeDetailActivity.loadJSONFromAssets(this));

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
                TextView tv_restext = findViewById(R.id.restext);
//                Log.e(TAG, "getJsonFileFromLocally:content "+zodiacModelList.get(0).getZodiacContent() );
                tv_restext.setText(tarotCardData.get(Integer.parseInt(adapterPosition)).getContent());
                tv_cardName.setText(tarotCardData.get(Integer.parseInt(adapterPosition)).getName());
//                employeeAdapter.dataChanged(employeeModelArrayList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
}