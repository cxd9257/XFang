package com.demo.fang.ui.stock;

import com.demo.fang.base.baseMVP.BaseObserver;
import com.demo.fang.base.baseMVP.BasePresenter;
import com.demo.fang.bean.StockModle;
import com.demo.fang.net.ApiConfig;

public class StockPresenter extends BasePresenter<StockView> {
    public StockPresenter(StockView baseView) {
        super(baseView);
    }
    public void getStockData(String gid){
        addDisposable(apiServer.getStockData(gid, ApiConfig.JuHeStockKey), new BaseObserver<StockModle>(baseView) {
            @Override
            protected void onSuccess(StockModle stockModle) {
                baseView.onStockSucc(stockModle);
                baseView.hideLoading();
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
