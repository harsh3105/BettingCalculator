package com.teamprofit.calculator.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.teamprofit.calculator.Activitys.Settings;
import com.teamprofit.calculator.Adapter.AdapterMenu;
import com.teamprofit.calculator.Models.DataMain;
import com.teamprofit.calculator.R;


public class CalculatorTab extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private DataMain main;
    private DataMain main1,main2;
    private View view;
    private Context mContext;
    private TextView settings;

    public static CalculatorTab newinstance(){
        CalculatorTab fragment= new CalculatorTab();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = inflater.inflate(R.layout.view1, container, false);
        recyclerView = view.findViewById(R.id.recycle);

        settings = view.findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Settings.class);
                startActivity(intent);
            }
        });


        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<DataMain> input = new ArrayList<>();
        mContext = getActivity();
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
        mAdapter = new AdapterMenu(mContext,input);
        recyclerView.setAdapter(mAdapter);
        return view;
    }
}
