package com.horoscopes.android.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.horoscopes.android.R;

public class TodayFragment extends Fragment {

    FragmentActivity mActivity;
    TextView contentId;

    public static TodayFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("describe",str);

        TodayFragment fragment = new TodayFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentActivity) {
            mActivity= (FragmentActivity) context;
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_today, container, false);
        contentId=view.findViewById(R.id.today_description);
        Bundle bundle=getArguments();
        String strToday=bundle.getString("describe");
        contentId.setText(strToday);
        return view;
    }
}