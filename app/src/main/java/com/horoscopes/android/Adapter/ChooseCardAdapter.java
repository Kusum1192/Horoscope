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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ChooseCardAdapter extends RecyclerView.Adapter<ChooseCardAdapter.viewHolder> {
    ArrayList<Integer> cardImages;
    Context context;

    public ChooseCardAdapter(ArrayList<Integer> cardImages, Context context) {
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
        holder.choose_card.setImageResource((Integer) cardImages.get(position));
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
                       int [] cards = {R.drawable.ace_of_cups,R.drawable.two_of_cups,R.drawable.three_of_cups,R.drawable.four_of_cups,R.drawable.five_of_cups,
                               R.drawable.six_of_cups,R.drawable.seven_of_cups,R.drawable.six_of_cups,R.drawable.seven_of_cups,R.drawable.eight_of_cups,
                               R.drawable.nine_of_cups,R.drawable.ten_of_cups,R.drawable.page_of_cups,R.drawable.knight_of_cups,R.drawable.queen_of_cups,
                               R.drawable.king_of_cups,R.drawable.ace_of_pentacles,R.drawable.two_of_pentacles,R.drawable.three_of_pentacles,R.drawable.four_of_pentacles,
                               R.drawable.five_of_pentacles,R.drawable.six_of_pentacles,R.drawable.seven_of_pentacles,R.drawable.eight_of_pentacles,R.drawable.nine_of_pentacles,
                               R.drawable.ten_of_pentacles,R.drawable.page_of_pentacles,R.drawable.knight_of_pentacles,R.drawable.queen_of_pentacles,R.drawable.king_of_pentacles,
                               R.drawable.ace_of_swords,R.drawable.two_of_swords,R.drawable.three_of_swords,R.drawable.four_of_swords,R.drawable.five_of_swords,
                               R.drawable.six_of_swords,R.drawable.seven_of_swords,R.drawable.eight_of_swords,R.drawable.nine_of_swords,R.drawable.ten_of_swords,
                               R.drawable.page_of_swords,R.drawable.knight_of_swords,R.drawable.queen_of_swords,R.drawable.king_of_swords,R.drawable.ace_of_wands,
                               R.drawable.two_of_wands,R.drawable.three_of_wands,R.drawable.four_of_wands,R.drawable.five_of_wands,R.drawable.six_of_wands,
                               R.drawable.seven_of_wands,R.drawable.eight_of_wands,R.drawable.nine_of_wands,R.drawable.ten_of_wands,R.drawable.page_of_wands,
                               R.drawable.knight_of_wands,R.drawable.queen_of_wands,R.drawable.king_of_wands,R.drawable.themagician,R.drawable.thehighpreiestess,
                               R.drawable.theempress,R.drawable.theemperor,R.drawable.thehierophant,R.drawable.thelovers,R.drawable.thechariot,
                               R.drawable.strength,R.drawable.thehermit,R.drawable.wheel_of_fortune,R.drawable.justice,R.drawable.the_hanged_man,
                               R.drawable.death,R.drawable.temperance,R.drawable.the_devil,R.drawable.the_tower,R.drawable.the_star,
                               R.drawable.the_moon,R.drawable.the_sun,R.drawable.judgement,R.drawable.the_world,R.drawable.thefool,};

                       Random r = new Random();
                       final int n = r.nextInt(5);
                       holder.choose_card.setImageResource(cards[n]);
                       oa2.start();
                       new Handler( ).postDelayed(new Runnable() {
                           @Override
                           public void run() {
                               Intent intent = new Intent(context, TarotDetailActivity.class);
                               intent.putExtra("position", n);
                               context.startActivity(intent);
                           }
                       },1000);
                   }
               });
                oa1.start();
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
