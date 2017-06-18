package com.learntoslip.language.service.busservice;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class WordTypeServiceTest {
    @Test
    public void getWords() throws Exception {
        String typeJson=WordService.getTypes();
        System.out.println(JSON.toJSONString(WordService.convertTypes(typeJson)));
        //assertEquals(4, 2 + 2);
    }

}