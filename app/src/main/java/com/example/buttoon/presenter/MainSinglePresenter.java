package com.example.buttoon.presenter;

import com.example.buttoon.base.BasePersenter;
import com.example.buttoon.bean.MainSingleBean;
import com.example.buttoon.contract.MainSingleContract;
import com.example.buttoon.model.MainSingleModel;
import com.example.buttoon.utils.MainSingleCallBack;
import com.example.buttoon.utils.URLcontract;
public class MainSinglePresenter extends BasePersenter<MainSingleContract.MainSingleView,MainSingleContract.MainSingleModel> implements MainSingleContract.MainSinglePresenter {

    @Override
    public void getdata() {
        iModel.MainSingleModel(URLcontract.Mainsingle_url, new MainSingleCallBack<MainSingleBean>() {
            @Override
            public void onScuess(MainSingleBean mainSingleBean) {
                iView.onScuess(mainSingleBean);
            }

            @Override
            public void onFeil(String msg) {
                iView.onFeil(msg);
            }
        });
    }

    @Override
    protected MainSingleContract.MainSingleModel getiModel() {
        return new MainSingleModel(this);
    }
}
