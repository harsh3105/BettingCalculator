package com.teamprofit.calculator.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;


import com.teamprofit.calculator.Adapter.AdapterMenu;
import com.teamprofit.calculator.Models.DataMain;
import com.teamprofit.calculator.R;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DataMain main;
    private DataMain main1,main2;
    private String PACKAGE_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycle);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<DataMain> input = new ArrayList<>();


        main =new DataMain();
        main.setFirst("Normal");
        main.setSecond("Trigger Bets");
        main.setImage(R.drawable.padlock);
        main1 = new DataMain();
        main1.setFirst("Free Bets (SNR)");
        main1.setImage(R.drawable.snr);
        main2 = new DataMain();
        main2.setFirst("Visit TeamProfit.com");
        main2.setImage(R.drawable.logotp);
        input.add(main);
        input.add(main1);
        input.add(main2);
        mAdapter = new AdapterMenu(this,input);
        recyclerView.setAdapter(mAdapter);
        PACKAGE_NAME = getApplicationContext().getPackageName();
        Toast.makeText(this, PACKAGE_NAME, Toast.LENGTH_SHORT).show();
    }
}
