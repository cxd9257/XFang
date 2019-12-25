package com.demo.fang.ui.adapter;
import android.graphics.Color;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.demo.fang.R;
import com.demo.fang.bean.StockDataBean;
import com.demo.fang.utils.DateUtil;
import com.demo.fang.utils.FloatUtils;
import com.demo.fang.utils.SharePrefUtil;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FangStockAdapter extends BaseQuickAdapter<StockDataBean, BaseViewHolder> {
    public FangStockAdapter(int layoutResId, @Nullable List<StockDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StockDataBean item) {
         String sameDate;
         String name =item.getName();
         String buyPrice =item.getBuyPrice();
         String newPrice =item.getNewPrice();
         String buyMeasure =item.getBuyMeasure();
         float marketValues =Float.parseFloat(newPrice)*Float.parseFloat(buyMeasure);//总成本
         float nowMarketValues =Float.parseFloat(buyPrice)*Float.parseFloat(buyMeasure);//现在市值
         String marketValue = FloatUtils.FloatToString(marketValues);
         //String allMarketValue = SharePrefUtil.getString("allMoney");
         String date = item.getDate();
        helper.setText(R.id.tv_stock_name,name);
        helper.setText(R.id.txt_all_market,FloatUtils.DecimalFormat2(marketValue));
        //成本/现价
        helper.setText(R.id.tv_cost,FloatUtils.DecimalFormat3(buyPrice));
        helper.setText(R.id.tv_current_price,FloatUtils.DecimalFormat3(newPrice));
        //持仓/可用
        helper.setText(R.id.tv_position,item.getMarketValue());
        if (DateUtil.isSameDay(DateUtil.strToDateLong(date),DateUtil.getNowDate())){
            helper.setText(R.id.tv_usable,"0");
        }else{
            helper.setText(R.id.tv_usable,item.getMarketValue());
        }
        //盈亏
        if (nowMarketValues>marketValues){
            helper.setTextColor(R.id.tv_profit, Color.parseColor("#f31510"));
            helper.setTextColor(R.id.tv_profit_percent,Color.parseColor("#f31510"));
        }else if (nowMarketValues<marketValues){
            helper.setTextColor(R.id.tv_profit, Color.parseColor("#29ae1c"));
            helper.setTextColor(R.id.tv_profit_percent,Color.parseColor("#29ae1c"));
        }
        helper.setText(R.id.tv_profit,FloatUtils.Profit(nowMarketValues,marketValues));
        helper.setText(R.id.tv_profit_percent,FloatUtils.profitPercent(Float.parseFloat(newPrice),Float.parseFloat(buyPrice)));
    }
}
