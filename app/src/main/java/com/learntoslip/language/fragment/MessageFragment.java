package com.learntoslip.language.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.learntoslip.language.R;
import com.learntoslip.language.adapter.WordAdapter;
import com.learntoslip.language.service.busservice.WordService;

/**
 * Created by Administrator on 2017/5/25.
 */
public class MessageFragment extends Fragment {
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.word_list , container, false);

//        WordAdapter wordAdapter = new WordAdapter(getActivity(), R.layout.word_list_item, WordService.listWord(1));
//
//        ListView listView = (ListView)view.findViewById(R.id.wordListView);
//
//        listView.setAdapter(wordAdapter);
        new Thread(networkTask).start();
        return view;
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);

            // UI界面的更新等相关操作
            WordAdapter wordAdapter = new WordAdapter(getActivity(), R.layout.word_list_item, WordService.convertWord(val));

            ListView listView = (ListView)view.findViewById(R.id.wordListView);

            listView.setAdapter(wordAdapter);

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
            data.putString("value", WordService.listWord(2));
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };
}
