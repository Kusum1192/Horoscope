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

import com.horoscopes.android.ApiClient;
import com.horoscopes.android.Model.ZodiacDetailsData;
import com.horoscopes.android.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TomorrowFragment extends Fragment {
    TextView content_tmr;
    FragmentActivity mActivity;
    private String content;

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
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_tomorrow, container, false);
        content_tmr=view.findViewById(R.id.content);
       // getHoroscopeDetails();
       // getJsonFileFromLocally();
        return view;
    }

    /*private void getJsonFileFromLocally () {
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
    }*/
   /* private void getHoroscopeDetails() {
        Call<ZodiacDetailsData> call = ApiClient.getMyInterface().getSign("Leo", "Tomorrow");
        call.enqueue(new Callback<ZodiacDetailsData>() {
            @Override
            public void onResponse(@NonNull Call<ZodiacDetailsData> call, @NonNull Response<ZodiacDetailsData> response) {
                ZodiacDetailsData model=response.body();

                if (model != null) {
                    String description=model.getDescription();
                    content_tmr.setText(description);
                }
            }
            @Override
            public void onFailure(@NonNull Call<ZodiacDetailsData> call, @NonNull Throwable t) {

            }
        });
    }*/
}