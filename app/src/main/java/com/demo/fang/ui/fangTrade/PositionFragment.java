package com.demo.fang.ui.fangTrade;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.demo.fang.R;
import com.demo.fang.base.BaseFragment;
import com.demo.fang.base.baseMVP.BasePresenter;
import com.demo.fang.bean.StockDataBean;
import com.demo.fang.database.FangDbController;
import com.demo.fang.ui.adapter.FangStockAdapter;
import com.demo.fang.utils.AppConfig;
import com.demo.fang.utils.FloatUtils;
import com.demo.fang.utils.SharePrefUtil;
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
    private List<StockDataBean> marketValueList;
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
                FangStockDetailsActivity.launch(getContext(),itemBean);

            }
        });
        mRecyclerView.addOnItemTouchListener(new OnItemLongClickListener() {
            @Override
            public void onSimpleItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                StockDataBean itemBean = (StockDataBean) adapter.getItem(position);
                showDeleteStock(itemBean.getIds());
            }
        });
    }
    /**
     * 设置开头数据
     */
    private void setHeadData(){
        marketValueList = FangDbController.getInstance().queryAll();
        if (marketValueList.size()>0){
            ShowToast("查看下"+marketValueList.get(0).getMarketValue()+"");
            // double a =  marketValueList.stream().mapToDouble(StockDataBean::marketValueList).sum();
            float a = 0; //总市值
            for (int i = 0; i < marketValueList.size(); i++) {
                a = Float.parseFloat(marketValueList.get(i).getMarketValue()) + a;
            }
            float b = 0; //总盈亏
            for (int i = 0; i < marketValueList.size(); i++) {
                b = Float.parseFloat(marketValueList.get(i).getMarketValue()) + b;
            }
            String allMoney = SharePrefUtil.getString(AppConfig.ALL_MONEY);
            txtAllMoney.setText(FloatUtils.FloatToString(FloatUtils.StringToFloat(allMoney)+a)); //总资产
            txtAllMarket.setText(a+"");
        }else{
            String allMoney = SharePrefUtil.getString(AppConfig.ALL_MONEY);
            txtAllMoney.setText(allMoney); //总资产
            txtAllMarket.setText("0.00");
        }


    }

    /**
     * 删除
     */
    private void showDeleteStock(long ids){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("确认删除吗？");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FangDbController.getInstance().deleteNote(ids);
            }
        });
        builder.show();
    }

    @Override
    public void initData() {
        super.initData();
        loadingData();
        setHeadData();
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
