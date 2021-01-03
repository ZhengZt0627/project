package com.example.buttoon.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.example.buttoon.R;
import com.example.buttoon.base.BaseFragment;
import com.example.buttoon.bean.ZhuantiBean;
import com.example.buttoon.contract.DashboardContract;
import com.example.buttoon.presenter.DashboardPresemter;

import java.util.ArrayList;

public class DashboardFragment extends BaseFragment<DashboardPresemter> implements DashboardContract.DashboardView {


    private RecyclerView rec;
    private DelegateAdapter delegateAdapter;
    private ArrayList<ZhuantiBean.DataDTO.DataDTOO> list;
    private DashboardAdapter dashboardAdapter;


    protected void initView(View view) {
        rec = view.findViewById(R.id.rec);

        initVLayout();

        initShoe();
    }

    @Override
    protected void initDate() {
        presenter.dashboard();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected DashboardPresemter getPresenter() {
        return new DashboardPresemter();
    }

    private void initShoe() {

        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        singleLayoutHelper.setItemCount(1);
        list = new ArrayList<>();
        dashboardAdapter = new DashboardAdapter(getContext(), list, singleLayoutHelper);
        delegateAdapter.addAdapter(dashboardAdapter);
    }


    private void initVLayout() {
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(getContext());
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        rec.setRecycledViewPool(recycledViewPool);
        recycledViewPool.setMaxRecycledViews(0, 20);
        //设置总体适配器
        delegateAdapter = new DelegateAdapter(virtualLayoutManager);
        rec.setAdapter(delegateAdapter);
        rec.setLayoutManager(virtualLayoutManager);
    }

    @Override
    public void onSuccess(ZhuantiBean zhuantiBean) {
        list.addAll(zhuantiBean.getData().getData());
        dashboardAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String error) {
        Toast.makeText(getContext(), "网络异常", Toast.LENGTH_SHORT).show();
    }
}