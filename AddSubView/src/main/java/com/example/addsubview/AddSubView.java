package com.example.addsubview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AddSubView extends LinearLayout implements View.OnClickListener {
    private final Context mContext;
    private ImageView iv_sub;
    private ImageView iv_add;
    private TextView tv_value;
    private int value = 1;
    private int minValue = 1;
    private int maxValue = 10;
    public AddSubView(Context context,AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        View.inflate(context,R.layout.add_sub_view,this); //把布局文件实例化，并且加载到AddSubView中
        iv_sub = findViewById(R.id.iv_sub);
        iv_add = findViewById(R.id.iv_add);
        tv_value = findViewById(R.id.tv_value);

        int value = getValue();
        setValue(value);


        //点击事件
        iv_sub.setOnClickListener(this);
        iv_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_sub: //减
                subNumber();
                break;
            case R.id.iv_add: //加
                addNumber();
                break;


        }
    }

    private void addNumber() {
        if (value < maxValue){
            value ++;
        }else {
            Toast.makeText(mContext,"不能再加了",Toast.LENGTH_SHORT).show();
        }
        setValue(value);
        if (onNumberChangeListener != null){
            onNumberChangeListener.onNumberChange(value);
        }
    }

    private void subNumber() {
        if (value > minValue){
            value --;
        }else {
            Toast.makeText(mContext,"不能再减了",Toast.LENGTH_SHORT).show();
        }
        setValue(value);
        if (onNumberChangeListener != null){
            onNumberChangeListener.onNumberChange(value);
        }
    }

    public int getValue() {
        String valueStr = tv_value.getText().toString().trim();
        if (TextUtils.isEmpty(valueStr)){
            value = Integer.parseInt(valueStr);
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tv_value.setText(value+"");
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
    //数量发生变化
    public interface OnNumberChangeListener{
        public void onNumberChange(int value);
    }
    private OnNumberChangeListener onNumberChangeListener;

    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) { //设置数据变化监听
        this.onNumberChangeListener = onNumberChangeListener;
    }
}
