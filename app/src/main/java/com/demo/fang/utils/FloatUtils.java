package com.demo.fang.utils;

import java.text.DecimalFormat;

public class FloatUtils {
    public static String DecimalFormat2(String a){
        DecimalFormat decimalFormat=new DecimalFormat(".00");
        String pri=decimalFormat.format(a);
        return pri;
    }
    public static String DecimalFormat3(String a){
        DecimalFormat decimalFormat=new DecimalFormat(".000");
        String pri=decimalFormat.format(a);
        return pri;
    }
    public static String FloatToString(Float a){
        DecimalFormat df = new DecimalFormat("#########.#");

        String marketValue = df.format(a);
        return marketValue;
    }
}
