package com.demo.fang.database;

import com.demo.fang.bean.StockDataBean;

import java.util.List;

public class FangDbController {
    private static FangDbController instance;//写成单例
    public static DaoSession mDaoSession;
    public StockDataBeanDao dao;
    public static FangDbController getInstance(){
        if (instance == null){
            instance = new FangDbController();
        }
        return instance;
    }

    private FangDbController(){
        mDaoSession = FangDbOpenHelper.mDaoSession;
        dao = mDaoSession.getStockDataBeanDao();
    }

    public void insertOrReplace(StockDataBean stockDataBean){
        this.dao.insertOrReplace(stockDataBean);
    }
    public List<StockDataBean> queryAll(){
        return dao.queryBuilder().list();
    }
}
