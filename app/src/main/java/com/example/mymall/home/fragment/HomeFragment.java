package com.example.mymall.home.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.mymall.base.BaseFragment;

import static android.content.ContentValues.TAG;

public class HomeFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        textView = new TextView(mContenxt);

        Log.e(TAG,"主页视图被初始化了");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"主页数据被初始化了");
        textView.setText("初始化首页");
    }
}
