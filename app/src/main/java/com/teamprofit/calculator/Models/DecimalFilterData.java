package com.teamprofit.calculator.Models;

public class DecimalFilterData {

        //Pattern mPattern;
        String regex = "[0-9]+((\\.[0-9]{0," + (2 - 1) + "})?)||(\\.)?";
        public DecimalFilterData(int digitsAfterZero)
        {
            //mPattern = Pattern.compile("[0-9]+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?");
            regex = "[0-9]+((\\.[0-9]{0," + (digitsAfterZero - 1) + "})?)||(\\.)?";
        }
}
