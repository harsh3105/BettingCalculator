package com.teamprofit.calculator.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import com.teamprofit.calculator.Adapter.Historyadapter;
import com.teamprofit.calculator.Database.Databasehandler;
import com.teamprofit.calculator.Models.historydata;
import com.teamprofit.calculator.Models.logdata;
import com.teamprofit.calculator.R;


public class HistoryTab extends Fragment {
    private View view;
    private RecyclerView.LayoutManager layoutManager;
    private Context mContext;
    private historydata main;
    ArrayList<logdata> itemsArrayList = new ArrayList<>();
    //    private Customlistadapter adapter;
    private ListView itemsListView;
    private RecyclerView recyclewView;
    private Historyadapter adapter;

    public static HistoryTab newinstance() {
        HistoryTab fragment = new HistoryTab();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.view2, container, false);

        mContext = getActivity();
        itemsListView = view.findViewById(R.id.listView);
        recyclewView = view.findViewById(R.id.recyclewView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclewView.setLayoutManager(linearLayoutManager);
//        adapter = new Customlistadapter(mContext, itemsArrayList);
//        itemsListView.setAdapter(adapter);
//        adapter.setOnItemClickListner(onItemClick);
        adapter = new Historyadapter(mContext, itemsArrayList);
        recyclewView.setAdapter(adapter);
        adapter.setOnItemClickListner(onItemClick);
        return view;
    }

    Historyadapter.onItemClick onItemClick = new Historyadapter.onItemClick() {
        @Override
        public void onItemClick(View view, int postion) {
            Databasehandler databasehandler = new Databasehandler(mContext);
            databasehandler.deleteContact(itemsArrayList.get(postion));
            refresh(mContext);
        }
    };

    public void refresh(Context mContext) {
        if (itemsArrayList != null) {
            itemsArrayList.clear();
            Databasehandler databasehandler = new Databasehandler(mContext);
            ArrayList<logdata> logdataArrayList = databasehandler.getdata();
            for (int i = 0; i < logdataArrayList.size(); i++) {
                itemsArrayList.add(logdataArrayList.get(i));
            }
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            refresh(mContext);
        }
    }


}