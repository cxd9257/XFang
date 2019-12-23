package com.demo.fang.ui.stock;

import com.demo.fang.base.baseMVP.BaseView;
import com.demo.fang.bean.StockModle;

public interface StockView extends BaseView {
    void onStockSucc(StockModle stockModle);
    void loadMoreData();
}
