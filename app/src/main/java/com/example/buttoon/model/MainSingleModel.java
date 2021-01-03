package com.example.buttoon.model;

import com.example.buttoon.contract.MainSingleContract;
import com.example.buttoon.presenter.MainSinglePresenter;
import com.example.buttoon.utils.MainSingleCallBack;
import com.example.buttoon.utils.RetrofitUtils;

public class MainSingleModel implements MainSingleContract.MainSingleModel {
    private MainSingleContract.MainSinglePresenter presenter;

    public MainSingleModel(MainSinglePresenter mainSinglePresenter) {
        this.presenter = presenter;
    }

    @Override
    public <T> void MainSingleModel(String url, MainSingleCallBack<T> callBack) {
        RetrofitUtils.getInstance().get(url,callBack);

    }
}
