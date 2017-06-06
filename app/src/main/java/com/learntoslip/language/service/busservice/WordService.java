package com.learntoslip.language.service.busservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.learntoslip.language.R;
import com.learntoslip.language.model.Word;
import com.learntoslip.language.service.webservice.WordWebService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */
public class WordService {
    /**
     * 获取字符串
     * @param j
     * @return
     */
    public static String listWord(int pageNum){
        return WordWebService.listWord(pageNum);
    }

    /**
     * json数据转换
     * @param wordJson
     * @return
     */
    public static List<Word> convertWord(String wordJson){
        if (wordJson==null)
            return new ArrayList<Word>();

        JSONObject ret = JSON.parseObject(wordJson);
        if (ret.getString("status").equals("0")) {
            if (ret.get("data") == null) {
                return new ArrayList<Word>();
            }
            JSONArray array = ret.getJSONArray("data");
            List<Word> words = JSON.parseArray(array.toJSONString(), Word.class);
            for(Word word : words){
                word.setImageId(R.drawable.witch);
            }
            return words;
        }
        return new ArrayList<Word>();
    }
}
