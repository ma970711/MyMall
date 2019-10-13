package com.example.mymall.home.adapter;

import android.content.Context;
import android.media.Image;
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

public class ChannelAdapter extends BaseAdapter {
    private final List<ResultBeanData.ResultBean.ChannelInfoBean> datas;
    private Context mContext;


    public ChannelAdapter(Context mContext, List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
        this.mContext = mContext;
        this.datas = channel_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null){
            view = View.inflate(mContext, R.layout.item_channel,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_icon=view.findViewById(R.id.iv_channel);
            viewHolder.tv_title = view.findViewById(R.id.tv_channel);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ResultBeanData.ResultBean.ChannelInfoBean channelInfoBean = datas.get(i);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+channelInfoBean.getImage()).into(viewHolder.iv_icon);
        viewHolder.tv_title.setText(channelInfoBean.getChannel_name());

        return view;
    }

    static class ViewHolder{
        ImageView iv_icon;
        TextView tv_title;
    }
}
