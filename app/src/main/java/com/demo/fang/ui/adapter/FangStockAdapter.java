package com.demo.fang.ui.adapter;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.demo.fang.R;
import com.demo.fang.bean.StockDataBean;
import java.util.List;

public class FangStockAdapter extends BaseQuickAdapter<StockDataBean, BaseViewHolder> {
    public FangStockAdapter(int layoutResId, @Nullable List<StockDataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StockDataBean item) {
        helper.setText(R.id.tv_stock_name,item.getName());
    }
}
