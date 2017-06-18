package com.learntoslip.language.util;

import com.learntoslip.language.model.Type;

import java.util.List;

/**
 * Created by Administrator on 2017/6/18.
 */
public class TypeUtil {
    public static String[] getNameArray(List<Type> types){
        String[] typeArray= new String[types.size()];
        int i=0;
        for(Type type : types){
            typeArray[i]=type.getName();
            i++;
        }
        return typeArray;
    }
}
