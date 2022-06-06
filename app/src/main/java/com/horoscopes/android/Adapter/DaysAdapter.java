package com.horoscopes.android.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.horoscopes.android.Fragment.MonthFragment;
import com.horoscopes.android.Fragment.TodayFragment;
import com.horoscopes.android.Fragment.TomorrowFragment;
import com.horoscopes.android.Fragment.WeekFragment;
import com.horoscopes.android.Fragment.YesterdayFragment;

public class DaysAdapter extends FragmentPagerAdapter {
    private final int Item;

    public DaysAdapter(@NonNull FragmentManager fm, int behavior, int item) {
        super(fm, behavior);
        Item = item;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new YesterdayFragment();
            case 1: return new TodayFragment();
            case 2: return new TomorrowFragment();
            case 3: return new WeekFragment();
            case 4: return new MonthFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return Item;
    }
}
