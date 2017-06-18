package com.learntoslip.language.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.learntoslip.language.R;
import com.learntoslip.language.model.Type;
import com.learntoslip.language.service.busservice.WordService;
import com.learntoslip.language.util.TypeUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */
public class IndexSelectRun implements  Runnable{
    private  IndexListOpera listOpera;

    public void setListOpera(IndexListOpera listOpera) {
        this.listOpera = listOpera;
    }

    private List<Type> types;

    private Spinner provinceSpinner = null;  //省级（省、直辖市）
    private Spinner citySpinner = null;     //地级市
    private Spinner countySpinner = null;    //县级（区、县、县级市）
    private ArrayAdapter<String> provinceAdapter = null;  //省级适配器
    private ArrayAdapter<String> cityAdapter = null;    //地级适配器
    private ArrayAdapter<String> countyAdapter = null;    //县级适配器
    private static int provincePosition = 0;
    private static int cityPosition = 0;

    /*
     * 设置下拉框
     */
    private void setSpinner()
    {
        provinceSpinner = (Spinner)listOpera.getHeaderView().findViewById(R.id.type_first);
        citySpinner = (Spinner)listOpera.getHeaderView().findViewById(R.id.type_second);
        countySpinner = (Spinner)listOpera.getHeaderView().findViewById(R.id.type_three);

        //绑定适配器和值
        provinceAdapter = new ArrayAdapter<String>(listOpera.getActivity(),
                android.R.layout.simple_list_item_1, TypeUtil.getNameArray(types));
        provinceSpinner.setAdapter(provinceAdapter);
        provinceSpinner.setSelection(0,true);  //设置默认选中项

        cityAdapter = new ArrayAdapter<String>(listOpera.getActivity(),
            android.R.layout.simple_list_item_1, TypeUtil.getNameArray(types.get(0).getTypes()));
        citySpinner.setAdapter(cityAdapter);
        citySpinner.setSelection(0,true);  //默认选中第0个

        countyAdapter = new ArrayAdapter<String>(listOpera.getActivity(),
            android.R.layout.simple_list_item_1, TypeUtil.getNameArray(types.get(0).getTypes().get(0).getTypes()));
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
                cityAdapter = new ArrayAdapter<String>(listOpera.getActivity(),
                    android.R.layout.simple_list_item_1, TypeUtil.getNameArray(types.get(position).getTypes()));

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
                countyAdapter =  new ArrayAdapter<String>(listOpera.getActivity(),
                        android.R.layout.simple_list_item_1, TypeUtil.getNameArray(types.get(provincePosition).getTypes().get(position).getTypes()));

                countySpinner.setAdapter(countyAdapter);
                cityPosition=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });
        //县级下拉监听
        countySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int position, long arg3)
            {
                Type type=types.get(provincePosition).getTypes().get(cityPosition).getTypes().get(position);
                IndexListRun indexListRun=new IndexListRun();
                listOpera.getAllword().clear();
                listOpera.setPageNum(1);
                listOpera.setTypeId(type.getId());
                indexListRun.setListOpera(listOpera);
                new Thread(indexListRun).start();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {

            }
        });
        Type type=types.get(provincePosition).getTypes().get(cityPosition).getTypes().get(0);
        IndexListRun indexListRun=new IndexListRun();
        listOpera.getAllword().clear();
        listOpera.setPageNum(1);
        listOpera.setTypeId(type.getId());
        indexListRun.setListOpera(listOpera);
        new Thread(indexListRun).start();
    }
    Handler wordTypeNetworkHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
            types=WordService.convertTypes(val);
            setSpinner();

        }
    };

    /**
     * 获取wordlist
     */
    @Override
    public void run() {
        // TODO
        // 在这里进行 http request.网络请求相关操作
        Message msg = new Message();
        Bundle data = new Bundle();
        data.putString("value", WordService.getTypes());
        msg.setData(data);
        wordTypeNetworkHandler.sendMessage(msg);
    }
}
