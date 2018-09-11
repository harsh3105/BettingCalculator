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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.teamprofit.calculator.R;

import java.text.DecimalFormat;


import co.ceryle.segmentedbutton.SegmentedButton;
import co.ceryle.segmentedbutton.SegmentedButtonGroup;


public class Normal extends AppCompatActivity {

    private TextView text ;
    private LinearLayout underlay1, underlay2, underlay3, standard1, standard2, standard3, overlay1, overlay2, overlay3;
    private ImageView underlay4, standard4, overlay4;
    me.grantland.widget.AutofitTextView u_laystack,u_liability,u_backwin,u_laywin, s_laystack, s_liability, standardvalue, o_laystack, o_liability, o_backwin, o_laywin;

    private EditText bb_stake, bb_odds, lb_odds;
    private SegmentedButton button1, button2, button3;
    ImageView plusClick;

    String bb_stake_value, bb_odds_value, lb_odds_value;

    float bb_comm = 0.00f;

    double button = 0.05;
    Context mContext;
    private int maxDigitsBeforeDecimalPoint = 15;
    private int maxDigitsAfterDecimalPoint = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        mContext = this;
        plusClick = findViewById(R.id.addData);


        u_laystack = findViewById(R.id.u_laystack);
        u_liability = findViewById(R.id.u_liability);
        u_backwin = findViewById(R.id.u_backwin);
        u_laywin = findViewById(R.id.u_laywins);

        s_laystack = findViewById(R.id.s_laystack);
        s_liability = findViewById(R.id.s_liability);
        standardvalue = findViewById(R.id.standardvalue);

        o_laystack = findViewById(R.id.o_laystake);
        o_liability = findViewById(R.id.o_liability);
        o_backwin = findViewById(R.id.o_backwins);
        o_laywin = findViewById(R.id.o_laywins);


        bb_stake = findViewById(R.id.bb_stake);
        bb_odds = findViewById(R.id.bb_odds);
        lb_odds = findViewById(R.id.lb_odds);

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



        button1 = findViewById(R.id.comm_0);
        button2 = findViewById(R.id.comm_2);
        button3 = findViewById(R.id.comm_5);
        final SegmentedButtonGroup segmentedButtonGroup = (SegmentedButtonGroup) findViewById(R.id.segmentedButtonGroup);
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

                            underlaycalculate();
                            overlaycalculate();
                            standardcaluclate();
                        }
                        if (segmentedButtonGroup.getPosition() == 1) {
                            button = 0.02;

                            standardcaluclate();
                            underlaycalculate();
                            overlaycalculate();
                        }
                        if (segmentedButtonGroup.getPosition() == 2) {
                            button = 0.05;
                            underlaycalculate();
                            overlaycalculate();
                            standardcaluclate();
                        }
                    }
                }
            }
        });

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

                            underlaycalculate();
                            overlaycalculate();
                            standardcaluclate();
                        }
                        if (segmentedButtonGroup.getPosition() == 1) {
                            button = 0.02;

                            standardcaluclate();
                            underlaycalculate();
                            overlaycalculate();
                        }
                        if (segmentedButtonGroup.getPosition() == 2) {
                            button = 0.05;
                            underlaycalculate();
                            overlaycalculate();
                            standardcaluclate();
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

                            underlaycalculate();
                            overlaycalculate();
                            standardcaluclate();
                        }
                        if (segmentedButtonGroup.getPosition() == 1) {
                            button = 0.02;

                            standardcaluclate();
                            underlaycalculate();
                            overlaycalculate();
                        }
                        if (segmentedButtonGroup.getPosition() == 2) {
                            button = 0.05;
                            underlaycalculate();
                            overlaycalculate();
                            standardcaluclate();
                        }
                    }
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
                        underlaycalculate();
                        overlaycalculate();
                        standardcaluclate();
                    }
                    if (position == 1) {
                        button = 0.02;
                        standardcaluclate();
                        underlaycalculate();
                        overlaycalculate();
                    }
                    if (position == 2) {
                        button = 0.05;
                        underlaycalculate();
                        overlaycalculate();
                        standardcaluclate();
                    }
                } else {
                    Toast.makeText(mContext, "Please fill filed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        segmentedButtonGroup.setPosition(2, 0);
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
                        underlaycalculate();
                    }

                } else {
                    underlay2.setVisibility(View.GONE);
                    underlay3.setVisibility(View.GONE);
                    underlay4.setVisibility(View.VISIBLE);

                }
            }
        });

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
                    if (TextUtils.isEmpty(bb_stake_value)) {
                        Toast.makeText(mContext, "Stake Value is empty", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(bb_odds_value)) {
                        Toast.makeText(mContext, "Stake odds is empty", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(lb_odds_value)) {
                        Toast.makeText(mContext, "Lay bet odds is empty", Toast.LENGTH_SHORT).show();
                    } else {
                        standardcaluclate();
                    }

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
                        overlaycalculate();
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
                Intent intent = new Intent(Normal.this, Adddetails.class);
                if (!TextUtils.isEmpty(standardvalue.getText().toString())) {
                    intent.putExtra("pro", standardvalue.getText().toString());
                } else {
                    intent.putExtra("pro", "0");
                }
                startActivity(intent);
            }
        });
    }


    void standardcaluclate() {


        //LAY STAKE
        double x;
        x = (Float.parseFloat(bb_stake_value) * (Float.parseFloat(bb_odds_value) - 1.0) * (1.0 - bb_comm) + Float.parseFloat(bb_stake_value)) / (100 * (Float.parseFloat(lb_odds_value) - button)) * 100;
        s_laystack.setText("£" + new DecimalFormat("##.00").format(x) + " Lay Stake");

        //Liability

        double y;
        y = x * (Float.parseFloat(lb_odds_value) - 1);
        s_liability.setText("£" + new DecimalFormat("##.00").format(y) + " Liability");

        //profit loss

        double standardProfit;

        standardProfit = x * (1.0 - button) - Float.parseFloat(bb_stake_value);

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

//        standardvaluereturn(standardProfit);

    }

    void underlaycalculate() {


        double actualBackOdds, actualLayOdds, x, y, z;
        actualBackOdds = 1.0 + (1.0 - bb_comm) * (Float.parseFloat(bb_odds_value) - 1.0);
        actualLayOdds = (100 * Float.parseFloat(lb_odds_value) - (button * 100)) / (100 - (100 * button));

        if (actualBackOdds <= actualLayOdds) {

            //LAY STACK
            x = (Float.parseFloat(bb_stake_value) * (Float.parseFloat(bb_odds_value) - 1.0) * (1.0 - bb_comm)) / (Float.parseFloat(lb_odds_value) - 1.0);
            u_laystack.setText("£" + new DecimalFormat("##.00").format(x) + " Lay Stake");

            //LIABILITY
            y = x * (Float.parseFloat(lb_odds_value) - 1.0);
            u_liability.setText("£" + new DecimalFormat("##.00").format(y) + " Liability");

            //PROFIT LOSS

            //BACK WINS
            z = x - x;
            u_backwin.setText("£" + new DecimalFormat("##.##").format(z));

            //LAY WINS
            double w = ((Float.parseFloat(bb_stake_value) * -1.0) + (x * (1.0 - button)));

            if (w < 0) {
                u_laywin.setText("£" + new DecimalFormat("##.##").format(w));
                u_laywin.setTextColor(getResources().getColor(R.color.red));
            } else if (w == 0) {
                u_laywin.setText("£" + new DecimalFormat("##.##").format(w));
                u_laywin.setTextColor(getResources().getColor(R.color.black));
            } else {
                u_laywin.setText("£" + new DecimalFormat("##.##").format(w));
                u_laywin.setTextColor(getResources().getColor(R.color.green));
            }
        } else {

            //LAY STAKE
            x = (Float.parseFloat(bb_stake_value)) / (1.0 - button);
            u_laystack.setText("£" + new DecimalFormat("##.00").format(x)+" Lay Stake");

            //LIABILITY
            y = x * (Float.parseFloat(lb_odds_value) - 1.0);
            u_liability.setText("£" + new DecimalFormat("##.00").format(y)+" Liability");

            //PROFIT LOSS

            //BACK WINS
            z = (Float.parseFloat(bb_stake_value) * (actualBackOdds - 1.0)) - y;

            if (z < 0) {
                u_backwin.setText("£" + new DecimalFormat("##.##").format(z));
                u_backwin.setTextColor(getResources().getColor(R.color.red));
            } else if (z == 0.00) {
                u_backwin.setText("£" + new DecimalFormat("##.##").format(z));
                u_backwin.setTextColor(getResources().getColor(R.color.black));
            } else {
                u_backwin.setText("£" + new DecimalFormat("##.##").format(z));
                u_backwin.setTextColor(getResources().getColor(R.color.green));
            }

            //LAY WINS

            double w = ((Float.parseFloat(bb_stake_value) * -1.0) + (x * (1.0 - button)));

            if (w < 0) {
                u_laywin.setText("£" + new DecimalFormat("##.##").format(w));
                u_laywin.setTextColor(getResources().getColor(R.color.red));
            } else if (w == 0) {
                u_laywin.setText("£" + new DecimalFormat("##.##").format(w));
                u_laywin.setTextColor(getResources().getColor(R.color.black));
            } else {
                u_laywin.setText("£" + new DecimalFormat("##.##").format(w));
                u_laywin.setTextColor(getResources().getColor(R.color.green));
            }

        }

    }

    void overlaycalculate() {


        double actualBackOdds, actualLayOdds, x, y, z;
        actualBackOdds = 1.0 + (1.0 - bb_comm) * (Float.parseFloat(bb_odds_value) - 1.0);
        actualLayOdds = (100 * Float.parseFloat(lb_odds_value) - (button * 100)) / (100 - (100 * button));

        if (actualBackOdds > actualLayOdds) {


            //LAY STAKE

            x = (Float.parseFloat(bb_stake_value) * (Float.parseFloat(bb_odds_value) - 1.0) * (1.0 - bb_comm)) / (Float.parseFloat(lb_odds_value) - 1.0);
            o_laystack.setText("£" + new DecimalFormat("##.00").format(x) + " Lay Stake");


            //LIABILITY
            y = x * (Float.parseFloat(lb_odds_value) - 1.0);
            o_liability.setText("£" + new DecimalFormat("##.00").format(y) + " Liability");

            //PROFIT LOSS

            //backwin
            z = x - x;
            o_backwin.setText("£" + new DecimalFormat("##.##").format(z));

            //Lay Wins
            double w = ((Float.parseFloat(bb_stake_value) * -1.0) + (x * (1.0 - button)));

            if (w < 0) {
                o_laywin.setText("£" + new DecimalFormat("##.##").format(w));
                o_laywin.setTextColor(getResources().getColor(R.color.red));
            } else if (w == 0) {
                o_laywin.setText("£" + new DecimalFormat("##.##").format(w));
                o_laywin.setTextColor(getResources().getColor(R.color.black));
            } else {
                o_laywin.setText("£" + new DecimalFormat("##.##").format(w));
                o_laywin.setTextColor(getResources().getColor(R.color.green));
            }
        } else {

            //LAY STAKE
            x = (Float.parseFloat(bb_stake_value)) / (1.0 - button);
            o_laystack.setText("£" + new DecimalFormat("##.00").format(x) + " Lay Stake");

            //LIABILITY
            y = x * (Float.parseFloat(lb_odds_value) - 1.0);
            o_liability.setText("£" + new DecimalFormat("##.00").format(y) + " Liability");

            //PROFIT LOSS

            //BACK WINS
            z = (Float.parseFloat(bb_stake_value) * (actualBackOdds - 1.0)) - y;

            if (z < 0) {
                o_backwin.setText("£" + new DecimalFormat("##.##").format(z));
                o_backwin.setTextColor(getResources().getColor(R.color.red));
            } else if (z == 0.00) {
                o_backwin.setText("£" + new DecimalFormat("##.##").format(z));
                o_backwin.setTextColor(getResources().getColor(R.color.black));
            } else {
                o_backwin.setText("£" + new DecimalFormat("##.##").format(z));
                o_backwin.setTextColor(getResources().getColor(R.color.green));
            }

            //LAY WINS

            double w = ((Float.parseFloat(bb_stake_value) * -1.0) + (x * (1.0 - button)));

            if (w < 0) {
                o_laywin.setText("£" + new DecimalFormat("##.##").format(w));

                o_laywin.setTextColor(getResources().getColor(R.color.red));
            } else if (w == 0) {
                o_laywin.setText("£" + new DecimalFormat("##.##").format(w));
                o_laywin.setTextColor(getResources().getColor(R.color.black));

            } else {
                o_laywin.setText("£" + new DecimalFormat("##.##").format(w));
                o_laywin.setTextColor(getResources().getColor(R.color.green));
            }

        }


    }
}