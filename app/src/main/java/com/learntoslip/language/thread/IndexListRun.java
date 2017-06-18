package com.learntoslip.language.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.learntoslip.language.model.Word;
import com.learntoslip.language.service.busservice.WordService;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */
public class IndexListRun implements  Runnable{
    private  IndexListOpera listOpera;

    public void setListOpera(IndexListOpera listOpera) {
        this.listOpera = listOpera;
    }

    Handler wordListNetworkHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
            if(val==null){
                //无数据删除底部布局
                listOpera.setShowMore(false);
                listOpera.getListView().removeFooterView(listOpera.getMoreView());
                Toast.makeText(listOpera.getContext(), "数据全部加载完成，没有更多数据！", Toast.LENGTH_LONG).show();
                return;
            }
            List<Word> templist=WordService.convertWords(val);

            //如果当前页数据小于每页数据，说明已无数据
            if(templist.size()<listOpera.getPageSize()){
                //无数据删除底部布局
                //listOpera.getFrament().rem
                listOpera.setShowMore(false);
                listOpera.getListView().removeFooterView(listOpera.getMoreView());
                Toast.makeText(listOpera.getContext(), "数据全部加载完成，没有更多数据！", Toast.LENGTH_LONG).show();
            }else{
                if(!listOpera.getShowMore()){
                    listOpera.getListView().addFooterView(listOpera.getMoreView());
                }
                listOpera.setPageNum(listOpera.getPageNum()+1);
            }

            //缓存数据，并通知listView刷新数据
            listOpera.getAllword().addAll(templist);
            listOpera.getWordAdapter().notifyDataSetChanged();

            //显示加载按钮
            //listOpera.getBt().setVisibility(View.VISIBLE);
            listOpera.getPg().setVisibility(View.GONE);

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
        data.putString("value", WordService.listWord(listOpera.getTypeId(),listOpera.getPageNum()));
        msg.setData(data);
        wordListNetworkHandler.sendMessage(msg);
    }
}
