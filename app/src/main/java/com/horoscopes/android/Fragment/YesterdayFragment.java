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

import com.horoscopes.android.Activity.HoroscopeDetailActivity;
import com.horoscopes.android.ApiClient;
import com.horoscopes.android.Model.ZodiacDetailsData;
import com.horoscopes.android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        //getJsonFileFromLocally();
        //getHoroscopeDetails();
        return view;
    }
    /*private void getJsonFileFromLocally() {
        int id=mActivity.getIntent().getIntExtra("position",0);
        try {
            JSONObject jsonObject = new JSONObject(HoroscopeDetailActivity.loadJSONFromAssets(getContext()));
            JSONArray zodiacList = jsonObject.getJSONArray("zodiacList");
            //String zodiacId = zodiacList.getJSONObject(id).getString("zodiacId");
            content = zodiacList.getJSONObject(id).getString("content");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        contentId.setText(content);

    }*/
   /* public void getHoroscopeDetails() {
        Call<ZodiacDetailsData> call = ApiClient.getMyInterface().getSign("Leo", "Yesterday");
        call.enqueue(new Callback<ZodiacDetailsData>() {
            @Override
            public void onResponse(@NonNull Call<ZodiacDetailsData> call, @NonNull Response<ZodiacDetailsData> response) {
                ZodiacDetailsData model=response.body();

                if (model != null) {
                    String description= model.getDescription();
                    contentId.setText(description);
                }
            }
            @Override
            public void onFailure(@NonNull Call<ZodiacDetailsData> call, @NonNull Throwable t) {

            }
        });
    }*/
}