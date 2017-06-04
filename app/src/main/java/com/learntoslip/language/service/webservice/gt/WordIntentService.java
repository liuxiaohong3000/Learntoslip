package com.learntoslip.language.service.webservice.gt;

import android.content.Context;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.learntoslip.language.model.Message;
import com.learntoslip.language.util.MessageUtil;

/**
 * Created by Administrator on 2017/6/4.
 */
public class WordIntentService extends GTIntentService {
    public WordIntentService() {

    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        String msgStr=new String(msg.getPayload());
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + msg.getClientId()+","+"onReceiveMsg -> " + "msg = " +msgStr);
        //处理消息
        MessageUtil.dealMsg(JSON.parseObject(msgStr, Message.class));
    }

    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + clientid);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
    }
}
