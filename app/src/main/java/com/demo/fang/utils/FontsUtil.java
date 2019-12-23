package com.demo.fang.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;

public class FontsUtil {
    public static void setFonts(TextView textView,Context context) {
        AssetManager mgr = context.getAssets();
        Typeface kt3 = Typeface.createFromAsset(mgr,"fonts/DINPro-Medium.ttf");
        textView.setTypeface(kt3);
    }
}
