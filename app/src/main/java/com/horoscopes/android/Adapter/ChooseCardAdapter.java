package com.horoscopes.android.Adapter;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.horoscopes.android.Activity.TarotDetailActivity;
import com.horoscopes.android.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class ChooseCardAdapter extends RecyclerView.Adapter<ChooseCardAdapter.viewHolder> {
    ArrayList<String> cardImages;
    Context context;
    public ChooseCardAdapter(ArrayList<String> cardImages, Context context) {
        this.cardImages = cardImages;
        this.context = context;
    }
    @NonNull
    @Override
    public ChooseCardAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarot_card_item,parent,false);
        return new viewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ChooseCardAdapter.viewHolder holder, int position) {
        holder.choose_card.setImageResource(Integer.parseInt(cardImages.get(position)));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ObjectAnimator oa1 = ObjectAnimator.ofFloat(holder.itemView, "scaleX", 1f, 0f);
                final ObjectAnimator oa2 = ObjectAnimator.ofFloat(holder.itemView, "scaleX", 0f, 1f);
                oa1.setInterpolator(new DecelerateInterpolator());
                oa2.setInterpolator(new AccelerateDecelerateInterpolator());
               oa1.addListener(new AnimatorListenerAdapter() {
                   @Override
                   public void onAnimationEnd(Animator animation) {
                       super.onAnimationEnd(animation);
                       String[] cards = {"https://horoscopee.herokuapp.com/ace_of_cups.PNG","https://horoscopee.herokuapp.com/two_of_cups.PNG","https://horoscopee.herokuapp.com/three_of_cups.PNG",
                               "https://horoscopee.herokuapp.com/four_of_cups.PNG","https://horoscopee.herokuapp.com/five_of_cups.PNG","https://horoscopee.herokuapp.com/six_of_cups.PNG",
                               "https://horoscopee.herokuapp.com/seven_of_cups.PNG","https://horoscopee.herokuapp.com/six_of_cups.PNG",
                               "https://horoscopee.herokuapp.com/seven_of_cups.PNG","https://horoscopee.herokuapp.com/eight_of_cups.PNG",
                               "https://horoscopee.herokuapp.com/nine_of_cups.PNG","https://horoscopee.herokuapp.com/ten_of_cups.PNG",
                               "https://horoscopee.herokuapp.com/page_of_cups.PNG","https://horoscopee.herokuapp.com/knight_of_cups.PNG",
                               "https://horoscopee.herokuapp.com/queen_of_cups.PNG","https://horoscopee.herokuapp.com/king_of_cups.PNG",
                               "https://horoscopee.herokuapp.com/ace_of_pentacles.PNG","https://horoscopee.herokuapp.com/two_of_pentacles.PNG",
                               "https://horoscopee.herokuapp.com/three_of_pentacles.PNG","https://horoscopee.herokuapp.com/four_of_pentacles.PNG",
                               "https://horoscopee.herokuapp.com/five_of_pentacles.PNG","https://horoscopee.herokuapp.com/six_of_pentacles",
                                "https://horoscopee.herokuapp.com/seven_of_pentacles","https://horoscopee.herokuapp.com/eight_of_pentacles",
                                "https://horoscopee.herokuapp.com/nine_of_pentacles", "https://horoscopee.herokuapp.com/ten_of_pentacles",
                                "https://horoscopee.herokuapp.com/page_of_pentacles","https://horoscopee.herokuapp.com/knight_of_pentacles",
                                "https://horoscopee.herokuapp.com/queen_of_pentacles","https://horoscopee.herokuapp.com/king_of_pentacles",
                                "https://horoscopee.herokuapp.com/ace_of_swords","https://horoscopee.herokuapp.com/two_of_swords",
                                "https://horoscopee.herokuapp.com/three_of_swords","https://horoscopee.herokuapp.com/four_of_swords",
                                 "https://horoscopee.herokuapp.com/five_of_swords","https://horoscopee.herokuapp.com/six_of_swords",
                                "https://horoscopee.herokuapp.com/seven_of_swords","https://horoscopee.herokuapp.com/eight_of_swords",
                                "https://horoscopee.herokuapp.com/nine_of_swords","https://horoscopee.herokuapp.com/ten_of_swords",
                               "https://horoscopee.herokuapp.com/page_of_swords","https://horoscopee.herokuapp.com/knight_of_swords",
                               "https://horoscopee.herokuapp.com/queen_of_swords","https://horoscopee.herokuapp.com/king_of_swords",
                               "https://horoscopee.herokuapp.com/ace_of_wands","https://horoscopee.herokuapp.com/two_of_wands",
                               "https://horoscopee.herokuapp.com/three_of_wands","https://horoscopee.herokuapp.com/four_of_wands",
                               "https://horoscopee.herokuapp.com/five_of_wands","https://horoscopee.herokuapp.com/six_of_wands",
                               "https://horoscopee.herokuapp.com/seven_of_wands","https://horoscopee.herokuapp.com/eight_of_wands",
                               "https://horoscopee.herokuapp.com/nine_of_wands","https://horoscopee.herokuapp.com/ten_of_wands",
                               "https://horoscopee.herokuapp.com/page_of_wands","https://horoscopee.herokuapp.com/knight_of_wands",
                               "https://horoscopee.herokuapp.com/queen_of_wands","https://horoscopee.herokuapp.com/king_of_wands",
                               "https://horoscopee.herokuapp.com/themagician","https://horoscopee.herokuapp.com/thehighpreiestess",
                               "https://horoscopee.herokuapp.com/theempress","https://horoscopee.herokuapp.com/",
                               "https://horoscopee.herokuapp.com/theemperor","https://horoscopee.herokuapp.com/thehierophant",
                               "https://horoscopee.herokuapp.com/thelovers","https://horoscopee.herokuapp.com/thechariot",
                               "https://horoscopee.herokuapp.com/strength","https://horoscopee.herokuapp.com/thehermit",
                               "https://horoscopee.herokuapp.com/wheel_of_fortune","https://horoscopee.herokuapp.com/justice",
                               "https://horoscopee.herokuapp.com/the_hanged_man", "https://horoscopee.herokuapp.com/death",
                               "https://horoscopee.herokuapp.com/temperance","https://horoscopee.herokuapp.com/the_devil",
                               "https://horoscopee.herokuapp.com/the_tower","https://horoscopee.herokuapp.com/the_star",
                               "https://horoscopee.herokuapp.com/the_moon","https://horoscopee.herokuapp.com/the_sun",
                               "https://horoscopee.herokuapp.com/judgement","https://horoscopee.herokuapp.com/the_world",
                               "https://horoscopee.herokuapp.com/thefool"};
                       Random r = new Random();
                       final int n = r.nextInt(5);
                       Picasso.get().load(cards[n]).into(holder.choose_card);
                      // holder.choose_card.setImageResource((cards[n]));
                       oa2.start();
                       new Handler( ).postDelayed(() -> {
                           Intent intent = new Intent(context, TarotDetailActivity.class);
                           intent.putExtra("position", n);
                           context.startActivity(intent);
                       },1000);
                   }
               });
                oa1.start();
                oa1.setAutoCancel(true);
            }
        });

    }
    @Override
    public int getItemCount() {
        return cardImages.size();
    }
    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView choose_card;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            choose_card=itemView.findViewById(R.id.chooseCard_iv);
        }
    }
}
