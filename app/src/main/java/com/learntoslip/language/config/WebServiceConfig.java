package com.learntoslip.language.config;

/**
 * Created by Administrator on 2017/6/2.
 */
public class WebServiceConfig {
    public static String IP="192.168.72.1";
    public static String getUrl(){
        return "http://"+IP+":8080/wordapi/";
    }
}
