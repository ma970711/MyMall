package com.example.mymall.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.mymall.R;
import com.example.mymall.base.BaseFragment;
import com.example.mymall.community.fragment.CommunityFragment;
import com.example.mymall.home.fragment.HomeFragment;
import com.example.mymall.shoppingcart.fragment.ShoppingCartFragment;
import com.example.mymall.type.fragment.TypeFragment;
import com.example.mymall.user.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {
    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.rb_home)
    RadioButton rbHome;
    @Bind(R.id.rb_type)
    RadioButton rbType;
    @Bind(R.id.rb_community)
    RadioButton rbCommunity;
    @Bind(R.id.rb_cart)
    RadioButton rbCart;
    @Bind(R.id.rb_user)
    RadioButton rbUser;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    private ArrayList<BaseFragment> fragments;
    private int position = 0;
    private Fragment tempFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        initListener();


    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_home: //主页
                        position = 0;
                        break;
                    case R.id.rb_type:  //分类
                        position = 1;
                        break;
                    case R.id.rb_community:  //发现
                        position = 2;
                        break;
                    case R.id.rb_cart:  //购物车
                        position = 3;
                        break;
                    case R.id.rb_user:  //个人中心
                        position = 4;
                        break;
                    default:
                        position = 0;
                        break;
                }
                //根据位置取不同的fragment
                BaseFragment baseFragment = getFragment(position);
                /*
                第一参数：上次显示的Fragment
                第二参数：当前显示的Fragment
                 */
                switchFragment(tempFragment,baseFragment);
            }
        });
        rgMain.check(R.id.rb_home);
    }

    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size()>0){
            BaseFragment fragment = fragments.get(position);
            return fragment;
        }
        return null;
    }

    //按顺序添加
    private void initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());

    }
    private void switchFragment(Fragment fromFragment,BaseFragment nextFragment){
        if (tempFragment != nextFragment){
            tempFragment = nextFragment;
            if (nextFragment != null){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()){
                    //隐藏当前Fragment
                    if (fromFragment != null){
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout,nextFragment).commit();
                }else {//隐藏当前Fragment
                    if (fromFragment != null){
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();

                }
            }
        }
    }
}
