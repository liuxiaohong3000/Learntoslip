package com.learntoslip.language.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.learntoslip.language.R;
import com.learntoslip.language.adapter.WordAdapter;
import com.learntoslip.language.model.Word;
import com.learntoslip.language.service.busservice.WordService;

import java.util.ArrayList;
import java.util.List;

import static com.learntoslip.language.R.layout.word_list_header;

/**
 * Created by Administrator on 2017/5/25.
 */
public class IndexFragment extends Fragment implements AbsListView.OnScrollListener {
    private View fragView;
    private ListView listView;
    private View moreView;
    private View headerView;
    private int pageNum=1;
    private int pageSize=15;
    //适配器
    private WordAdapter wordAdapter;

    // 最后可见条目的索引
    private int lastVisibleIndex;

    private  Button bt;
    private ProgressBar pg;

    private List<Word> allword;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //实例化数据list
        pageNum=1;
        allword=new ArrayList<Word>();

        //实例化整个窗口布局
        fragView= inflater.inflate(R.layout.word_list , container, false);

        //实例化列表布局
        listView = (ListView)fragView.findViewById(R.id.wordListView);

        // 实例化底部布局
        moreView = inflater.inflate(R.layout.word_list_more_data, null);
        bt = (Button) moreView.findViewById(R.id.bt_load);
        pg = (ProgressBar) moreView.findViewById(R.id.pg);

        // 实例化header布局
        headerView = inflater.inflate(word_list_header, null);

        //默认隐藏加载按钮
        bt.setVisibility(View.INVISIBLE);

        // UI界面的更新等相关操作
        wordAdapter = new WordAdapter(getActivity(), R.layout.word_list_item, allword);

        //设置列表布局的底部和数据处理器
        listView.addHeaderView(headerView);
        listView.addFooterView(moreView);
        listView.setAdapter(wordAdapter);

        // 绑定滚动监听器
        listView.setOnScrollListener(this);

        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pg.setVisibility(View.VISIBLE);// 将进度条可见
                bt.setVisibility(View.GONE);// 按钮不可见

                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        new Thread(networkTask).start();// 加载更多数据
                        bt.setVisibility(View.VISIBLE);
                        pg.setVisibility(View.GONE);
                    }

                }, 1000);
            }
        });
        setSpinner();
        //开始加载数据
        new Thread(networkTask).start();

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
                && lastVisibleIndex == wordAdapter.getCount()) {
            // 当滑到底部时自动加载
             pg.setVisibility(View.VISIBLE);
             bt.setVisibility(View.GONE);
             handler.postDelayed(new Runnable() {

                 @Override
                 public void run() {
                     new Thread(networkTask).start();
                 }

             }, 2000);

        }

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
                listView.removeFooterView(moreView);
                Toast.makeText(getContext(), "数据全部加载完成，没有更多数据！", Toast.LENGTH_LONG).show();
                return;
            }
            List<Word> templist=WordService.convertWords(val);

            //如果当前页数据小于每页数据，说明已无数据
            if(templist.size()<pageSize){
                //无数据删除底部布局
                listView.removeFooterView(moreView);
                Toast.makeText(getContext(), "数据全部加载完成，没有更多数据！", Toast.LENGTH_LONG).show();
            }else{
                pageNum++;
            }

            //缓存数据，并通知listView刷新数据
            allword.addAll(templist);
            wordAdapter.notifyDataSetChanged();

            //显示加载按钮
            bt.setVisibility(View.VISIBLE);
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
            data.putString("value", WordService.listWord(pageNum));
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };

    private Spinner provinceSpinner = null;  //省级（省、直辖市）
    private Spinner citySpinner = null;     //地级市
    private Spinner countySpinner = null;    //县级（区、县、县级市）
    ArrayAdapter<String> provinceAdapter = null;  //省级适配器
    ArrayAdapter<String> cityAdapter = null;    //地级适配器
    ArrayAdapter<String> countyAdapter = null;    //县级适配器
    static int provincePosition = 3;


    //省级选项值
    private String[] province = new String[] {"北京","上海","天津","广东"};//,"重庆","黑龙江","江苏","山东","浙江","香港","澳门"};
    //地级选项值
    private String[][] city = new String[][]
            {
                    { "东城区", "西城区", "崇文区", "宣武区", "朝阳区", "海淀区", "丰台区", "石景山区", "门头沟区",
                            "房山区", "通州区", "顺义区", "大兴区", "昌平区", "平谷区", "怀柔区", "密云县",
                            "延庆县" },
                    { "长宁区", "静安区", "普陀区", "闸北区", "虹口区" },
                    { "和平区", "河东区", "河西区", "南开区", "河北区", "红桥区", "塘沽区", "汉沽区", "大港区",
                            "东丽区" },
                    { "广州", "深圳", "韶关" // ,"珠海","汕头","佛山","湛江","肇庆","江门","茂名","惠州","梅州",
                            // "汕尾","河源","阳江","清远","东莞","中山","潮州","揭阳","云浮"
                    }
            };

    //县级选项值
    private String[][][] county = new String[][][]
            {
                    {   //北京
                            {"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},
                            {"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"}
                    },
                    {    //上海
                            {"无"},{"无"},{"无"},{"无"},{"无"}
                    },
                    {    //天津
                            {"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"},{"无"}
                    },
                    {    //广东
                            {"海珠区","荔湾区","越秀区","白云区","萝岗区","天河区","黄埔区","花都区","从化市","增城市","番禺区","南沙区"}, //广州
                            {"宝安区","福田区","龙岗区","罗湖区","南山区","盐田区"}, //深圳
                            {"武江区","浈江区","曲江区","乐昌市","南雄市","始兴县","仁化县","翁源县","新丰县","乳源县"}  //韶关
                    }
            };


    /*
     * 设置下拉框
     */
    private void setSpinner()
    {
        provinceSpinner = (Spinner)headerView.findViewById(R.id.type_first);
        citySpinner = (Spinner)headerView.findViewById(R.id.type_second);
        countySpinner = (Spinner)headerView.findViewById(R.id.type_three);

        //绑定适配器和值
        provinceAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, province);
        provinceSpinner.setAdapter(provinceAdapter);
        provinceSpinner.setSelection(3,true);  //设置默认选中项，此处为默认选中第4个值

        cityAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, city[3]);
        citySpinner.setAdapter(cityAdapter);
        citySpinner.setSelection(0,true);  //默认选中第0个

        countyAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, county[3][0]);
        countySpinner.setAdapter(countyAdapter);
        countySpinner.setSelection(0, true);


        //省级下拉框监听
        provinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            // 表示选项被改变的时候触发此方法，主要实现办法：动态改变地级适配器的绑定值
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3)
            {
                //position为当前省级选中的值的序号

                //将地级适配器的值改变为city[position]中的值
                cityAdapter = new ArrayAdapter<String>(
                        getActivity(),android.R.layout.simple_list_item_1, city[position]);
                // 设置二级下拉列表的选项内容适配器
                citySpinner.setAdapter(cityAdapter);
                provincePosition = position;    //记录当前省级序号，留给下面修改县级适配器时用
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }

        });


        //地级下拉监听
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long arg3)
            {
                countyAdapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, county[provincePosition][position]);
                countySpinner.setAdapter(countyAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });
    }
}
