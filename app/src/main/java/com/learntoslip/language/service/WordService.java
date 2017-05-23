package com.learntoslip.language.service;

import com.learntoslip.language.R;
import com.learntoslip.language.model.Word;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/23.
 */
public class WordService {
    public static List<Word> listWord(){
        List<Word> words = new ArrayList<Word>();
        for(int i=0;i<10;i++){
            Word word=new Word();
            word.setImageId(R.drawable.witch);
            word.setName("头像"+i);
            words.add(word);
        }
        return words;
    }
}
