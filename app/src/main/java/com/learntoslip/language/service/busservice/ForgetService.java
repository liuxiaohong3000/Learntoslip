package com.learntoslip.language.service.busservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.learntoslip.language.model.ForgettingCurve;
import com.learntoslip.language.service.webservice.WordWebService;

import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.JSON.parseArray;

/**
 * Created by Administrator on 2017/5/23.
 */
public class ForgetService {
    /**
     * 获取字符串
     * @param pageNum
     * @return
     */
    public static String getForgets(){
        return WordWebService.getForgets();
    }

    /**
     * json数据转换
     * @param forgetJson
     * @return
     */
    public static List<ForgettingCurve> converForgets(String forgetJson){
        if (forgetJson==null)
            return new ArrayList<ForgettingCurve>();

        JSONObject ret = JSON.parseObject(forgetJson);
        if (ret.getString("status").equals("0")) {
            if (ret.get("data") == null) {
                return new ArrayList<ForgettingCurve>();
            }
            JSONArray array = ret.getJSONArray("data");
            List<ForgettingCurve> forgets = parseArray(array.toJSONString(), ForgettingCurve.class);
            return forgets;
        }
        return new ArrayList<ForgettingCurve>();
    }
}
