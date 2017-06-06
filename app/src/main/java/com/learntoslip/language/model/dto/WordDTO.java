package com.learntoslip.language.model.dto;

import com.learntoslip.language.model.Word;
import com.learntoslip.language.model.WordTab;

/**
 * Created by Administrator on 2017/6/6.
 */
public class WordDTO {
    private Word word;
    private WordTab tab;

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public WordTab getTab() {
        return tab;
    }

    public void setTab(WordTab tab) {
        this.tab = tab;
    }
}
