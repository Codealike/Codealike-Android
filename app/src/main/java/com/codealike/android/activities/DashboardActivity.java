package com.codealike.android.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.codealike.android.R;
import com.codealike.android.fragments.CodeFactsFragment;
import com.codealike.android.fragments.DaysOnFireFragment;
import com.codealike.android.fragments.UserFactsFragment;

public class DashboardActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            switch(pos) {
                case 0: return UserFactsFragment.newInstance("UserFactsFragment");
                case 1: return CodeFactsFragment.newInstance("CodeFactsFragment");
                case 2: return DaysOnFireFragment.newInstance("DaysOnFireFragment");
                default: return DaysOnFireFragment.newInstance("DaysOnFireFragment, Default");
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}