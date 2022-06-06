package com.horoscopes.android.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.horoscopes.android.Activity.HoroscopeDetailActivity;
import com.horoscopes.android.Model.ZodiacModel;
import com.horoscopes.android.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ZodiacSignsAdapter extends RecyclerView.Adapter<ZodiacSignsAdapter.viewHolder> {
    ArrayList<ZodiacModel> zodiacModelList;
    Context context;

    public ZodiacSignsAdapter(ArrayList<ZodiacModel> zodiacModelList, Context context) {
        this.zodiacModelList = zodiacModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.zodiac_signs,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") int position) {
        ZodiacModel item=zodiacModelList.get(position);
        Picasso.get().load(item.getZodiacSign()).into(holder.zodiac_icon);
        holder.zodiac_name.setText(item.getZodiacName());
        holder.itemView.setOnClickListener(view -> {
        Intent intent = new Intent(context, HoroscopeDetailActivity.class);
        intent.putExtra("id",item.getId());
        intent.putExtra("image",item.getZodiacSign());
        context.startActivity(intent);
    });
    }
    @Override
    public int getItemCount() {
        return zodiacModelList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView zodiac_icon;
        TextView zodiac_name;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            zodiac_icon=itemView.findViewById(R.id.zodiac_signs);
            zodiac_name=itemView.findViewById(R.id.zodiac_title);
        }
    }
}
