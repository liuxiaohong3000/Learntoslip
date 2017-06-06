package com.learntoslip.language.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import com.igexin.sdk.PushManager;
import com.learntoslip.language.fragment.IndexFragment;
import com.learntoslip.language.fragment.MessageFragment;
import com.learntoslip.language.fragment.MindFragment;
import com.learntoslip.language.R;

/**
 *
 * @author zqy
 *
 */
public class MainActivity extends FragmentActivity {
    /**
     * FragmentTabhost
     */
    private FragmentTabHost mTabHost;

    /**
     * 布局填充器
     *
     */
    private LayoutInflater mLayoutInflater;

    /**
     * Fragment数组界面
     *
     */
    private Class mFragmentArray[] = { IndexFragment.class, MessageFragment.class,
            MindFragment.class};
    /**
     * 存放图片数组
     *
     */
    private int mImageArray[] = { R.drawable.tab_home_btn,
            R.drawable.tab_message_btn, R.drawable.tab_selfinfo_btn};

    /**
     * 选修卡文字
     *
     */
    private String mTextArray[] = { "首页", "提醒", "我的" };
    /**
     *
     *
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        //配置个推
        PushManager.getInstance().initialize(this.getApplicationContext(), com.learntoslip.language.service.webservice.gt.WordPushService.class);
        //为第三方自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), com.learntoslip.language.service.webservice.gt.WordIntentService.class);
    }

    /**
     * 初始化组件
     */
    private void initView() {
        mLayoutInflater = LayoutInflater.from(this);

        // 找到TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        // 得到fragment的个数
        int count = mFragmentArray.length;
        for (int i = 0; i < count; i++) {
            // 给每个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i])
                    .setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, mFragmentArray[i], null);
            // 设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.drawable.selector_tab_background);
        }
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
//                mTabHost.setCurrentTabByTag(tabId);
                upDateTab(mTabHost);
            }
        });
    }

    /**
     *
     * 给每个Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = mLayoutInflater.inflate(R.layout.tab_item, null);
        //ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        //imageView.setImageResource(mImageArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextArray[index]);
        if(index==0){
            textView.setTextColor(this.getResources().getColor(R.color.app_blue));
        }

        return view;
    }
    private void upDateTab(FragmentTabHost mTabHost) {
        for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
            TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(R.id.textview);
            if (mTabHost.getCurrentTab() == i) {//选中
                tv.setTextColor(this.getResources().getColor(R.color.app_blue));
            } else {//不选中
                tv.setTextColor(this.getResources().getColor(R.color.app_orange));
            }
        }


    }
}
