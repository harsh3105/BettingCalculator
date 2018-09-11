package com.teamprofit.calculator.Activitys;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.teamprofit.calculator.Database.Databasehandler;
import com.teamprofit.calculator.R;

public class Settings extends AppCompatActivity {

    LinearLayout clear;
    Context mContext;
    private TextView setting_done_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mContext = this;
        clear = findViewById(R.id.clear);
        setting_done_textview = findViewById(R.id.setting_done_textview);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog(mContext);
            }
        });


        setting_done_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void alertDialog(final Context mContext) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(getResources().getString(R.string.alert));
        builder.setMessage(getResources().getString(R.string.clear_data));
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Databasehandler databasehandler = new Databasehandler(mContext);
                databasehandler.cleardata();
                clearAlert();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void clearAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(getResources().getString(R.string.successful));
        builder.setMessage(getResources().getString(R.string.clear_success_message));
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }
}
