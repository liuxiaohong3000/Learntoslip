package com.learntoslip.language.listener;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.learntoslip.language.service.busservice.WordService;

/**
 * Created by Administrator on 2017/9/21.
 */
public class SaveWordDetailListener implements View.OnClickListener {
    private Context context;
    private Long id;
    private EditText tabclassview;
    private EditText tabshorthandview;
    private EditText tabsynonymview;
    private EditText tabphrasesview;
    private EditText tabotherInfoview;
    public SaveWordDetailListener(Context context, Long id, EditText tabclassview,EditText tabshorthandview,EditText tabsynonymview,EditText tabphrasesview,EditText tabotherInfoview) {
        this.context = context;
        this.id = id;
        this.tabclassview = tabclassview;
        this.tabshorthandview = tabshorthandview;
        this.tabsynonymview = tabsynonymview;
        this.tabphrasesview = tabphrasesview;
        this.tabotherInfoview = tabotherInfoview;
    }
    @Override
    public void onClick(View v) {
        new Thread(networkTask).start();
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
            Toast.makeText(context, "修改完成，请更新！", Toast.LENGTH_LONG).show();
        }
    };

    /**
     * 网络操作相关的子线程
     */
    Runnable networkTask = new Runnable() {

        @Override
        public void run() {
            // 在这里进行 http request.网络请求相关操作
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value", WordService.modifyWordDetail(id,tabsynonymview.getText().toString(),tabclassview.getText().toString(),tabshorthandview.getText().toString(),tabphrasesview.getText().toString(),tabotherInfoview.getText().toString()));
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };
}
