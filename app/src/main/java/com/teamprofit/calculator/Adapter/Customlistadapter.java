package com.teamprofit.calculator.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.teamprofit.calculator.Models.logdata;
import com.teamprofit.calculator.R;

public class Customlistadapter extends BaseAdapter implements View.OnClickListener {
    private Context context; //context
    private ArrayList<logdata> items; //data source of the list adapter
    public onItemClick onItemClick;

    public Customlistadapter(Context context, ArrayList<logdata> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.historyview, parent, false);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.first = convertView.findViewById(R.id.first);
        viewHolder.second = convertView.findViewById(R.id.second);
        viewHolder.third = convertView.findViewById(R.id.third);
        viewHolder.forth = convertView.findViewById(R.id.fourth);
        viewHolder.money = convertView.findViewById(R.id.money);
        viewHolder.image = convertView.findViewById(R.id.imageView1);
        viewHolder.delete = convertView.findViewById(R.id.delete_layout);
        viewHolder.first.setText(items.get(position).getEvent());
        viewHolder.second.setText(items.get(position).getBook());
        viewHolder.third.setText(items.get(position).getExchange() + " / " + items.get(position).getOut());
        viewHolder.money.setText(items.get(position).getProfit());
        String doubledata = items.get(position).getProfit().replace("£", "");
        if (Float.parseFloat(doubledata) < 0) {
            viewHolder.money.setTextColor(context.getResources().getColor(R.color.red));
        }
        if (Float.parseFloat(doubledata) == 0) {
            viewHolder.money.setTextColor(context.getResources().getColor(R.color.black));
        }
        if (Float.parseFloat(doubledata) > 0) {
            viewHolder.money.setTextColor(context.getResources().getColor(R.color.green));
        }
        viewHolder.image.setImageResource(R.drawable.padlock);

        long timestamp = Long.parseLong(items.get(position).getTimestamp()) * 1000L;
        viewHolder.forth.setText(getDate(timestamp));


        final View finalConvertView = convertView;

        viewHolder.delete.setOnClickListener(this);
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(finalConvertView, position);

                notifyDataSetChanged();
            }
        });
        return convertView;

    }

    public interface onItemClick {
        void onItemClick(View view, int postion);
    }

    public void setOnItemClickListner(onItemClick onItemClickListner) {
        this.onItemClick = onItemClickListner;
    }

    @Override
    public int getViewTypeCount() {
        // menu type count
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        // current menu type
        return position;
    }


    @Override
    public void onClick(View v) {

    }

    public class ViewHolder {
        public TextView first;
        public TextView second;
        public TextView third;
        public TextView forth;
        public TextView money;
        public ImageView image;
        public FrameLayout delete;
    }

    private String getDate(long timeStamp) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy  hh:mm");
            Date netDate = (new Date(timeStamp));
            return sdf.format(netDate);
        } catch (Exception ex) {
            return "xx";
        }
    }
}
