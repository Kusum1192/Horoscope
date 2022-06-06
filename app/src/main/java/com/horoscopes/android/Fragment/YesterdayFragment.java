package com.horoscopes.android.Fragment;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.icu.text.Transliterator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.horoscopes.android.Activity.HoroscopeDetailActivity;
import com.horoscopes.android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class YesterdayFragment extends Fragment {

    FragmentActivity mActivity;
    String content;
    TextView contentId;

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
        View view= inflater.inflate(R.layout.fragment_yesterday, container, false);
        contentId=view.findViewById(R.id.yesterday_description);
        getJsonFileFromLocally();
        return view;
    }

    private void getJsonFileFromLocally() {
        int id=mActivity.getIntent().getIntExtra("position",0);
        try {
            JSONObject jsonObject = new JSONObject(HoroscopeDetailActivity.loadJSONFromAssets(getContext()));
            JSONArray zodiacList = jsonObject.getJSONArray("zodiacList");
            String zodiacId = zodiacList.getJSONObject(id).getString("zodiacId");
            content = zodiacList.getJSONObject(id).getString("content");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        contentId.setText(content);
    }
}