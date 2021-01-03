package com.example.buttoon.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.bumptech.glide.Glide;
import com.example.buttoon.R;
import com.example.buttoon.bean.ZhuantiBean;

import java.util.ArrayList;

public class DashboardAdapter extends DelegateAdapter.Adapter {

    private Context context;
    private ArrayList<ZhuantiBean.DataDTO.DataDTOO>list;
    private SingleLayoutHelper singleLayoutHelper;

    public DashboardAdapter(Context context, ArrayList<ZhuantiBean.DataDTO.DataDTOO> list, SingleLayoutHelper singleLayoutHelper) {
        this.context = context;
        this.list = list;
        this.singleLayoutHelper = singleLayoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return singleLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dashboard_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder= (MyViewHolder) holder;
        Glide.with(context).load(list.get(position).getScene_pic_url()).into(viewHolder.iv_dashboard);
        viewHolder.title_dashboard.setText(list.get(position).getTitle());
        viewHolder.subtitle_dashboard.setText(list.get(position).getSubtitle());
        viewHolder.price_dashboard.setText(list.get(position).getPrice_info()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_dashboard;
        private TextView title_dashboard;
        private TextView subtitle_dashboard;
        private TextView price_dashboard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_dashboard = itemView.findViewById(R.id.iv_dashboard);
             title_dashboard = itemView.findViewById(R.id.title_dashboard);
            subtitle_dashboard = itemView.findViewById(R.id.subtitle_dashboard);
            price_dashboard = itemView.findViewById(R.id.price_dashboard);
        }
    }

}
