package com.demo.fang.widget;

import android.app.Dialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.fang.R;
import com.demo.fang.base.BaseDialogFragment;
import com.demo.fang.bean.StockDataBean;
import com.demo.fang.bean.StockModle;
import com.demo.fang.database.FangDbController;
import com.demo.fang.ui.stock.StockPresenter;
import com.demo.fang.ui.stock.StockView;
import com.demo.fang.utils.ShowToast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class StockDialogFragment extends BaseDialogFragment<StockPresenter> implements StockView {
    @BindView(R.id.spin_type)
    Spinner spinType;
    @BindView(R.id.et_stock_code)
    EditText etStockCode;
    @BindView(R.id.tv_stock_name)
    TextView tvStockName;
    @BindView(R.id.et_stock_buy_price)
    EditText etStockBuyPrice;
    @BindView(R.id.et_stock_buy_measure)
    EditText etStockBuyMeasure;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    private String stockType;
    private StockDataBean mStockDataBean;
    private String code;
    private String name;
    private String buyPrice;
    private String newPrice;
    private String buyMeasure;
    private  String marketValue;
    private String allMarketValue;
    private String date;
    @Override
    protected StockPresenter createPresenter() {
        return new StockPresenter(this);
    }

    @Override
    protected Dialog createDialog() {
        Dialog dialog = new Dialog(getContext());
        if (dialog != null) {
            dialog.setContentView(R.layout.fragment_dialog_stock);
            dialog.getWindow().setWindowAnimations(R.style.dialog_animations);
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.75), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        return dialog;
    }
    public static StockDialogFragment newInstance(){
        StockDialogFragment fragment = new StockDialogFragment();
        return fragment;
    }
    @Override
    protected void initViewData() {
        spinType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stockType = getResources().getStringArray(R.array.stock_type)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        etStockCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>5){
                    mPresenter.getStockData(stockType+s.toString());
                }else{
                    tvStockName.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onStockSucc(StockModle stockModle) {
        if (stockModle!=null&&stockModle.getResultcode().equals("200")){
            tvStockName.setVisibility(View.VISIBLE);
            tvStockName.setText(stockModle.getResult().get(0).getData().getName());
            etStockBuyPrice.setText(stockModle.getResult().get(0).getData().getNowPri());
            name = stockModle.getResult().get(0).getData().getName();
            newPrice = stockModle.getResult().get(0).getData().getNowPri();
        }else{
            ShowToast.show(getActivity(),"输入错误",Toast.LENGTH_SHORT);
            etStockCode.setText("");
        }
    }


    @Override
    public void loadMoreData() {

    }

    @OnClick(R.id.btn_confirm)
    public void onViewClicked() {
        /**
         * 存取数据库
         */
        buyPrice = etStockBuyPrice.getText().toString();
        buyMeasure = etStockBuyMeasure.getText().toString();
        if (buyPrice.length()==0||buyPrice.isEmpty()){
            ShowToast.show(getContext(),"购买价不能空", Toast.LENGTH_SHORT);
            return;
        }
        if (buyMeasure.length()==0||buyMeasure.isEmpty()){
            ShowToast.show(getContext(),"购买数量不能空", Toast.LENGTH_SHORT);
            return;
        }
        code = stockType+etStockCode.getText().toString();
        marketValue = Float.valueOf(buyMeasure) * Float.valueOf(buyPrice)+"";
        allMarketValue = "0";
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        date = dateFormat.format(now);
        mStockDataBean = new StockDataBean();
        mStockDataBean.setCode(code);
        mStockDataBean.setName(name);
        mStockDataBean.setBuyPrice(buyPrice);
        mStockDataBean.setNewPrice(newPrice);
        mStockDataBean.setBuyMeasure(buyMeasure);
        mStockDataBean.setMarketValue(marketValue);
        mStockDataBean.setAllMarketValue(allMarketValue);
        mStockDataBean.setDate(date);
        if(mStockDataBean!= null){
            saveStockData();
        }
    }
    private void handleDataBase(List<StockDataBean> list){
        List<StockDataBean> newList = new ArrayList<>();
        for (int i=0; i<list.size(); i++){
            StockDataBean info = list.get(i);
            newList.add(info);
        }
    }
    private void saveStockData() {
        FangDbController.getInstance().insertOrReplace(mStockDataBean);
        ShowToast.show(getActivity(),"买入成功",Toast.LENGTH_SHORT);
        dismiss();
    }
}
