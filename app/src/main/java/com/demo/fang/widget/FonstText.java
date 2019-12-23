package com.demo.fang.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class FonstText extends TextView {
    public FonstText(Context context) {
        super(context);
        init(context);
    }

    public FonstText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FonstText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/DINPro-Medium.ttf");
        this.setTypeface(typeface);
    }
}
