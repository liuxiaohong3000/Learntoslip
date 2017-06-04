package com.learntoslip.language.service.webservice;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class WordWebServiceTest {
    @Test
    public void getWords() throws Exception {
        System.out.println(JSON.toJSONString(WordWebService.listWord()));
        //assertEquals(4, 2 + 2);
    }
}