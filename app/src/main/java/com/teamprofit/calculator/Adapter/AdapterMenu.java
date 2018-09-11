package com.teamprofit.calculator.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import com.teamprofit.calculator.Activitys.FreeBet;
import com.teamprofit.calculator.Activitys.Normal;

import com.teamprofit.calculator.Models.DataMain;
import com.teamprofit.calculator.R;

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.ViewHolder> {

    private ArrayList<DataMain> values;
    private Context context;
    private TextView name;

    public AdapterMenu(Context context, ArrayList<DataMain> myDataset){
       this.values = myDataset;
       this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View  view= LayoutInflater.from(context).inflate(R.layout.main,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.photo.setImageResource(values.get(position).getImage());
        holder.txtHeader.setText(values.get(position).getFirst());
        holder.mainview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (values.get(position).getFirst() == "Normal")
                {
//                    Intent intent = new Intent(context, Testing.class);
                    Intent intent = new Intent(context, Normal.class);
                    context.startActivity(intent);
                }
                else if (values.get(position).getFirst() == "Free Bets (SNR)")
                {
                    Intent intent = new Intent(context, FreeBet.class);
                    context.startActivity(intent);
                }
                else if (values.get(position).getFirst() == "Visit TeamProfit.com")
                {
                    Uri uri = Uri.parse("http://www.teamprofit.com"); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    context.startActivity(intent);
                }


            }
        });

        if(!TextUtils.isEmpty(values.get(position).getSecond()))
        {
            holder.txtFooter.setVisibility(View.VISIBLE);
            holder.txtFooter.setText(values.get(position).getSecond());
        }
        else {
            holder.txtFooter.setVisibility(View.GONE);
        }


        if (values.size() == position + 1)
        {
            holder.shortv.setVisibility(View.GONE);
        }
        else{
            holder.shortv.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView photo;
        public View shortv;
        public LinearLayout mainview;


        public ViewHolder(View v){
            super(v);
            txtHeader = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
            photo= v.findViewById(R.id.imageView);
            shortv= v.findViewById(R.id.shortView);
            mainview= v.findViewById(R.id.mainview);


        }
    }





}
