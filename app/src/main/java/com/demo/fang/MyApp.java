package com.demo.fang;

import com.demo.fang.database.FangDbOpenHelper;

import org.litepal.LitePalApplication;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

public class MyApp extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        BGASwipeBackHelper.init(this,null);
        FangDbOpenHelper.initDataBase();
    }
}
