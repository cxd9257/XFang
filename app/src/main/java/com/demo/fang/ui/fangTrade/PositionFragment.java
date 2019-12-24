package com.demo.fang.ui.fangTrade;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.demo.fang.R;
import com.demo.fang.base.BaseFragment;
import com.demo.fang.base.baseMVP.BasePresenter;
import com.demo.fang.bean.StockDataBean;
import com.demo.fang.database.FangDbController;
import com.demo.fang.ui.adapter.FangStockAdapter;
import com.demo.fang.widget.FonstText;

import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class PositionFragment extends BaseFragment {
    @BindView(R.id.txt_all_money)
    FonstText txtAllMoney;
    @BindView(R.id.txt_all_profit)
    FonstText txtAllProfit;
    @BindView(R.id.txt_today_profit)
    FonstText txtTodayProfit;
    @BindView(R.id.txt_all_market)
    FonstText txtAllMarket;
    @BindView(R.id.txt_desirable)
    FonstText txtDesirable;
    @BindView(R.id.txt_usable)
    TextView txtUsable;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.ptrClassic)
    PtrClassicFrameLayout ptrClassic;

    private List<StockDataBean> beanList;
    private FangStockAdapter mStockAdapter;
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_position;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        super.bindView(view, savedInstanceState);
        ptrClassic.disableWhenHorizontalMove(true);
        ptrClassic.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame,mRecyclerView,header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                loadingData();
                ptrClassic.refreshComplete();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mStockAdapter = new FangStockAdapter(R.layout.item_fang_context,beanList));
        mStockAdapter.setEnableLoadMore(true);
        mStockAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                StockDataBean itemBean = (StockDataBean) adapter.getItem(position);
                ShowToast(itemBean.getName());
            }
        });
    }
    /**
     * 设置开头数据
     */
    private void setHeadData(){

    }

    @Override
    public void initData() {
        super.initData();
        loadingData();
    }

    /**
     * 获取本地数据
     */
    private void loadingData(){
        beanList = FangDbController.getInstance().queryAll();
        if (beanList == null){
            return;
        }
        mStockAdapter.setNewData(beanList);
    }
}
