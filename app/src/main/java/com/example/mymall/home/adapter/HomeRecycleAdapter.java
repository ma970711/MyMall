package com.example.mymall.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mymall.R;
import com.example.mymall.home.bean.ResultBeanData;
import com.example.mymall.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;
import com.zhy.magicviewpager.transformer.AlphaPageTransformer;
import com.zhy.magicviewpager.transformer.RotateDownPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class HomeRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * 五种类型
     */
    public static final int BANNER = 0; //横幅广告
    public static final int CHANNEL = 1;//频道
    public static final int ACT = 2; //活动
    public static final int SECKILL = 3;//秒杀
    public static final int RECOMMEND = 4;//推荐
    public static final int HOT = 5; //热卖
    /**
     * 当前类型
     */
    public int currentType = BANNER;
    /**
     * data object
     */
    private ResultBeanData.ResultBean resultBean;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ChannelViewHolder channelViewHolder;
    private ChannelAdapter adapter;
    private ActViewHolder actViewHolder;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == BANNER){
            return new BannerViewHolder(mContext,mLayoutInflater.inflate(R.layout.banner_viewpager,null));
        }else if (i == CHANNEL){
            return new ChannelViewHolder(mContext,mLayoutInflater.inflate(R.layout.channel_item,null));
        }else if (i == ACT){
            return new ActViewHolder(mContext,mLayoutInflater.inflate(R.layout.act_item,null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == BANNER){
            BannerViewHolder bannerViewHolder = (BannerViewHolder) viewHolder;
            bannerViewHolder.setData(resultBean.getBanner_info());
        }else if (getItemViewType(i) == CHANNEL){
            channelViewHolder = (ChannelViewHolder) viewHolder;
            channelViewHolder.setData(resultBean.getChannel_info());
        }else if (getItemViewType(i) == ACT){
            actViewHolder = (ActViewHolder) viewHolder;
            actViewHolder.setData(resultBean.getAct_info());
        }

    }

    @Override
    public int getItemCount() {
        return 3; //横幅广告为1
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }

    public HomeRecycleAdapter(Context mContext, ResultBeanData.ResultBean resultBean){
        this.mContext = mContext;
        this.resultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    class BannerViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private Banner banner;

        public BannerViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            this.banner = itemView.findViewById(R.id.banner);
        }

        public void setData(List<ResultBeanData.ResultBean.BannerInfoBean> banner_info) {
            //设置banner的数据
            List<String> imagesUrl = new ArrayList<>(); //得到图片地址
            for (int i=0;i<banner_info.size();i++){
                String imageUrl = banner_info.get(i).getImage();
                imagesUrl.add(imageUrl);
            }
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            banner.setBannerAnimation(Transformer.Accordion);
            banner.setImages(imagesUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    //联网请求图片
                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE+url).into(view);

                }
            });
            //设置item的点击事件
            banner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext,"position="+position,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private class ChannelViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private GridView gv_channel;
        public ChannelViewHolder(Context mContext, View view) {
            super(view);
            this.mContext = mContext;
            gv_channel = view.findViewById(R.id.gv_channel);
            initListener();
        }

        private void initListener() {
            gv_channel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(mContext,"当前为"+resultBean.getChannel_info().get(i).getChannel_name(),Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setData(List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
            //得到数据
            //设置适配器
            adapter = new ChannelAdapter(mContext,channel_info);
            gv_channel.setAdapter(adapter);
        }
    }

    private class ActViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private ViewPager act_viewpager;
        public ActViewHolder(Context mContext, View itemView) {
            super(itemView);
            this.mContext = mContext;
            act_viewpager = itemView.findViewById(R.id.act_viewpager);

        }

        public void setData(final List<ResultBeanData.ResultBean.ActInfoBean> act_info) {
            //set adapter
            act_viewpager.setPageMargin(20);
            act_viewpager.setPageTransformer(true,new AlphaPageTransformer());
            act_viewpager.setAdapter(new PagerAdapter() {
                @Override
                public int getCount() {
                    return act_info.size();
                }

                /**
                 *
                 * @param view 页面
                 * @param object instantiateItem方法返回的值
                 * @return
                 */
                @Override
                public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                    return view==object;
                }

                /**
                 *
                 * @param container ViewPager
                 * @param position  对应页面的位置
                 * @return
                 */
                @NonNull
                @Override
                public Object instantiateItem(@NonNull ViewGroup container, final int position) {
                    ImageView imageView = new ImageView(mContext);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    //添加到容器
                    Glide.with(mContext).load(Constants.BASE_URL_IMAGE+act_info.get(position).getIcon_url()).into(imageView);
                    container.addView(imageView);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext,act_info.get(position).getName(),Toast.LENGTH_SHORT).show();
                        }
                    });

                    return imageView;
                }

                @Override
                public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                    container.removeView((View) object);
                }
            });
        }
    }
}
