package com.horoscopes.android.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.horoscopes.android.Fragment.HoroscopeFragment;
import com.horoscopes.android.Fragment.TarotCardFragment;

public class FragmentAdapter extends FragmentPagerAdapter {


    public FragmentAdapter(@NonNull FragmentManager fm, int behaviorResumeOnlyCurrentFragment, int tabCount) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new HoroscopeFragment();
            case 1: return new TarotCardFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;
        if (position == 0)
            title = "Horoscope";
        else if (position == 1)
            title = "Tarot Card";
        return title;
    }
}
