package com.teamprofit.calculator.Activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.teamprofit.calculator.R;

import java.text.DecimalFormat;


import co.ceryle.segmentedbutton.SegmentedButton;
import co.ceryle.segmentedbutton.SegmentedButtonGroup;

public class FreeBet extends AppCompatActivity {

    private TextView text, bb_stake, bb_odds, lb_odds;
    Context mContext;
    private ImageView plusClick;

    private LinearLayout underlay1, underlay2, underlay3;
    private ImageView underlay4;
    private LinearLayout standard1, standard2, standard3;
    private ImageView standard4;
    private LinearLayout overlay1, overlay2, overlay3;
    private ImageView overlay4;
    private SegmentedButton button1, button2, button3;

    String bb_stake_value, bb_odds_value, lb_odds_value;

    double button = 0.05;

    double bb_comm = 0.00;
    me.grantland.widget.AutofitTextView u_laystack, u_liability, u_laywin, u_backwin,s_laystack, s_liability, standardvalue, s_retension,o_laystack, o_liability, o_backwin, o_laywin;
    private int maxDigitsBeforeDecimalPoint = 15;
    private int maxDigitsAfterDecimalPoint = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_bet);

        mContext = this;

        bb_stake = findViewById(R.id.bb_stake);
        bb_odds = findViewById(R.id.bb_odds);
        lb_odds = findViewById(R.id.lb_odds);
        plusClick = findViewById(R.id.plus);

        button1 = findViewById(R.id.comm_0);
        button2 = findViewById(R.id.comm_2);
        button3 = findViewById(R.id.comm_5);

        u_laystack = findViewById(R.id.u_laystack);
        u_liability = findViewById(R.id.u_liability);
        u_backwin = findViewById(R.id.u_backwin);
        u_laywin = findViewById(R.id.u_laywins);

        s_laystack = findViewById(R.id.s_laystack);
        s_liability = findViewById(R.id.s_liability);
        standardvalue = findViewById(R.id.standardvalue);
        s_retension = findViewById(R.id.s_retension);

        final View view = this.getCurrentFocus();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }, 1000);



        bb_stake.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                StringBuilder builder = new StringBuilder(dest);
                builder.replace(dstart, dend, source.subSequence(start, end).toString());
                if (!builder.toString().matches("(([1-9]{1})([0-9]{0," + (maxDigitsBeforeDecimalPoint - 1) + "})?)?(\\.[0-9]{0," + maxDigitsAfterDecimalPoint + "})?")) {
                    if (source.length() == 0) return dest.subSequence(dstart, dend);
                    return "";
                }
                return null;
            }
        }});
        bb_odds.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                StringBuilder builder = new StringBuilder(dest);
                builder.replace(dstart, dend, source.subSequence(start, end).toString());
                if (!builder.toString().matches("(([1-9]{1})([0-9]{0," + (maxDigitsBeforeDecimalPoint - 1) + "})?)?(\\.[0-9]{0," + maxDigitsAfterDecimalPoint + "})?")) {
                    if (source.length() == 0) return dest.subSequence(dstart, dend);
                    return "";
                }
                return null;
            }
        }});
        lb_odds.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                StringBuilder builder = new StringBuilder(dest);
                builder.replace(dstart, dend, source.subSequence(start, end).toString());
                if (!builder.toString().matches("(([1-9]{1})([0-9]{0," + (maxDigitsBeforeDecimalPoint - 1) + "})?)?(\\.[0-9]{0," + maxDigitsAfterDecimalPoint + "})?")) {
                    if (source.length() == 0) return dest.subSequence(dstart, dend);
                    return "";
                }
                return null;
            }
        }});


        o_laystack = findViewById(R.id.o_laystake);
        o_liability = findViewById(R.id.o_liability);
        o_backwin = findViewById(R.id.o_backwins);
        o_laywin = findViewById(R.id.o_laywins);
        final SegmentedButtonGroup segmentedButtonGroup = findViewById(R.id.segmentedButtonGroup);

        bb_stake.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    bb_stake_value = bb_stake.getText().toString();
                    bb_odds_value = bb_odds.getText().toString();
                    lb_odds_value = lb_odds.getText().toString();
                    if (!TextUtils.isEmpty(bb_stake_value) && !TextUtils.isEmpty(bb_odds_value) && !TextUtils.isEmpty(lb_odds_value)) {
                        if (segmentedButtonGroup.getPosition() == 0) {
                            button = 0.00;
                            underlayCalculate();
                            overlayCalculate();
                            standardCalclulate();

                        }
                        if (segmentedButtonGroup.getPosition() == 1) {
                            button = 0.02;
                            underlayCalculate();
                            overlayCalculate();
                            standardCalclulate();

                        }
                        if (segmentedButtonGroup.getPosition() == 2) {
                            button = 0.05;
                            underlayCalculate();
                            overlayCalculate();
                            standardCalclulate();

                        }
                    }
                }
            }
        });

        bb_odds.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    bb_stake_value = bb_stake.getText().toString();
                    bb_odds_value = bb_odds.getText().toString();
                    lb_odds_value = lb_odds.getText().toString();
                    if (!TextUtils.isEmpty(bb_stake_value) && !TextUtils.isEmpty(bb_odds_value) && !TextUtils.isEmpty(lb_odds_value)) {
                        if (segmentedButtonGroup.getPosition() == 0) {
                            button = 0.00;
                            underlayCalculate();
                            overlayCalculate();
                            standardCalclulate();

                        }
                        if (segmentedButtonGroup.getPosition() == 1) {
                            button = 0.02;
                            underlayCalculate();
                            overlayCalculate();
                            standardCalclulate();

                        }
                        if (segmentedButtonGroup.getPosition() == 2) {
                            button = 0.05;
                            underlayCalculate();
                            overlayCalculate();
                            standardCalclulate();

                        }
                    }
                }
            }
        });

        lb_odds.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    bb_stake_value = bb_stake.getText().toString();
                    bb_odds_value = bb_odds.getText().toString();
                    lb_odds_value = lb_odds.getText().toString();
                    if (!TextUtils.isEmpty(bb_stake_value) && !TextUtils.isEmpty(bb_odds_value) && !TextUtils.isEmpty(lb_odds_value)) {
                        if (segmentedButtonGroup.getPosition() == 0) {
                            button = 0.00;
                            underlayCalculate();
                            overlayCalculate();
                            standardCalclulate();

                        }
                        if (segmentedButtonGroup.getPosition() == 1) {
                            button = 0.02;
                            underlayCalculate();
                            overlayCalculate();
                            standardCalclulate();

                        }
                        if (segmentedButtonGroup.getPosition() == 2) {
                            button = 0.05;
                            underlayCalculate();
                            overlayCalculate();
                            standardCalclulate();

                        }
                    }
                }
            }
        });


        underlay1 = findViewById(R.id.underlay);
        underlay2 = findViewById(R.id.underlayDetails1);
        underlay3 = findViewById(R.id.underlayDetails2);
        underlay4 = findViewById(R.id.imageUnder);

        underlay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                standard2.setVisibility(View.GONE);
                standard3.setVisibility(View.GONE);
                standard4.setVisibility(View.VISIBLE);
                overlay2.setVisibility(View.GONE);
                overlay3.setVisibility(View.GONE);
                overlay4.setVisibility(View.VISIBLE);

                if (underlay2.getVisibility() == View.GONE) {
                    underlay2.setVisibility(View.VISIBLE);
                    underlay3.setVisibility(View.VISIBLE);
                    underlay4.setVisibility(View.GONE);
                    if (TextUtils.isEmpty(bb_stake_value)) {
                        Toast.makeText(mContext, "Stake Value is empty", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(bb_odds_value)) {
                        Toast.makeText(mContext, "Stake odds is empty", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(lb_odds_value)) {
                        Toast.makeText(mContext, "Lay bet odds is empty", Toast.LENGTH_SHORT).show();
                    } else {
                        underlayCalculate();
                    }

                } else {
                    underlay2.setVisibility(View.GONE);
                    underlay3.setVisibility(View.GONE);
                    underlay4.setVisibility(View.VISIBLE);
                }
            }
        });


        segmentedButtonGroup.setOnClickedButtonListener(new SegmentedButtonGroup.OnClickedButtonListener() {
            @Override
            public void onClickedButton(int position) {
                bb_stake_value = bb_stake.getText().toString();
                bb_odds_value = bb_odds.getText().toString();
                lb_odds_value = lb_odds.getText().toString();

                if (!TextUtils.isEmpty(bb_stake_value) && !TextUtils.isEmpty(bb_odds_value) && !TextUtils.isEmpty(lb_odds_value)) {
                    if (position == 0) {
                        button = 0.00;
                        underlayCalculate();
                        overlayCalculate();
                        standardCalclulate();
                    }
                    if (position == 1) {
                        button = 0.02;
                        underlayCalculate();
                        overlayCalculate();
                        standardCalclulate();
                    }
                    if (position == 2) {
                        button = 0.05;
                        underlayCalculate();
                        overlayCalculate();
                        standardCalclulate();
                    }
                } else {
                    Toast.makeText(mContext, "Please fill filed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        segmentedButtonGroup.setPosition(2, 0);

        standard1 = findViewById(R.id.standard);
        standard2 = findViewById(R.id.standardDetails1);
        standard3 = findViewById(R.id.standardDetails2);
        standard4 = findViewById(R.id.imageStandard);

        standard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                overlay2.setVisibility(View.GONE);
                overlay3.setVisibility(View.GONE);
                overlay4.setVisibility(View.VISIBLE);
                underlay2.setVisibility(View.GONE);
                underlay3.setVisibility(View.GONE);
                underlay4.setVisibility(View.VISIBLE);

                if (standard2.getVisibility() == View.GONE) {
                    standard2.setVisibility(View.VISIBLE);
                    standard3.setVisibility(View.VISIBLE);
                    standard4.setVisibility(View.GONE);
                    standardCalclulate();
                } else {
                    standard2.setVisibility(View.GONE);
                    standard3.setVisibility(View.GONE);
                    standard4.setVisibility(View.VISIBLE);

                }

            }
        });

        overlay1 = findViewById(R.id.overlay);
        overlay2 = findViewById(R.id.overlayDetails1);
        overlay3 = findViewById(R.id.overlayDetails2);
        overlay4 = findViewById(R.id.imageOverlay);

        overlay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                standard2.setVisibility(View.GONE);
                standard3.setVisibility(View.GONE);
                standard4.setVisibility(View.VISIBLE);
                underlay2.setVisibility(View.GONE);
                underlay3.setVisibility(View.GONE);
                underlay4.setVisibility(View.VISIBLE);

//                Databasehandler Databasehandler =new Databasehandler(mContext);
//                logdata logdata = Databasehandler.getdata();

                if (overlay2.getVisibility() == View.GONE) {
                    overlay2.setVisibility(View.VISIBLE);
                    overlay3.setVisibility(View.VISIBLE);
                    overlay4.setVisibility(View.GONE);
                    if (TextUtils.isEmpty(bb_stake_value)) {
                        Toast.makeText(mContext, "Stake Value is empty", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(bb_odds_value)) {
                        Toast.makeText(mContext, "Stake odds is empty", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(lb_odds_value)) {
                        Toast.makeText(mContext, "Lay bet odds is empty", Toast.LENGTH_SHORT).show();
                    } else {

                        overlayCalculate();
                    }
                } else {
                    overlay2.setVisibility(View.GONE);
                    overlay3.setVisibility(View.GONE);
                    overlay4.setVisibility(View.VISIBLE);
                }

            }
        });


        plusClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FreeBet.this, Adddetails.class);
                if (!TextUtils.isEmpty(standardvalue.getText().toString())) {
                    intent.putExtra("pro", standardvalue.getText().toString());
                } else {
                    intent.putExtra("pro", "0");
                }
                startActivity(intent);
            }
        });
    }

    void standardCalclulate() {

        String bb_stake_value, bb_odds_value, lb_odds_value;
        bb_stake = findViewById(R.id.bb_stake);
        bb_odds = findViewById(R.id.bb_odds);
        lb_odds = findViewById(R.id.lb_odds);


        bb_stake_value = bb_stake.getText().toString();
        bb_odds_value = bb_odds.getText().toString();
        lb_odds_value = lb_odds.getText().toString();

        double actualBackodds = 1.0 + (1.0 - bb_comm) * (Float.parseFloat(bb_odds_value) - 1.0);

        //LAY STAKE
        double x = ((Float.parseFloat(bb_stake_value) * (actualBackodds - 1.0)) / (100 * (Float.parseFloat(lb_odds_value) - button))) * 100;
        s_laystack.setText(new DecimalFormat("##.00").format(x) + " Lay Stake");

        //LIABILITY
        double y = x * (Float.parseFloat(lb_odds_value) - 1.0);
        s_liability.setText(new DecimalFormat("##.00").format(y) + " Liability");

        //PROFIT LOSS
        double standardProfit = (Float.parseFloat(bb_stake_value) * actualBackodds) - y - Float.parseFloat(bb_stake_value);

        if (standardProfit < 0) {
            standardvalue.setText("£" + new DecimalFormat("##.00").format(standardProfit));
            standardvalue.setTextColor(getResources().getColor(R.color.red));
        } else if (standardProfit == 0.00) {
            standardvalue.setText("£" + new DecimalFormat("##.00").format(standardProfit));
            standardvalue.setTextColor(getResources().getColor(R.color.black));
        } else {
            standardvalue.setText("£" + new DecimalFormat("##.00").format(standardProfit));
            standardvalue.setTextColor(getResources().getColor(R.color.green));
        }

        //retention

        double retentions = 100 * (standardProfit / Float.parseFloat(bb_stake_value));
        s_retension.setText(new DecimalFormat("##.00").format(retentions) + "% retention");


    }

    void underlayCalculate() {

        String bb_stake_value, bb_odds_value, lb_odds_value;
        bb_stake = findViewById(R.id.bb_stake);
        bb_odds = findViewById(R.id.bb_odds);
        lb_odds = findViewById(R.id.lb_odds);


        bb_stake_value = bb_stake.getText().toString();
        bb_odds_value = bb_odds.getText().toString();
        lb_odds_value = lb_odds.getText().toString();


        double actualBackOdds, actualLayOdds, x, y, z;
        actualBackOdds = 1.0 + (1.0 - bb_comm) * (Float.parseFloat(bb_odds_value) - 1.0);
        actualLayOdds = (100 * Float.parseFloat(lb_odds_value) - (button * 100)) / (100 - (100 * button));

        if (actualBackOdds <= actualLayOdds) {


            //LIABILITY
            y = (Float.parseFloat(bb_stake_value) * (actualBackOdds - 1.0)) - Float.parseFloat(bb_stake_value);
            u_liability.setText("£" + new DecimalFormat("##.00").format(y) + " Liability");


            //LAY STACK
            x = y / (Float.parseFloat(lb_odds_value) - 1.0);
            u_laystack.setText("£" + new DecimalFormat("##.00").format(x) + " Lay Stake");

            //PROFIT LOSS

            //BACK WINS
            z = (actualBackOdds - 1.0) * Float.parseFloat(bb_stake_value) - y;
            if (z < 0) {
                u_backwin.setText("£" + new DecimalFormat("##.00").format(z) + "");
                u_backwin.setTextColor(getResources().getColor(R.color.red));
            } else if (z == 0) {
                u_backwin.setText("£" + new DecimalFormat("##.00").format(z) + "");
                u_backwin.setTextColor(getResources().getColor(R.color.black));
            } else {
                u_backwin.setText("£" + new DecimalFormat("##.00").format(z) + "");
                u_backwin.setTextColor(getResources().getColor(R.color.green));
            }


            //LAY WINS
            double w = x * (1.0 - button);
            if (w < 0) {
                u_laywin.setText("£" + new DecimalFormat("##.00").format(w) + "");
                u_laywin.setTextColor(getResources().getColor(R.color.red));
            } else if (w == 0) {
                u_laywin.setText("£" + new DecimalFormat("##.00").format(w) + "");
                u_laywin.setTextColor(getResources().getColor(R.color.black));
            } else {
                u_laywin.setText("£" + new DecimalFormat("##.##").format(w) + "");
                u_laywin.setTextColor(getResources().getColor(R.color.green));
            }
        } else {

            //LAY STAKE
            x = (Float.parseFloat(bb_stake_value)) / (1.0 - button);
            u_laystack.setText(new DecimalFormat("##.00").format(x) + " Lay Stake");

            //LIABILITY
            y = x * (Float.parseFloat(lb_odds_value) - 1.0);
            u_liability.setText(new DecimalFormat("##.00").format(y) + " Liability");

            //PROFIT LOSS

            //BACK WINS
            z = (Float.parseFloat(bb_stake_value) * (actualBackOdds - 1.0)) - y;

            if (z < 0) {
                u_backwin.setText("£" + new DecimalFormat("##.00").format(z) + "");
                u_backwin.setTextColor(getResources().getColor(R.color.red));
            } else if (z == 0.00) {
                u_backwin.setText("£" + new DecimalFormat("##.00").format(z) + "");
                u_backwin.setTextColor(getResources().getColor(R.color.black));
            } else {
                u_backwin.setText("£" + new DecimalFormat("##.00").format(z) + "");
                u_backwin.setTextColor(getResources().getColor(R.color.green));
            }

            //LAY WINS

            double w = x * (1.0 - button);

            if (w < 0) {
                u_laywin.setText("£" + new DecimalFormat("##.00").format(w) + "");
                u_laywin.setTextColor(getResources().getColor(R.color.red));
            } else if (w == 0) {
                u_laywin.setText("£" + new DecimalFormat("##.00").format(w) + "");
                u_laywin.setTextColor(getResources().getColor(R.color.black));
            } else {
                u_laywin.setText("£" + new DecimalFormat("##.00").format(w) + "");
                u_laywin.setTextColor(getResources().getColor(R.color.green));
            }

        }
    }

    void overlayCalculate() {

        String bb_stake_value, bb_odds_value, lb_odds_value;
        bb_stake = findViewById(R.id.bb_stake);
        bb_odds = findViewById(R.id.bb_odds);
        lb_odds = findViewById(R.id.lb_odds);


        bb_stake_value = bb_stake.getText().toString();
        bb_odds_value = bb_odds.getText().toString();
        lb_odds_value = lb_odds.getText().toString();

        double actualBackOdds, actualLayOdds, x, y, z;
        actualBackOdds = 1.0 + (1.0 - bb_comm) * (Float.parseFloat(bb_odds_value) - 1.0);
        actualLayOdds = (100 * Float.parseFloat(lb_odds_value) - (button * 100)) / (100 - (100 * button));

        if (actualBackOdds >= actualLayOdds) {

            //LIABILITY
            y = (Float.parseFloat(bb_stake_value) * (actualBackOdds - 1.0)) - Float.parseFloat(bb_stake_value);
            o_liability.setText("£" + new DecimalFormat("##.00").format(y) + " Liability");

            //LAY STACK
            x = y / (Float.parseFloat(lb_odds_value) - 1.0);
            o_laystack.setText("£" + new DecimalFormat("##.00").format(x) + " Lay Stake");

            //PROFIT LOSS

            //BACK WINS
            z = (actualBackOdds - 1.0) * Float.parseFloat(bb_stake_value) - y;
            if (z < 0) {
                o_backwin.setText("£" + new DecimalFormat("##.00").format(z) + "");
                o_backwin.setTextColor(getResources().getColor(R.color.red));
            } else if (z == 0) {
                o_backwin.setText("£" + new DecimalFormat("##.00").format(z) + "");
                o_backwin.setTextColor(getResources().getColor(R.color.black));
            } else {
                o_backwin.setText("£" + new DecimalFormat("##.00").format(z) + "");
                o_backwin.setTextColor(getResources().getColor(R.color.green));
            }


            //LAY WINS
            double w = x * (1.0 - button);
            if (w < 0) {
                o_laywin.setText("£" + new DecimalFormat("##.00").format(w) + "");
                o_laywin.setTextColor(getResources().getColor(R.color.red));
            } else if (w == 0) {
                o_laywin.setText("£" + new DecimalFormat("##.00").format(w) + "");
                o_laywin.setTextColor(getResources().getColor(R.color.black));
            } else {
                o_laywin.setText("£" + new DecimalFormat("##.00").format(w) + "");
                o_laywin.setTextColor(getResources().getColor(R.color.green));
            }
        } else {

            //LAY STAKE
            x = (Float.parseFloat(bb_stake_value)) / (1.0 - button);
            o_laystack.setText("£" + new DecimalFormat("##.00").format(x) + "Lay Stake");

            //LIABILITY
            y = x * (Float.parseFloat(lb_odds_value) - 1.0);
            o_liability.setText("£" + new DecimalFormat("##.00").format(y) + "Liability");

            //PROFIT LOSS

            //BACK WINS
            z = (Float.parseFloat(bb_stake_value) * (actualBackOdds - 1.0)) - y;

            if (z < 0) {
                o_backwin.setText("£" + new DecimalFormat("##.00").format(z) + "");
                o_backwin.setTextColor(getResources().getColor(R.color.red));
            } else if (z == 0.00) {
                o_backwin.setText("£" + new DecimalFormat("##.00").format(z) + "");

                o_backwin.setTextColor(getResources().getColor(R.color.black));
            } else {
                o_backwin.setText("£" + new DecimalFormat("##.00").format(z) + "");
                o_backwin.setTextColor(getResources().getColor(R.color.green));
            }
            //LAY WINS

            double w = x * (1.0 - button);

            if (w < 0) {
                o_laywin.setText("£" + new DecimalFormat("##.00").format(w) + "");
                o_laywin.setTextColor(getResources().getColor(R.color.red));
            } else if (w == 0) {
                o_laywin.setText("£" + new DecimalFormat("##.00").format(w) + "");
                o_laywin.setTextColor(getResources().getColor(R.color.black));
            } else {
                o_laywin.setText("£" + new DecimalFormat("##.00").format(w) + "");
                o_laywin.setTextColor(getResources().getColor(R.color.green));
            }

        }
    }
}


