package com.demo.fang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.demo.fang.base.BaseActivity;
import com.demo.fang.base.baseMVP.BasePresenter;
import com.demo.fang.ui.TradeActivity;
import com.demo.fang.utils.StatusBarUtil;
import com.demo.fang.widget.StockDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_money)
    TextView mAllMoney;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected void restoreSaveInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        super.bindView(view, savedInstanceState);
    }

    @Override
    public void initView() {
        super.initView();
    }

    @OnClick({R.id.btn_buy, R.id.btn_fang_trade, R.id.btn_set_money})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_buy:
                StockDialogFragment stockDialogFragment = StockDialogFragment.newInstance();
                stockDialogFragment.show(getSupportFragmentManager(), "");
                break;
            case R.id.btn_fang_trade:
                startActivity(new Intent(this, TradeActivity.class));
                break;
            case R.id.btn_set_money:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
          ButterKnife.bind(this);
    }
}
