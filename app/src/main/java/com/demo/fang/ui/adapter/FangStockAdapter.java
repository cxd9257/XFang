package com.demo.fang.ui.adapter;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.demo.fang.R;
import com.demo.fang.bean.StockDataBean;
import com.demo.fang.utils.FloatUtils;
import com.demo.fang.utils.SharePrefUtil;

import java.text.DecimalFormat;
import java.util.List;

public class FangStockAdapter extends BaseQuickAdapter<StockDataBean, BaseViewHolder> {
    public FangStockAdapter(int layoutResId, @Nullable List<StockDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StockDataBean item) {
         String code;
         String name =item.getName();
         String buyPrice =item.getBuyPrice();
         String newPrice =item.getNewPrice();
         String buyMeasure =item.getBuyMeasure();
         float marketValues =Float.parseFloat(newPrice)*Float.parseFloat(buyMeasure);
         String marketValue = FloatUtils.FloatToString(marketValues);

         //String allMarketValue = SharePrefUtil.getString("allMoney");
         String date;

        helper.setText(R.id.tv_stock_name,name);
        helper.setText(R.id.txt_all_market,FloatUtils.DecimalFormat2(marketValue));
        helper.setText(R.id.tv_cost,FloatUtils.DecimalFormat3(buyPrice));
        helper.setText(R.id.tv_current_price,FloatUtils.DecimalFormat3(newPrice));

    }
}
