package com.learntoslip.language.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import com.learntoslip.language.R;
import com.learntoslip.language.thread.IndexListOpera;
import com.learntoslip.language.thread.IndexListRun;
import com.learntoslip.language.thread.IndexSelectRun;

/**
 * Created by Administrator on 2017/5/25.
 */
public class IndexFragment extends Fragment implements AbsListView.OnScrollListener {
    private  IndexListOpera listOpera;
    // 最后可见条目的索引
    private int lastVisibleIndex;
    private SwipeRefreshLayout mSwipeLayout;
    private static final int REFRESH_COMPLETE = 0X110;
    private Handler mHandler = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
            switch (msg.what)
            {
                case REFRESH_COMPLETE:
                    listOpera.setPageNum(1);
                    if(listOpera.getAllword()!=null){
                        listOpera.getAllword().clear();
                    }

                    setList();
                    mSwipeLayout.setRefreshing(false);
                    break;

            }
        };
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //实例化整个窗口布局
        View fragView= inflater.inflate(R.layout.word_list , container, false);
        mSwipeLayout = (SwipeRefreshLayout) fragView.findViewById(R.id.word_list_swipe_ly);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            public void onRefresh()
            {
                // Log.e("xxx", Thread.currentThread().getName());
                // UI Thread

                mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);

            }
        });
        listOpera=new IndexListOpera();
        listOpera.setContext(getContext());
        listOpera.setActivity(getActivity());
        listOpera.setFragView(fragView);
        listOpera.setInflater(inflater);
        listOpera.setFrament(this);

        listOpera.initListView();
        listOpera.initSpinnerView();
        listOpera.initMoreView();
        listOpera.initAdapter();
        listOpera.setShowMore(true);
        //开始加载下拉框
        setSpinner();

        return fragView;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        // 计算最后可见条目的索引
        lastVisibleIndex = firstVisibleItem + visibleItemCount-2;

        // 所有的条目已经和最大条数相等，则移除底部的View
//        if (totalItemCount == MaxDateNum + 1) {
//            listView.removeFooterView(moreView);
//            Toast.makeText(getContext(), "数据全部加载完成，没有更多数据！", Toast.LENGTH_LONG).show();
//        }

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // 滑到底部后自动加载，判断listview已经停止滚动并且最后可视的条目等于adapter的条目
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                && lastVisibleIndex == listOpera.getWordAdapter().getCount()) {
            // 当滑到底部时自动加载
            listOpera.getPg().setVisibility(View.VISIBLE);
            //listOpera.getBt().setVisibility(View.GONE);
            setList();

        }

    }



    /**
     * 设置下拉框
     */
    private void setSpinner()
    {
        IndexSelectRun indexSelect=new IndexSelectRun();
        indexSelect.setListOpera(listOpera);
        new Thread(indexSelect).start();
    }
    /**
     * 设置下拉框
     */
    private void setList()
    {
        IndexListRun indexListRun=new IndexListRun();
        indexListRun.setListOpera(listOpera);
        new Thread(indexListRun).start();
    }
}
