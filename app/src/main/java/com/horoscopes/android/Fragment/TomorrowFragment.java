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

public class TomorrowFragment extends Fragment {

    FragmentActivity mActivity;
    TextView contentId;
    public static TomorrowFragment newInstance(String str) {
        Bundle args = new Bundle();
        args.putString("content",str);
        TomorrowFragment fragment = new TomorrowFragment();
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
        View view= inflater.inflate(R.layout.fragment_tomorrow, container, false);
        contentId=view.findViewById(R.id.content);
        //getHoroscopeDetails();
        Bundle bundle=getArguments();
        String str=bundle.getString("content");
        contentId.setText(str);
        return view;
    }
}