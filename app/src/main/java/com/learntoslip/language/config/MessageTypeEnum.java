package com.learntoslip.language.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/4.
 */
public enum MessageTypeEnum {
    IP("001", "IP");

    private String value;
    private String desc;

    MessageTypeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String value() {
        return this.value;
    }

    public String desc() {
        return this.desc;
    }

    public static String getDesc(String value) {
        for (MessageTypeEnum s : values()) {
            if (s.value == value) {
                return s.desc();
            }
        }
        return null;
    }

    public static Map<String, String> getMap() {
        Map<String, String> timeMap = new HashMap<String, String>();
        for (MessageTypeEnum s : values()) {
            timeMap.put(s.value, s.desc);
        }
        return timeMap;
    }
}
