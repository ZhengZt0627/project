package com.example.buttoon.ui.home;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.example.buttoon.R;
import com.example.buttoon.adapter.BannerAdapter;
import com.example.buttoon.adapter.CateAdapter;
import com.example.buttoon.adapter.FindAdapter;
import com.example.buttoon.adapter.HotGoodsAdapter;
import com.example.buttoon.adapter.MainSingleAdapter;
import com.example.buttoon.adapter.MonAdapter;
import com.example.buttoon.adapter.NewGoodsAdapter;
import com.example.buttoon.adapter.NextSingleAdapter;
import com.example.buttoon.adapter.TextAdapter;
import com.example.buttoon.adapter.TopallAdapter;
import com.example.buttoon.adapter.ZtAdapter;
import com.example.buttoon.adapter.ploAdapter;
import com.example.buttoon.base.BaseFragment;
import com.example.buttoon.bean.MainSingleBean;
import com.example.buttoon.contract.MainSingleContract;
import com.example.buttoon.presenter.MainSinglePresenter;
import com.example.buttoon.ui.dashboard.DashboardFragment;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment<MainSinglePresenter> implements MainSingleContract.MainSingleView{

    private RecyclerView rlv;
    private VirtualLayoutManager vmanager;
    private ArrayList<MainSingleBean.DataDTO.ChannelDTO> singlelist;
    private MainSingleAdapter mainSingleAdapter;
    private ArrayList<MainSingleBean.DataDTO.BrandListDTO> nextlist;
    private NextSingleAdapter nextSingleAdapter;
    private TextAdapter textAdapter;
    private ArrayList<MainSingleBean.DataDTO.BannerDTO> bannerlist;
    private BannerAdapter bannerAdapter;
    private FindAdapter findAdapter;
    private MonAdapter monAdapter;
    private ArrayList<MainSingleBean.DataDTO.NewGoodsListDTO> goodslist;
    private NewGoodsAdapter newGoodsAdapter;
    private ArrayList<MainSingleBean.DataDTO.HotGoodsListDTO> hotlist;
    private DelegateAdapter delegateAdapter;
    private HotGoodsAdapter hotGoodsAdapter;
    private ArrayList<MainSingleBean.DataDTO.TopicListDTO> toplist;
    private TopallAdapter topallAdapter;
    private ZtAdapter ztAdapter;
    private ArrayList<MainSingleBean.DataDTO.CategoryListDTO> catList;
    private CateAdapter cateAdapter;
    private com.example.buttoon.adapter.ploAdapter ploAdapter;

    protected void initView(View view) {
        rlv = view.findViewById(R.id.rlv_main);
        //创建Vlayout对象
        initVmanager();
        //搜索框
        initSearch();
        //banner
        initBanner();
        //专题行
        initZhuanti();
        //对应的文字和网格
        initNext();
        //滑动
        initSColl();
        //居家开始 数据展示
        initShow();
        //布局管理器绑定
        initsetmanager();
    }
    private void initShow() {
        catList = new ArrayList<>();
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        singleLayoutHelper.setItemCount(1);
        cateAdapter = new CateAdapter(singleLayoutHelper,getActivity(),catList);
        delegateAdapter.addAdapter(cateAdapter);


    }
    @SuppressLint("ResourceType")
    private void initSColl() {
        //定义文字：专题推荐
        SingleLayoutHelper singleLayoutHelper6 = new SingleLayoutHelper();
        // 公共属性
        singleLayoutHelper6.setItemCount(1);// 设置布局里Item个数
        ztAdapter = new ZtAdapter(singleLayoutHelper6,getActivity());
        delegateAdapter.addAdapter(ztAdapter);
        toplist = new ArrayList<>();
        SingleLayoutHelper singleLayoutHelpers = new SingleLayoutHelper();
        // 公共属性
        singleLayoutHelpers.setItemCount(1);// 设置布局里Item个数
        //singleLayoutHelpers.setPadding(20,20,20,20);

        topallAdapter = new TopallAdapter(singleLayoutHelpers,getActivity(),toplist);
        delegateAdapter.addAdapter(topallAdapter);
        topallAdapter.setMyclick(new TopallAdapter.Myclick() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(), "hahha", Toast.LENGTH_SHORT).show();
              Intent it= new Intent(getActivity(),DashboardFragment.class);
               startActivity(it);
            }
        });

    }
    private void initSearch() {
        SingleLayoutHelper singleLayoutHelper2 = new SingleLayoutHelper();
        // 公共属性
        // singleLayoutHelper2.setMargin(10, 10, 10, 10);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        singleLayoutHelper2.setItemCount(1);// 设置布局里Item个数
        findAdapter = new FindAdapter(singleLayoutHelper2, getActivity());
        delegateAdapter.addAdapter(findAdapter);

    }
    private void initBanner() {
        bannerlist = new ArrayList<>();
        //定义文字
        SingleLayoutHelper singleLayoutHelper1 = new SingleLayoutHelper();
        // 公共属性
        singleLayoutHelper1.setItemCount(1);// 设置布局里Item个数

        bannerAdapter = new BannerAdapter(bannerlist,getActivity(),singleLayoutHelper1);
        delegateAdapter.addAdapter(bannerAdapter);
    }

    private void initZhuanti() {

        singlelist = new ArrayList<>();
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(5);
        // 在构造函数设置每行的网格个数
        // 公共属性
        gridLayoutHelper.setItemCount(5);// 设置布局里Item个数
        gridLayoutHelper.setSpanCount(5);// 设置每行多少个网格
        mainSingleAdapter = new MainSingleAdapter(gridLayoutHelper, getActivity(), singlelist);
        //设置适配器2
        delegateAdapter.addAdapter(mainSingleAdapter);

    }

    private void initNext() {
        //定义文字
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
        // 公共属性
        singleLayoutHelper.setItemCount(1);// 设置布局里Item个数
        textAdapter = new TextAdapter(singleLayoutHelper, getActivity());
        delegateAdapter.addAdapter(textAdapter);
        //直供
        nextlist = new ArrayList<>();
        ///< 网格布局 - 构造中传入相应的列的数量
        GridLayoutHelper gridLayoutHelper1 = new GridLayoutHelper(4);
        gridLayoutHelper1.setSpanCount(2);
        gridLayoutHelper1.setAutoExpand(true);
        nextSingleAdapter = new NextSingleAdapter(nextlist, gridLayoutHelper1, getActivity());
        //设置适配器2
        delegateAdapter.addAdapter(nextSingleAdapter);
        //定义文字
        SingleLayoutHelper singleLayoutHelper4 = new SingleLayoutHelper();
        // 公共属性
        singleLayoutHelper4.setItemCount(1);// 设置布局里Item个数
        monAdapter = new MonAdapter(singleLayoutHelper4, getActivity());
        delegateAdapter.addAdapter(monAdapter);
        //周一周日网格：
        goodslist = new ArrayList<>();
        ///< 网格布局 - 构造中传入相应的列的数量
        GridLayoutHelper gridLayoutHelper2 = new GridLayoutHelper(4);
        //gridLayoutHelper1.setMarginTop(30);
        gridLayoutHelper2.setSpanCount(2);
        gridLayoutHelper2.setAutoExpand(true);
        newGoodsAdapter = new NewGoodsAdapter(goodslist, gridLayoutHelper2, getActivity());
        delegateAdapter.addAdapter(newGoodsAdapter);
        //定义文字：人气推荐
        SingleLayoutHelper singleLayoutHelper5 = new SingleLayoutHelper();
        // 公共属性
        singleLayoutHelper5.setItemCount(1);// 设置布局里Item个数
        ploAdapter = new ploAdapter(singleLayoutHelper5,getActivity());
        delegateAdapter.addAdapter(ploAdapter);
        //人气推荐网格：
        hotlist = new ArrayList<>();
        GridLayoutHelper gridLayoutHelper3 = new GridLayoutHelper(3);
        gridLayoutHelper3.setSpanCount(1);
        gridLayoutHelper3.setItemCount(4);
        hotGoodsAdapter = new HotGoodsAdapter(gridLayoutHelper3,getActivity(),hotlist);
        delegateAdapter.addAdapter(hotGoodsAdapter);

    }

    private void initVmanager() {
        vmanager = new VirtualLayoutManager(getActivity());

        RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool();
        rlv.setRecycledViewPool(pool);
        pool.setMaxRecycledViews(0, 20);
        //设置总体适配器
        delegateAdapter = new DelegateAdapter(vmanager);
    }

    private void initsetmanager() {
        rlv.setAdapter(delegateAdapter);
        rlv.setLayoutManager(vmanager);
    }
    @Override
    protected void initDate() {
        presenter.getdata();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected MainSinglePresenter getPresenter() {
        return new MainSinglePresenter();
    }

    //第层专题选择数据请求
    @Override
    public void onScuess(MainSingleBean mainSingleBean) {
        singlelist.addAll(mainSingleBean.getData().getChannel());
        nextlist.addAll(mainSingleBean.getData().getBrandList());
        bannerlist.addAll(mainSingleBean.getData().getBanner());
        goodslist.addAll(mainSingleBean.getData().getNewGoodsList());
        hotlist.addAll(mainSingleBean.getData().getHotGoodsList());
        toplist.addAll(mainSingleBean.getData().getTopicList());
        catList.addAll(mainSingleBean.getData().getCategoryList());
        cateAdapter.notifyDataSetChanged();
        topallAdapter.notifyDataSetChanged();
        ztAdapter.notifyDataSetChanged();
        bannerAdapter.notifyDataSetChanged();
        nextSingleAdapter.notifyDataSetChanged();
        mainSingleAdapter.notifyDataSetChanged();
        findAdapter.notifyDataSetChanged();
        monAdapter.notifyDataSetChanged();
        newGoodsAdapter.notifyDataSetChanged();
        ploAdapter.notifyDataSetChanged();
        hotGoodsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFeil(String msg) {
    }

}