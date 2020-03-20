package com.demo.fang.database;

import android.database.Cursor;

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
    public List<StockDataBean> querySum(){
        return dao.queryBuilder().where(StockDataBeanDao.Properties.AllMarketValue.eq("MARKET_VALUE")).list();
    }
    /**
     * 根据查询条件,返回数据列表
     * @param where        条件
     * @param params       参数
     * @return             数据列表
     */
    public List<StockDataBean> queryNote(String where, String... params){
        return dao.queryRaw(where, params);
    }



    public void deleteNote(long id){
        dao.deleteByKey(id);
    }

    /**
     * 根据用户信息,插件或修改信息
     * @param user              用户信息
     * @return 插件或修改的用户id
     */
    public long saveNote(StockDataBean user){
        return dao.insertOrReplace(user);
    }

}
