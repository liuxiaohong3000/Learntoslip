package com.learntoslip.language.listener;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.learntoslip.language.config.MindConfig;
import com.learntoslip.language.service.busservice.UserWordService;

/**
 * Created by Administrator on 2017/9/21.
 */
public class DelExpireUserWordListener implements View.OnClickListener {
    private Context context;

    public DelExpireUserWordListener(Context context) {
        this.context = context;
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
            Toast.makeText(context, "清除完成，请更新！", Toast.LENGTH_LONG).show();
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
            data.putString("value", UserWordService.delExpireUserWord(MindConfig.userId));
            msg.setData(data);
            handler.sendMessage(msg);
        }
    };
}
