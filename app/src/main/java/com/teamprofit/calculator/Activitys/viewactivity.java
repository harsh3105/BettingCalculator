package com.teamprofit.calculator.Activitys;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.teamprofit.calculator.Adapter.MyAdapter;
import com.teamprofit.calculator.R;
public class viewactivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);

        mContext = this;
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Calculator"));
        tabLayout.addTab(tabLayout.newTab().setText("History"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        setupTabIcons();
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();


        final MyAdapter adapter = new MyAdapter(this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
//                if (tab.getPosition() == 1)
//                {
//                    HistoryTab v = new HistoryTab();
//                    v.refresh(mContext);
//                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.calculator_tab);
        tabLayout.getTabAt(1).setIcon(R.drawable.history_tab);

    }


}
