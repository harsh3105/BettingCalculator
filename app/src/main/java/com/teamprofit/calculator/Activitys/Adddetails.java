package com.teamprofit.calculator.Activitys;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.teamprofit.calculator.Database.Databasehandler;
import com.teamprofit.calculator.Models.logdata;
import com.teamprofit.calculator.R;

public class Adddetails extends AppCompatActivity {

    private EditText first, second, third, forth;
    private TextView done;
    private Context mContext;
    final int maxDigitsBeforeDecimalPoint = 15;
    final int maxDigitsAfterDecimalPoint = 2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);
        mContext = this;
        first = findViewById(R.id.event);
        second = findViewById(R.id.outcome);
        third = findViewById(R.id.bookmarker);
        forth = findViewById(R.id.exchange);
        done = findViewById(R.id.done);
//        second.setFilters(new InputFilter[]{new InputFilter() {
//            @Override
//            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//                StringBuilder builder = new StringBuilder(dest);
//                builder.replace(dstart, dend, source.subSequence(start, end).toString());
//                if (!builder.toString().matches("(([1-9]{1})([0-9]{0," + (maxDigitsBeforeDecimalPoint - 1) + "})?)?(\\.[0-9]{0," + maxDigitsAfterDecimalPoint + "})?")) {
//                    if (source.length() == 0) return dest.subSequence(dstart, dend);
//                    return "";
//                }
//                return null;
//            }
//        }});

        Bundle bundle = getIntent().getExtras();
        final String profite = bundle.getString("pro");

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(first.getText().toString())) {
                    alertDialog(mContext, getResources().getString(R.string.event_empty));
                    return;
                } else if (TextUtils.isEmpty(second.getText().toString())) {
                    alertDialog(mContext, getResources().getString(R.string.outcome_empty));
                    return;
                } else if (TextUtils.isEmpty(third.getText().toString())) {
                    alertDialog(mContext, getResources().getString(R.string.bookmark_empty));
                    return;
                } else if (TextUtils.isEmpty(forth.getText().toString())) {
                    alertDialog(mContext, getResources().getString(R.string.exchange_empty));
                    return;
                } else {
                    Long tsLong = System.currentTimeMillis() / 1000;
                    String ts = tsLong.toString();
                    Databasehandler databasehandler = new Databasehandler(mContext);
                    logdata logdata = new logdata();
                    logdata.setBook(third.getText().toString());
                    logdata.setExchange(forth.getText().toString());
                    logdata.setOut(second.getText().toString());
                    logdata.setEvent(first.getText().toString());
                    logdata.setProfit(profite);
                    logdata.setTimestamp(ts);
                    logdata.setType("");
                    databasehandler.adddata(logdata);
                    finish();
                }
            }
        });
    }

    private void alertDialog(Context mContext, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(R.string.error);
        builder.setMessage(message);
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}