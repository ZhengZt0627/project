package com.example.buttoon.model;

import com.example.buttoon.contract.DashboardContract;
import com.example.buttoon.utils.DashboardUtils;
import com.example.buttoon.utils.MainSingleCallBack;
import com.example.buttoon.utils.RetrofitUtils;

public class DashboardModel implements DashboardContract.DashboardModel {

    private DashboardContract.DashboardPresenter presenter;

    public DashboardModel(DashboardContract.DashboardPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public <T> void dashboardModel(String url, MainSingleCallBack<T> callBack) {
       DashboardUtils.getDashboardUtils().get(url,callBack);
    }
}
