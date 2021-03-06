package com.demo.fang.utils;

import java.text.DecimalFormat;

public class FloatUtils {
    public static String DecimalFormat2(float a){
        DecimalFormat decimalFormat=new DecimalFormat(".00");
        String pri=decimalFormat.format(a);
        return pri;
    }
    public static String DecimalFormat3(float a){
        DecimalFormat decimalFormat=new DecimalFormat(".000");
        String pri=decimalFormat.format(a);
        return pri;
    }
    public static String FloatToString(Float a){
        DecimalFormat df = new DecimalFormat("#########.#");
        String marketValue = df.format(a);
        return marketValue;
    }
    public static Float StringToFloat(String a){
        float d= Float.parseFloat(a);
        return d;
    }
    public static String Profit(float nowMarketValues,float marketValues){
        float profitValus = nowMarketValues-marketValues;
        return FloatUtils.DecimalFormat2(profitValus);
    }
    public static String profitPercent(float newPrice,float buyPrice){
        float profitPercentValus = (newPrice-buyPrice)/buyPrice*100;
        return FloatUtils.DecimalFormat2(profitPercentValus)+"%";
    }


}
