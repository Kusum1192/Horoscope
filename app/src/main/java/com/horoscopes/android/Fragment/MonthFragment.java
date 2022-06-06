package com.horoscopes.android.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.horoscopes.android.Activity.HoroscopeDetailActivity;
import com.horoscopes.android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MonthFragment extends Fragment {

    private FragmentActivity mActivity;
    private String content;
    TextView month_content;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentActivity)
            mActivity=(FragmentActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_month, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        month_content=mActivity.findViewById(R.id.month_description);
        getJsonFileFromLocally();
    }

    private void getJsonFileFromLocally() {
        int id=mActivity.getIntent().getIntExtra("position",0);
        try {
            JSONObject jsonObject = new JSONObject(HoroscopeDetailActivity.loadJSONFromAssets(getContext()));
            JSONArray zodiacList = jsonObject.getJSONArray("zodiacList");
            String zodiacId = zodiacList.getJSONObject(6).getString("zodiacId");
            content = zodiacList.getJSONObject(6).getString("content");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        month_content.setText(content);
    }
}