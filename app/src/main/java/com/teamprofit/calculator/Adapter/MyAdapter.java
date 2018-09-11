package com.teamprofit.calculator.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.teamprofit.calculator.Fragments.CalculatorTab;
import com.teamprofit.calculator.Fragments.HistoryTab;


public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                CalculatorTab tab1 = new CalculatorTab();
                return tab1;

            case 1:
                HistoryTab tab2 = new HistoryTab();
                return tab2;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }

}
