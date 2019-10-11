package com.example.mymall.shoppingcart.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.mymall.base.BaseFragment;

import static android.content.ContentValues.TAG;

public class ShoppingCartFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        textView = new TextView(mContenxt);

        Log.e(TAG,"主页视图被初始化了");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return null;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"主页数据被初始化了");
        textView.setText("初始化数据");
    }
}
