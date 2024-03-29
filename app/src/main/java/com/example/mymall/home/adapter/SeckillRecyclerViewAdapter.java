package com.example.mymall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mymall.R;
import com.example.mymall.home.bean.ResultBeanData;
import com.example.mymall.utils.Constants;

import java.util.List;

/**
 *  秒杀的适配器
 */
class SeckillRecyclerViewAdapter extends RecyclerView.Adapter<SeckillRecyclerViewAdapter.ViewHolder> {


    private final Context mContext;
    private final List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list;

    public SeckillRecyclerViewAdapter(Context mContext, List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list) {
        this.mContext = mContext;
        this.list =list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_seckill,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //1.根据位置得到对应的数据
        final ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = list.get(position);

        //2.绑定数据
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + listBean.getFigure()).into(holder.iv_figure);
        holder.tv_cover_price.setText(listBean.getCover_price());
        holder.tv_origin_price.setText(listBean.getOrigin_price());
      /*  holder.iv_figure.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(mContext,"当前"+listBean.getName()+"的原价为"+listBean.getOrigin_price(),Toast.LENGTH_LONG).show();
                return true;
            }
        });*/


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;


        public ViewHolder(View itemView) {
            super(itemView);
            iv_figure = itemView.findViewById(R.id.iv_figure);
            tv_cover_price = itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price = itemView.findViewById(R.id.tv_origin_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onSeckillRecyclerview !=null){
                        onSeckillRecyclerview.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    /**
     * 监听器
     */
    public interface OnSeckillRecyclerview{
        public void onItemClick(int position);
    }
    private OnSeckillRecyclerview onSeckillRecyclerview;

    public void setOnSeckillRecyclerview(OnSeckillRecyclerview onSeckillRecyclerview){
        this.onSeckillRecyclerview = onSeckillRecyclerview;
    }
}
