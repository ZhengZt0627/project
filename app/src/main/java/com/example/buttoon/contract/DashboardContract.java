package com.example.buttoon.contract;

import com.example.buttoon.base.BaseModel;
import com.example.buttoon.base.BasePersenter;
import com.example.buttoon.base.BaseView;
import com.example.buttoon.bean.ZhuantiBean;
import com.example.buttoon.model.MainSingleModel;
import com.example.buttoon.utils.MainSingleCallBack;

public class DashboardContract {

    public interface DashboardView extends BaseView {
        void onSuccess(ZhuantiBean zhuantiBean);
        void onFail(String error);
    }

    public interface DashboardModel extends BaseModel {
         <T> void dashboardModel(String url, MainSingleCallBack<T>callBack);
    }

    public interface DashboardPresenter extends BaseView{
        void dashboard();
    }

}
