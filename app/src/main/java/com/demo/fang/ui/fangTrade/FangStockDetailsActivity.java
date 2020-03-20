package com.demo.fang.ui.fangTrade;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.demo.fang.R;
import com.demo.fang.base.BaseActivity;
import com.demo.fang.bean.StockDataBean;
import com.demo.fang.bean.StockModle;
import com.demo.fang.database.FangDbController;
import com.demo.fang.ui.stock.StockPresenter;
import com.demo.fang.ui.stock.StockView;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FangStockDetailsActivity extends BaseActivity<StockPresenter> implements StockView {
    private static StockDataBean mDataBean;
    @BindView(R.id.et_cost)
    EditText etCost;
    @BindView(R.id.et_current_price)
    EditText etCurrentPrice;
    @BindView(R.id.et_stock_count)
    EditText etStockCount;

    @Override
    protected StockPresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_fang_detail;
    }

    public static void launch(Context context, StockDataBean dataBean) {
        Intent intent = new Intent(context, FangStockDetailsActivity.class);
        intent.putExtra("data", (Serializable) dataBean);
        context.startActivity(intent);
    }

    @Override
    protected void restoreSaveInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        super.bindView(view, savedInstanceState);
        mDataBean = new StockDataBean();
    }

    @Override
    public void initData() {
        super.initData();
        mDataBean = (StockDataBean) getIntent().getSerializableExtra("data");
        if (mDataBean != null) {
            etCost.setText(mDataBean.getBuyPrice());
            etCurrentPrice.setText(mDataBean.getNewPrice());
            etStockCount.setText(mDataBean.getBuyMeasure());
        }
    }

    @Override
    public void onStockSucc(StockModle stockModle) {

    }

    @Override
    public void loadMoreData() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_change)
    public void onViewClicked() {
        mDataBean.setBuyMeasure(etStockCount.getText().toString());
        FangDbController.getInstance().saveNote(mDataBean);
    }
}
