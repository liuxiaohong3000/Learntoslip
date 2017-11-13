package com.learntoslip.language.service.busservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.learntoslip.language.R;
import com.learntoslip.language.model.Type;
import com.learntoslip.language.model.Word;
import com.learntoslip.language.model.dto.WordDTO;
import com.learntoslip.language.service.webservice.WordWebService;

import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.JSON.parseArray;

/**
 * Created by Administrator on 2017/5/23.
 */
public class WordService {
    /**
     * 获取关键词里诶啊哦
     * @param pageNum
     * @return
     */
    public static String listWord(long typeId,long userId,int pageNum){
        return WordWebService.listWord(typeId,userId,pageNum);
    }

    /**
     * 关键词数据转换
     * @param wordJson
     * @return
     */
    public static List<Word> convertWords(String wordJson){
        if (wordJson==null)
            return new ArrayList<Word>();

        JSONObject ret = JSON.parseObject(wordJson);
        if (ret.getString("status").equals("0")) {
            if (ret.get("data") == null) {
                return new ArrayList<Word>();
            }
            JSONArray array = ret.getJSONArray("data");
            List<Word> words = parseArray(array.toJSONString(), Word.class);
            for(Word word : words){
                word.setImageId(R.drawable.witch);
            }
            return words;
        }
        return new ArrayList<Word>();
    }

    /**
     * 获取关键词
     * @param id
     * @return
     */
    public static String getWord(long id){

        return WordWebService.getWord(id);
    }
    /**
     * 关键词数据转换
     * @param wordJson
     * @return
     */
    public static WordDTO convertWord(String wordJson){
        if (wordJson==null)
            return new WordDTO();

        JSONObject ret = JSON.parseObject(wordJson);
        if (ret.getString("status").equals("0")) {
            if (ret.get("data") == null) {
                return new WordDTO();
            }
            JSONObject objjson=ret.getJSONObject("data");
            WordDTO dto=JSON.parseObject(objjson.toJSONString(),WordDTO.class);
            return dto;
        }
        return new WordDTO();
    }

    /**
     * 获取类型列表
     * @return
     */
    public static String getTypes(){
        return WordWebService.getTypes();
    }

    /**
     * 类型数据转换
     * @return
     */
    public static List<Type> convertTypes(String typeJson){
        if (typeJson==null)
            return new ArrayList<Type>();

        JSONObject ret = JSON.parseObject(typeJson);
        if (ret.getString("status").equals("0")) {
            if (ret.get("data") == null) {
                return new ArrayList<Type>();
            }
            JSONArray array = ret.getJSONArray("data");
            List<Type> types = parseArray(array.toJSONString(), Type.class);
            return types;
        }
        return new ArrayList<Type>();
    }
    public static String modifyWordDetail(Long id, String synonym, String wordClasses, String shorthand, String phrases, String otherInfo){
        String wordJson=WordWebService.modifyWordDetail(id,synonym,wordClasses,shorthand,phrases,otherInfo);
        return wordJson;
    }
}
