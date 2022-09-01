package com.horoscopes.android.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.horoscopes.android.Fragment.TodayFragment;
import com.horoscopes.android.Fragment.TomorrowFragment;
import com.horoscopes.android.Fragment.YesterdayFragment;

import java.util.ArrayList;

public class VPDaysAdapter extends FragmentPagerAdapter {
    private final int Item;
    ArrayList<Fragment>fragments=new ArrayList<>();

    public VPDaysAdapter(@NonNull FragmentManager fm, int behavior, int item) {
        super(fm, behavior);
        Item = item;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
       /* switch (position){
            case 0: return new YesterdayFragment();
            case 1: return new TodayFragment();
            case 2: return new TomorrowFragment();
            default: return null;
        }*/
    }
    @Override
    public int getCount() {
        return Item;
    }

    public void addFrag(Fragment fragment){
        fragments.add(fragment);
    }
}
