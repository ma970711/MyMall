package com.example.mymall.home.fragment;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.mymall.R;
import com.example.mymall.base.BaseFragment;
import com.example.mymall.home.adapter.HomeRecycleAdapter;
import com.example.mymall.home.bean.ResultBeanData;
import com.example.mymall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.w3c.dom.Text;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Request;

import static android.content.ContentValues.TAG;

public class HomeFragment extends BaseFragment {
    private TextView tv_search_home;
    private ImageView ib_top;
    private RecyclerView rvHome;
    private TextView tv_message_home;
    private ResultBeanData.ResultBean resultBean;
    private HomeRecycleAdapter adapter;


    @Override
    public View initView() {
        View view = (View) View.inflate(mContenxt,R.layout.fragment_home,null);
        tv_search_home = view.findViewById(R.id.tv_search_home);
        tv_message_home = view.findViewById(R.id.tv_message_home);
        ib_top = view.findViewById(R.id.ib_top);
        rvHome = view.findViewById(R.id.rv_home);
        initListener();
        return view;
    }

    private void initListener() {
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvHome.scrollToPosition(0);
            }
        });

        //搜索的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContenxt,"搜索功能开发中！",Toast.LENGTH_SHORT).show();
            }
        });

        //消息的监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContenxt,"消息中心正在开发中！",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"主页数据被初始化了");
        //联网请求数据
        getDataFromNet();

    }

    private void getDataFromNet() {
        String url = Constants.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback()
                {
                    /**
                     * 请求失败的回调
                     * @param call
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG,"首页请求失败=="+e.getMessage());

                    }
                    //当联网成功的时候进行回调
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG,"首页请求成功=="+response);
                        //解析数据
                        processData(response);
                    }


                });
    }

    private void processData(String json) {
        ResultBeanData resultBeanData = JSON.parseObject(json,ResultBeanData.class);
        resultBean = resultBeanData.getResult();
//        Log.e(TAG, "解析成功=" + resultBean.getHot_info().get(0).getName());
        if (resultBean != null){
            adapter = new HomeRecycleAdapter(mContenxt,resultBean);
            rvHome.setAdapter(adapter);
            //设置布局管理者
            rvHome.setLayoutManager(new GridLayoutManager(mContenxt,1));
        }else {
            Toast.makeText(mContenxt,"加载失败",Toast.LENGTH_SHORT).show();
        }



    }
}
