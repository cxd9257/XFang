package com.demo.fang.widget;

import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.demo.fang.R;
import com.demo.fang.base.BaseDialogFragment;
import com.demo.fang.base.baseMVP.BasePresenter;
import com.demo.fang.utils.SharePrefUtil;
import com.demo.fang.utils.ShowToast;

import butterknife.BindView;
import butterknife.OnClick;

public class SetMoneyDialogFragment extends BaseDialogFragment {
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected Dialog createDialog() {
        Dialog dialog = new Dialog(getContext());
        if (dialog != null) {
            dialog.setContentView(R.layout.fragment_dialog_set_money);
            dialog.getWindow().setWindowAnimations(R.style.dialog_animations);
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.75), ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        return dialog;
    }

    public static SetMoneyDialogFragment newInstance() {
        SetMoneyDialogFragment fragment = new SetMoneyDialogFragment();
        return fragment;
    }

    @Override
    protected void initViewData() {

    }

    @OnClick(R.id.btn_confirm)
    public void onViewClicked() {
        if (Integer.parseInt(etMoney.getText().toString())<50000){
            ShowToast.showShort(getContext(),"输入的本金也太少了吧");
            return;
        }
        SharePrefUtil.putString("allMoney",etMoney.getText().toString());
    }
}
