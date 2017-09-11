package com.learntoslip.language.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.learntoslip.language.R;
import com.learntoslip.language.adapter.UserWordAdapter;
import com.learntoslip.language.config.MindConfig;
import com.learntoslip.language.model.UserWord;
import com.learntoslip.language.service.busservice.UserWordService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */
public class MessageFragment extends Fragment{
    private View fragView;
    private ListView listView;
    private View moreView;
    private View headerView;
    private int pageNum=1;
    private int pageSize=15;
    private SwipeRefreshLayout mSwipeLayout;
    //适配器
    private UserWordAdapter userWordAdapter;

    // 最后可见条目的索引
    private int lastVisibleIndex;

    //private Button bt;
    private ProgressBar pg;
    private static final int REFRESH_COMPLETE = 0X110;
    private List<UserWord> allword;
    private Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            switch (msg.what)
            {
                case REFRESH_COMPLETE:
                    allword.clear();
                    pageNum=1;
                    new Thread(networkTask).start();
                    mSwipeLayout.setRefreshing(false);
                    break;

            }
        };
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        //实例化数据list
        pageNum=1;
        allword=new ArrayList<UserWord>();

        //实例化整个窗口布局
        fragView= inflater.inflate(R.layout.user_word_list , container, false);

        mSwipeLayout = (SwipeRefreshLayout) fragView.findViewById(R.id.id_swipe_ly);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            public void onRefresh()
            {
                // Log.e("xxx", Thread.currentThread().getName());
                // UI Thread

                mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);

            }
        });
        //实例化列表布局
        listView = (ListView)fragView.findViewById(R.id.userWordListView);

        // 实例化底部布局
        moreView = inflater.inflate(R.layout.word_list_more_data, null);
        //bt = (Button) moreView.findViewById(R.id.bt_load);
        pg = (ProgressBar) moreView.findViewById(R.id.pg);

        // 实例化header布局
        headerView = inflater.inflate(R.layout.user_word_list_header, null);
        //默认隐藏加载按钮
        //bt.setVisibility(View.INVISIBLE);

        // UI界面的更新等相关操作
        userWordAdapter = new UserWordAdapter(getActivity(), R.layout.user_word_list_item, allword);

        //设置列表布局顶部、底部和数据处理器
        listView.addHeaderView(headerView);
        listView.addFooterView(moreView);
        listView.setAdapter(userWordAdapter);

//        // 绑定滚动监听器
        listView.setOnScrollListener(new AbsListView.OnScrollListener(){
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                // 计算最后可见条目的索引
                lastVisibleIndex = firstVisibleItem + visibleItemCount - 2;

                // 所有的条目已经和最大条数相等，则移除底部的View
//        if (totalItemCount == MaxDateNum + 1) {
//            listView.removeFooterView(moreView);
//            Toast.makeText(getContext(), "数据全部加载完成，没有更多数据！", Toast.LENGTH_LONG).show();
//        }

            }

            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // 滑到底部后自动加载，判断listview已经停止滚动并且最后可视的条目等于adapter的条目
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                        && lastVisibleIndex == userWordAdapter.getCount()) {
                    // 当滑到底部时自动加载
                    pg.setVisibility(View.VISIBLE);
                    //bt.setVisibility(View.GONE);
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            new Thread(networkTask).start();
                        }

                    }, 2000);

                }

            }
        });



        //开始加载数据
        new Thread(networkTask).start();

        return fragView;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
            if(val==null){
                //无数据删除底部布局
                //listView.removeFooterView(moreView);
                Toast.makeText(getContext(), "数据全部加载完成，没有更多数据！", Toast.LENGTH_LONG).show();
                return;
            }
            List<UserWord> templist=UserWordService.convertUserWords(val);

            //如果当前页数据小于每页数据，说明已无数据
            if(templist.size()<pageSize){
                //无数据删除底部布局
                //listView.removeFooterView(moreView);
                Toast.makeText(getContext(), "数据全部加载完成，没有更多数据！", Toast.LENGTH_LONG).show();
            }
            pageNum++;
            //缓存数据，并通知listView刷新数据
            allword.addAll(templist);
            userWordAdapter.notifyDataSetChanged();

            //显示加载按钮
            //bt.setVisibility(View.VISIBLE);
            pg.setVisibility(View.GONE);

        }
    };

    /**
     * 网络操作相关的子线程
     */
    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // TODO
            // 在这里进行 http request.网络请求相关操作
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value", UserWordService.getUserWords(MindConfig.userId,null,pageNum));
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };
}
