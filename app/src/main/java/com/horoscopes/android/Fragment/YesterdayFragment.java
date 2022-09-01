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

public class YesterdayFragment extends Fragment {

    FragmentActivity mActivity;
    TextView contentId;

    public static YesterdayFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString("description",str);
        YesterdayFragment fragment = new YesterdayFragment();
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
        View view= inflater.inflate(R.layout.fragment_yesterday, container, false);
        contentId=view.findViewById(R.id.yesterday_description);
        //getHoroscopeDetails();
        Bundle bundle=getArguments();
        String str=bundle.getString("description");
        contentId.setText(str);
        return view;
    }

}