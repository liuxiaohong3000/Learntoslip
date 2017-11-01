package com.learntoslip.language.service.busservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.learntoslip.language.model.UserWord;
import com.learntoslip.language.service.webservice.WordWebService;

import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.JSON.parseArray;

/**
 * Created by Administrator on 2017/5/23.
 */
public class UserWordService {
    /**
     * 获取字符串
     * @param pageNum
     * @return
     */
    public static String getUserWords(Long userId, Long wordType, Integer pageNum){
        return WordWebService.getUserWords(userId,wordType,pageNum);
    }

    /**
     * json数据转换
     * @param wordJson
     * @return
     */
    public static List<UserWord> convertUserWords(String wordJson){
        if (wordJson==null)
            return new ArrayList<UserWord>();

        JSONObject ret = JSON.parseObject(wordJson);
        if (ret.getString("status").equals("0")) {
            if (ret.get("data") == null) {
                return new ArrayList<UserWord>();
            }
            JSONArray array = ret.getJSONArray("data");
            List<UserWord> userWords = parseArray(array.toJSONString(), UserWord.class);
            return userWords;
        }
        return new ArrayList<UserWord>();
    }

    /**
     * 增加记忆关键词
     * @return
     */
    public static String addUserWord(Long userId, Long forgetId, Long wordId){
        return WordWebService.addUserWord(userId,forgetId,wordId);
    }

    /**
     * 增加记忆关键词
     * @return
     */
    public static String modifyUserWord(Long userWordId,Long forgetId){
        return WordWebService.modifyUserWord(userWordId,forgetId);
    }

    /**
     * 获取字符串
     * @return
     */
    public static String getUserWord(Long userId, Long wordId){
        String wordJson=WordWebService.getUserWord(userId,wordId);
        return wordJson;
    }
    /**
     * 删除过期的关键词
     * @return
     */
    public static String delExpireUserWord(Long userId,Long userWordId){
        String wordJson=WordWebService.delExpireUserWord(userId,userWordId);
        return wordJson;
    }
    /**
     * 获取字符串
     * @return
     */
    public static UserWord convertUserWord(String wordJson){
        if (wordJson==null)
            return null;

        JSONObject ret = JSON.parseObject(wordJson);
        if (ret.getString("status").equals("0")) {
            if (ret.get("data") == null) {
                return null;
            }
            JSONObject objjson=ret.getJSONObject("data");
            UserWord dto=JSON.parseObject(objjson.toJSONString(),UserWord.class);
            return dto;
        }
        return null;
    }
}
