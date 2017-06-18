package com.learntoslip.language.thread;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.learntoslip.language.service.webservice.WordWebService;

import java.lang.reflect.Method;
import java.util.Map;

public class NormalThread implements  Runnable {
    private Context context;
    private View view;
    private String requestMethod;
    private Map<String,Object> params;

    public void setContext(Context context){
        this.context=context;
    }

    public void setView(View view){
        this.view=view;
    }

    public void setRequestMethod(String requestMethod){
        this.requestMethod=requestMethod;
    }

    public void setParams(Map<String,Object> params) {
        this.params = params;
    }

    @Override
    public void run() {
        Message msg = new Message();
        Bundle data = new Bundle();
        try {
            WordWebService webService=new WordWebService();
            Method method= webService.getClass().getMethod(requestMethod,new Class[]{Map.class});
            String response=(String)method.invoke(webService, new Object[]{params});
            data.putString("value", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        msg.setData(data);
        handler.sendMessage(msg);
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("mylog", "请求结果为-->" + val);
            Toast.makeText(context, "更新成功！", Toast.LENGTH_LONG).show();

        }
    };
}
