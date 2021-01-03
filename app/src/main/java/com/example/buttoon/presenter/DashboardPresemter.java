package com.example.buttoon.presenter;

import com.example.buttoon.base.BasePersenter;
import com.example.buttoon.bean.ZhuantiBean;
import com.example.buttoon.contract.DashboardContract;
import com.example.buttoon.model.DashboardModel;
import com.example.buttoon.utils.MainSingleCallBack;
import com.example.buttoon.utils.URLcontract;

public class DashboardPresemter extends BasePersenter<DashboardContract.DashboardView,DashboardContract.DashboardModel> implements DashboardContract.DashboardPresenter {
    @Override
    protected DashboardContract.DashboardModel getiModel() {
        return new DashboardModel(this);
    }

    @Override
    public void dashboard() {
        iModel.dashboardModel(URLcontract.DashboardUrl, new MainSingleCallBack<ZhuantiBean>() {
            @Override
            public void onScuess(ZhuantiBean zhuantiBean) {
                iView.onSuccess(zhuantiBean);
            }

            @Override
            public void onFeil(String msg) {
                iView.onFail(msg);
            }
        });
    }
}
