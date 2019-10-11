package com.example.mymall.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作用：基类Fragment
 *
 首页：HomeFragment
 分类：TypeFragment
 发现：CommunityFragment
 购物车：ShoppingCartFragment
 用户中心：UserFragemnt
 */
public abstract class BaseFragment extends Fragment {
    protected Context mContenxt;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContenxt = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }
    public abstract View initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    //当子类需要联网请求数据的时候，可以重写该方法,在该方法中联网请求
    public void initData(){};
}
