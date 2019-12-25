package com.demo.fang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.demo.fang.base.BaseActivity;
import com.demo.fang.base.baseMVP.BasePresenter;
import com.demo.fang.ui.TradeActivity;
import com.demo.fang.utils.SharePrefUtil;
import com.demo.fang.widget.SetMoneyDialogFragment;
import com.demo.fang.widget.StockDialogFragment;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tv_money)
    TextView mAllMoney;
    @BindView(R.id.btn_set_money)
    Button btnSetMoney;
    @BindView(R.id.btn_buy)
    Button btnBuy;
    @BindView(R.id.btn_fang_trade)
    Button btnFangTrade;
    @BindView(R.id.btn_fa_trade)
    Button btnFaTrade;
    private double allMoney;

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
        if (isFirstStart()) {
            SharePrefUtil.putString("allMoney", random() + "");
            SharePrefUtil.commit();
        } else {
            mAllMoney.setText(SharePrefUtil.getString("allMoney"));
        }
        mAllMoney.setText(SharePrefUtil.getString("allMoney"));
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
                SetMoneyDialogFragment moneyDialogFragment = SetMoneyDialogFragment.newInstance();
                moneyDialogFragment.show(getSupportFragmentManager(), "");
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 检查是否第一次启动
     */
    private boolean isFirstStart() {
        if (SharePrefUtil.getBoolean("isFristLoad")) {
            SharePrefUtil.putBoolean("isFristLoad", false);
            SharePrefUtil.commit();
            return true;
        } else {
            return false;
        }
    }

    /**
     * 生成随机数
     */
    private double random() {
        Random random = new Random();
        double max = 22000.00;
        double min = 11000.00;
        double temp = random.nextDouble() * max + min;
        double low = min - 1;
        double high = max + 1;
        while (temp < low || temp > high) {
            temp = random.nextDouble() * max + min;
        }
        return temp;
    }

}
