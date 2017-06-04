package com.learntoslip.language.util;

import com.learntoslip.language.config.MessageTypeEnum;
import com.learntoslip.language.config.WebServiceConfig;
import com.learntoslip.language.model.Message;

/**
 * Created by Administrator on 2017/6/4.
 */
public class MessageUtil {
    public static void dealMsg(Message msg){
        if(msg != null){
            if(MessageTypeEnum.IP.value().equals(msg.getType())){
                WebServiceConfig.IP=msg.getMessage();
            }
        }
    }
}
