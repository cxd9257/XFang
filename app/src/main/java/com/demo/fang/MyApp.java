package com.demo.fang;

import org.litepal.LitePalApplication;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

public class MyApp extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        BGASwipeBackHelper.init(this,null);
    }
}
