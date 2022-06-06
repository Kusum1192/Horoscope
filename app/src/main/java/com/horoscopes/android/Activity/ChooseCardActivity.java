package com.horoscopes.android.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.horoscopes.android.Adapter.ChooseCardAdapter;
import com.horoscopes.android.R;

import java.util.ArrayList;

public class ChooseCardActivity extends AppCompatActivity {

    private Toolbar toolbar_tc;
    private ArrayList<Integer> list;
    ChooseCardAdapter chooseCardAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarot_card);
        toolbar_tc=findViewById(R.id.toolbar_tarot_card);
        toolbar_tc.setTitle("Choose Card");
        setSupportActionBar(toolbar_tc);
        toolbar_tc.setNavigationIcon(R.drawable.ic_back);
        toolbar_tc.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseCardActivity.this.onBackPressed();
            }
        });
        list = new ArrayList<>();
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        list.add(R.drawable.bg_card);
        recyclerView=findViewById(R.id.recyclerCardView);
        chooseCardAdapter=new ChooseCardAdapter(list,this);
        recyclerView.setAdapter(chooseCardAdapter);
        gridLayoutManager = new GridLayoutManager(this,6, RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}