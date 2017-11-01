package com.learntoslip.language.service.webservice;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class WordWebServiceTest {
    @Test
    public void getWords() throws Exception {
        System.out.println(JSON.toJSONString(WordWebService.listWord(1L,1)));
        //assertEquals(4, 2 + 2);
    }
    @Test
    public void getWord() throws Exception {
        System.out.println(JSON.toJSONString(WordWebService.getWord(1)));
        //assertEquals(4, 2 + 2);
    }
    @Test
    public void getForgets() throws Exception {
        System.out.println(JSON.toJSONString(WordWebService.getForgets()));
        //assertEquals(4, 2 + 2);
    }
    @Test
    public void getUserWords() throws Exception {
        System.out.println(JSON.toJSONString(WordWebService.getUserWords(1L, null, 1)));
        //assertEquals(4, 2 + 2);
    }
    @Test
    public void addUserWords() throws Exception {
        System.out.println(JSON.toJSONString(WordWebService.addUserWord(1L, 1L, 2l)));
        //assertEquals(4, 2 + 2);
    }

    @Test
    public void modifyUserWord() throws Exception {
        System.out.println(JSON.toJSONString(WordWebService.modifyUserWord(2L,2L)));
        //assertEquals(4, 2 + 2);
    }


    @Test
    public void getTypes() throws Exception {
        System.out.println(JSON.toJSONString(WordWebService.getTypes()));
        //assertEquals(4, 2 + 2);
    }
    @Test
    public void delExpireUseWord() throws Exception {
        System.out.println(JSON.toJSONString(WordWebService.delExpireUserWord(1L,25L)));
        //assertEquals(4, 2 + 2);
    }
}