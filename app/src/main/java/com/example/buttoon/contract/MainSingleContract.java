package com.example.buttoon.contract;


import com.example.buttoon.base.BaseModel;
import com.example.buttoon.base.BaseView;
import com.example.buttoon.bean.MainSingleBean;
import com.example.buttoon.utils.MainSingleCallBack;


public class MainSingleContract {
    public interface MainSingleView extends BaseView{
        void onScuess(MainSingleBean mainSingleBean);
        void onFeil(String msg);
    }
    public interface MainSingleModel extends BaseModel {
        <T> void MainSingleModel(String url, MainSingleCallBack<T> callBack);
    }
    public interface MainSinglePresenter extends BaseView{
        void getdata();
    }
}
