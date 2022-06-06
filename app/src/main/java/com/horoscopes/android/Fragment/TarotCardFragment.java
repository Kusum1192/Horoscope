package com.horoscopes.android.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.horoscopes.android.Adapter.TarotCardAdapter;
import com.horoscopes.android.Model.TarotCardModel;
import com.horoscopes.android.R;

import java.util.ArrayList;

public class TarotCardFragment extends Fragment {

    private View view;
    ArrayList<TarotCardModel> list;
    private RecyclerView recyclerView;
    private TarotCardAdapter tarotCardAdapter;
    private FragmentActivity mActivity;
    private GridLayoutManager gridLayoutManager;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentActivity) {
            mActivity= (FragmentActivity) context; }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view =inflater.inflate(R.layout.fragment_tarotcard, container, false);
        list = new ArrayList<>();
        list.add(new TarotCardModel(R.drawable.lv_n_rltn, "Love and Relations"));
        list.add(new TarotCardModel(R.drawable.money, "Money"));
        list.add(new TarotCardModel(R.drawable.life, "Life"));
        list.add(new TarotCardModel(R.drawable.fmly_n_frds, "Family and Friends"));
        list.add(new TarotCardModel(R.drawable.health, "Health"));
        recyclerView=view.findViewById(R.id.recyclerTarotCard);
        tarotCardAdapter =new TarotCardAdapter(list,mActivity);
        recyclerView.setAdapter(tarotCardAdapter);
        gridLayoutManager = new GridLayoutManager(mActivity,2, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        return view;
    }
}