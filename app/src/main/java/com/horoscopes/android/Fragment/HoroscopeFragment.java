package com.horoscopes.android.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.horoscopes.android.Activity.FindSignActivity;
import com.horoscopes.android.Adapter.ZodiacSignsAdapter;
import com.horoscopes.android.Model.ZodiacModel;
import com.horoscopes.android.R;

import java.util.ArrayList;
import java.util.List;


public class HoroscopeFragment extends Fragment {
    private FragmentActivity mActivity;
    ZodiacSignsAdapter zodiacSignsAdapter;
    View view;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    List<ZodiacModel> list;
    TextView findSign;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentActivity) {
            mActivity = (FragmentActivity) context;
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_horoscope, container, false);
        findSign = view.findViewById(R.id.findSign);
        findSign.setOnClickListener(view -> startActivity(new Intent(mActivity, FindSignActivity.class)));
        list = new ArrayList<>();
        list.add(new ZodiacModel("https://horoscopee.herokuapp.com/aries.png", "Aries", 0, ""));
        list.add(new ZodiacModel("https://horoscopee.herokuapp.com/taurus.png", "Taurus", 1, ""));
        list.add(new ZodiacModel("https://horoscopee.herokuapp.com/gemini.png", "Gemini", 2, ""));
        list.add(new ZodiacModel("https://horoscopee.herokuapp.com/cancer.png", "Cancer", 3, ""));
        list.add(new ZodiacModel("https://horoscopee.herokuapp.com/leo.png", "Leo", 4, ""));
        list.add(new ZodiacModel("https://horoscopee.herokuapp.com/virgo.png", "Virgo", 5, ""));
        list.add(new ZodiacModel("https://horoscopee.herokuapp.com/libra.png", "Libra", 6, ""));
        list.add(new ZodiacModel("https://horoscopee.herokuapp.com/scorpio.png", "Scorpio", 7, ""));
        list.add(new ZodiacModel("https://horoscopee.herokuapp.com/sagittarius.png", "Sagittarius", 8, ""));
        list.add(new ZodiacModel("https://horoscopee.herokuapp.com/capricorn.png", "Capricorn", 9, ""));
        list.add(new ZodiacModel("https://horoscopee.herokuapp.com/aquarius.png", "Aquarius", 10, ""));
        list.add(new ZodiacModel("https://horoscopee.herokuapp.com/pisces.png", "Pisces", 11, ""));
        recyclerView = view.findViewById(R.id.recyclerZodiacSigns);
        zodiacSignsAdapter = new ZodiacSignsAdapter((ArrayList<ZodiacModel>) list, mActivity);
        recyclerView.setAdapter(zodiacSignsAdapter);
        gridLayoutManager = new GridLayoutManager(mActivity, 3, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}