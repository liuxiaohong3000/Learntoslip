package com.learntoslip.language.config;

import com.learntoslip.language.model.Mind;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/4.
 */
public class MindConfig {
    public static List<Mind> listMind(){
        List<Mind> minds= new ArrayList<Mind>();
        Mind mind1=new Mind();
        mind1.setTitle("当前IP:");
        mind1.setText("192292929292");
        minds.add(mind1);
        return minds;
    }
}
