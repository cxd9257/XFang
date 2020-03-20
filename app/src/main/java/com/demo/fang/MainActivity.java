package com.demo.fang;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.demo.fang.base.BaseActivity;
import com.demo.fang.base.baseMVP.BasePresenter;
import com.demo.fang.ui.TradeActivity;
import com.demo.fang.utils.AppConfig;
import com.demo.fang.utils.FloatUtils;
import com.demo.fang.utils.SharePrefUtil;
import com.demo.fang.widget.SetMoneyDialogFragment;
import com.demo.fang.widget.StockDialogFragment;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
        if (isFirstStart()) {
            SharePrefUtil.putString(AppConfig.ALL_MONEY, FloatUtils.DecimalFormat2(random()));
            SharePrefUtil.commit();
            Log.e("xxxx888","true");
        } else {
            Log.e("xxxx","fa");
            mAllMoney.setText(SharePrefUtil.getString(AppConfig.ALL_MONEY));
        }
    }

    @Override
    public void initView() {
        super.initView();
        mAllMoney.setText(SharePrefUtil.getString(AppConfig.ALL_MONEY));
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
    protected void onRestart() {
        super.onRestart();
        Log.e("onRestart",SharePrefUtil.getBoolean("isFristLoad")+""+random());
        mAllMoney.setText(SharePrefUtil.getString(AppConfig.ALL_MONEY));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("onResume",SharePrefUtil.getBoolean("isFristLoad")+""+random());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("onPause",SharePrefUtil.getBoolean("isFristLoad")+""+random());
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
        Log.e("xxx",SharePrefUtil.getBoolean("isFristLoad")+"");
        if (SharePrefUtil.getBoolean("isFristLoad")) {
            return false;
        } else {
            SharePrefUtil.putBoolean("isFristLoad", true);
            SharePrefUtil.commit();
            return true;
        }
    }

    /**
     * 生成随机数
     */
    private float random() {
        float min = 100f;
        float max = 32050f;
        float floatBounded = min + new Random().nextFloat() * (max - min);
            return floatBounded;
    }

}
