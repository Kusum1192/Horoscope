package com.horoscopes.android.Fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.horoscopes.android.Activity.HoroscopeDetailActivity;
import com.horoscopes.android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class TomorrowFragment extends Fragment {


    TextView content_tmr;
    Fragment mActivity;
    private String content;

    // TODO: Rename and change types and number of parameters
    public static TomorrowFragment newInstance(String param1, String param2) {
        TomorrowFragment fragment = new TomorrowFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tomorrow, container, false);
        content_tmr=view.findViewById(R.id.content);
        getJsonFileFromLocally();
        return view;
    }

    private void getJsonFileFromLocally () {
        //int id = mActivity.getIntent().getIntExtra("position", 0);
        try {
            JSONObject jsonObject = new JSONObject(HoroscopeDetailActivity.loadJSONFromAssets(getContext()));
            JSONArray zodiacList = jsonObject.getJSONArray("zodiacList");
            String zodiacId = zodiacList.getJSONObject(3).getString("zodiacId");
            content = zodiacList.getJSONObject(3).getString("content");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        content_tmr.setText(content);
    }

}