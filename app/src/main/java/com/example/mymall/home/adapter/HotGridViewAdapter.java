package com.example.mymall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mymall.R;
import com.example.mymall.home.bean.ResultBeanData;
import com.example.mymall.utils.Constants;

import java.util.List;

class HotGridViewAdapter extends BaseAdapter {
    private final List<ResultBeanData.ResultBean.HotInfoBean> datas;
    Context mContext;


    public HotGridViewAdapter(Context mContext, List<ResultBeanData.ResultBean.HotInfoBean> hot_info) {
        this.mContext = mContext;
        this.datas = hot_info;

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null){
            view =View.inflate(mContext, R.layout.item_hot_grid_view,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_hot = view.findViewById(R.id.iv_hot);
            viewHolder.tv_name = view.findViewById(R.id.tv_name);
            viewHolder.tv_price = view.findViewById(R.id.tv_price);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + datas.get(i).getFigure()).into(viewHolder.iv_hot);
        viewHolder.tv_name.setText(datas.get(i).getName());
        viewHolder.tv_price.setText("￥"+datas.get(i).getCover_price());
        return view;
    }

    static class ViewHolder{
        ImageView iv_hot;
        TextView tv_name;
        TextView tv_price;
    }
}
