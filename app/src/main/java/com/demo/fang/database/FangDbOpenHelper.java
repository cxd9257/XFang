package com.demo.fang.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.demo.fang.MyApp;
import com.demo.fang.utils.AppConfig;

public class FangDbOpenHelper extends DaoMaster.OpenHelper {
    public static DaoSession mDaoSession;
    public FangDbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    public static void initDataBase(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MyApp.getContext(), AppConfig.DB_NAME);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
    }
}
