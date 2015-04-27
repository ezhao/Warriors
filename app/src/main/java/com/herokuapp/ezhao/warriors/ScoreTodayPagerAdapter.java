package com.herokuapp.ezhao.warriors;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.Date;

public class ScoreTodayPagerAdapter extends FragmentStatePagerAdapter {
    public static int NUM_ITEMS_BACK = 30;
    public static int NUM_ITEMS_FORWARD = 30;

    public ScoreTodayPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        int daysBefore = NUM_ITEMS_BACK - position;
        Date date = new Date((new Date()).getTime() - daysBefore*1000*60*60*24);
        return ScoreTodayFragment.newInstance(date);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS_FORWARD + NUM_ITEMS_BACK;
    }
}
