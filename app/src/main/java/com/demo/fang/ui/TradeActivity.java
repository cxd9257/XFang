package com.demo.fang.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.demo.fang.R;
import com.demo.fang.base.BaseActivity;
import com.demo.fang.base.baseMVP.BasePresenter;
import com.demo.fang.ui.fangTrade.FirstFragment;
import com.demo.fang.ui.fangTrade.PositionFragment;
import com.demo.fang.widget.CustomViewPager;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TradeActivity extends BaseActivity {
    @BindView(R.id.slidingTabLayout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    private ArrayList<Fragment> mFragments;
    private String[] mTitle = {"买入","卖出","撤单","委托","持仓","查询"};
    private MyPagerAdapter mAdapter;
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void restoreSaveInstanceState(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_fang_trade;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void initView() {
        super.initView();
        mFragments = new ArrayList<>();
        mFragments.add(new FirstFragment());
        mFragments.add(new FirstFragment());
        mFragments.add(new FirstFragment());
        mFragments.add(new FirstFragment());
        mFragments.add(new PositionFragment());
        mFragments.add(new FirstFragment());
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        slidingTabLayout.setViewPager(viewPager);
    }
    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
    }
}
