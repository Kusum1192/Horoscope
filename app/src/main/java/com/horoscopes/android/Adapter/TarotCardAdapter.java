package com.horoscopes.android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.horoscopes.android.Activity.ChooseCardActivity;
import com.horoscopes.android.Model.TarotCardModel;
import com.horoscopes.android.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TarotCardAdapter extends RecyclerView.Adapter<TarotCardAdapter.viewHolder> {
    ArrayList<TarotCardModel> tarotCardModels;
    Context context;

    public TarotCardAdapter(ArrayList<TarotCardModel> tarotCardModels, Context context) {
        this.tarotCardModels = tarotCardModels;
        this.context = context;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarot_cards,parent,false);
        return new viewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        TarotCardModel list=tarotCardModels.get(position);
        Picasso.get().load(list.getTarotCardIcon()).into(holder.TC_icon);
        holder.TC_name.setText(list.getTarotCardName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ChooseCardActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return tarotCardModels.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView TC_icon;
        TextView TC_name;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            TC_icon=itemView.findViewById(R.id.tarot_ic);
            TC_name=itemView.findViewById(R.id.tc_title);
        }
    }
}
