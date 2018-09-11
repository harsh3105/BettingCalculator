package com.teamprofit.calculator.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.teamprofit.calculator.Models.logdata;
import com.teamprofit.calculator.R;

public class Historyadapter extends RecyclerView.Adapter<Historyadapter.ViewHolder> {

    private ArrayList<logdata> values;
    private Context context;
    public onItemClick onItemClick;

    public Historyadapter(Context context, ArrayList<logdata> items) {
        this.context = context;
        this.values = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.historyview, parent, false);
        return new Historyadapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.setIsRecyclable(false);
        holder.first.setText(values.get(position).getEvent());
        holder.second.setText(values.get(position).getBook());
        holder.third.setText(values.get(position).getExchange() + " / " + values.get(position).getOut());
        holder.money.setText(values.get(position).getProfit());
        String doubledata = values.get(position).getProfit().replace("£", "");
        if (Float.parseFloat(doubledata) < 0) {
            holder.money.setTextColor(context.getResources().getColor(R.color.red));
        }
        if (Float.parseFloat(doubledata) == 0) {
            holder.money.setTextColor(context.getResources().getColor(R.color.black));
        }
        if (Float.parseFloat(doubledata) > 0) {
            holder.money.setTextColor(context.getResources().getColor(R.color.green));
        }
        holder.image.setImageResource(R.drawable.padlock);

        long timestamp = Long.parseLong(values.get(position).getTimestamp()) * 1000L;
        holder.fourth.setText(getDate(timestamp));


    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public me.grantland.widget.AutofitTextView first;
        public me.grantland.widget.AutofitTextView second;
        public me.grantland.widget.AutofitTextView third;
        public me.grantland.widget.AutofitTextView fourth;
        public ImageView image;
        public me.grantland.widget.AutofitTextView money;
        public View shortv;
        private FrameLayout delete;

        public ViewHolder(View v) {
            super(v);
            first = v.findViewById(R.id.first);
            second = v.findViewById(R.id.second);
            third = v.findViewById(R.id.third);
            fourth = v.findViewById(R.id.fourth);
            image = v.findViewById(R.id.imageView1);
            shortv = v.findViewById(R.id.shortView);
            delete = v.findViewById(R.id.delete_layout);
            money = v.findViewById(R.id.money);
            delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClick.onItemClick(v, getAdapterPosition());
        }
    }

    public interface onItemClick {
        void onItemClick(View view, int postion);
    }

    public void setOnItemClickListner(onItemClick onItemClickListner) {
        this.onItemClick = onItemClickListner;
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
