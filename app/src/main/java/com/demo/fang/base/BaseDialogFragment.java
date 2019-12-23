package com.demo.fang.base;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.demo.fang.base.baseMVP.BaseModel;
import com.demo.fang.base.baseMVP.BasePresenter;
import com.demo.fang.base.baseMVP.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseDialogFragment<P extends BasePresenter> extends DialogFragment implements BaseView {
    private Unbinder unbinder;
    protected P mPresenter;

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onErrorCode(BaseModel model) {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    protected abstract P createPresenter();
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewData();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = createDialog();
        unbinder = ButterKnife.bind(this, dialog);
        mPresenter = createPresenter();
        return dialog;
    }
    protected abstract Dialog createDialog();
    protected abstract void initViewData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter !=null){
            mPresenter.detachView();
        }
        try {
            if (unbinder!=null){
                unbinder.unbind();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
