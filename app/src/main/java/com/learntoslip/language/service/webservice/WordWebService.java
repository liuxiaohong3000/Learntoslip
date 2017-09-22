package com.learntoslip.language.service.webservice;

import com.learntoslip.language.config.WebServiceConfig;
import com.learntoslip.language.util.http.HttpRequest;

/**
 * Created by Administrator on 2017/6/2.
 */
public class WordWebService {
    private static int readTimeOut=20000;
    public static String getTypes(){
        try {

            /*Client client = Client.create();
            client.setConnectTimeout(2000);
            client.setReadTimeout(5000);

            WebResource webResource = client
                    .resource(WebServiceConfig.getUrl()+"wordapi/types");
            ClientResponse response = webResource.accept("application/json")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);*/
            HttpRequest hr = new HttpRequest(WebServiceConfig.getUrl()+"wordapi/types");
            return hr.get(1000, readTimeOut, "utf-8", "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String listWord(long typeId,int pageNum){
        try {

            /*Client client = Client.create();
            client.setConnectTimeout(2000);
            client.setReadTimeout(5000);

            WebResource webResource = client
                    .resource(WebServiceConfig.getUrl()+"wordapi/words");
            MultivaluedMapImpl params = new MultivaluedMapImpl();
            params.add("typeId", typeId);
            params.add("pageNum", pageNum);
            ClientResponse response = webResource.queryParams(params).accept("application/json")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);*/
            HttpRequest hr = new HttpRequest(WebServiceConfig.getUrl()+"wordapi/words");
            hr.addParam("typeId", typeId);
            hr.addParam("pageNum", pageNum);
            return hr.get(1000, readTimeOut, "utf-8", "utf-8");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getWord(long id){
        try {

            /*Client client = Client.create();
            client.setConnectTimeout(2000);
            client.setReadTimeout(5000);

            WebResource webResource = client
                    .resource(WebServiceConfig.getUrl()+"wordapi/words/"+id);
            ClientResponse response = webResource.accept("application/json")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);
            System.out.println("Output from Server .... \n" + output);
            return output;*/

            HttpRequest hr = new HttpRequest(WebServiceConfig.getUrl()+"wordapi/words/"+id);
            return hr.get(1000, readTimeOut, "utf-8", "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getForgets(){
        try {

            /*Client client = Client.create();
            client.setConnectTimeout(2000);
            client.setReadTimeout(5000);

            WebResource webResource = client
                    .resource(WebServiceConfig.getUrl()+"forgetapi/forgets");
            ClientResponse response = webResource.accept("application/json")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);
            System.out.println("Output from Server .... \n" + output);
            return output;*/
            HttpRequest hr = new HttpRequest(WebServiceConfig.getUrl()+"forgetapi/forgets");
            return hr.get(1000, readTimeOut, "utf-8", "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getUserWords(Long userId, Long wordType, Integer pageNum){
        try {

            /*Client client = Client.create();
            client.setConnectTimeout(2000);
            client.setReadTimeout(5000);

            WebResource webResource = client
                    .resource(WebServiceConfig.getUrl()+"userapi/words");
            MultivaluedMapImpl params = new MultivaluedMapImpl();
            params.add("userId", userId);
            params.add("wordType", wordType);
            params.add("pageNum", pageNum);
            ClientResponse response = webResource.queryParams(params).accept("application/json")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);
            System.out.println("Output from Server .... \n" + output);
            return output;*/
            HttpRequest hr = new HttpRequest(WebServiceConfig.getUrl()+"userapi/words");
            hr.addParam("userId", userId);
            hr.addParam("wordType", wordType);
            hr.addParam("pageNum", pageNum);
            return hr.get(1000, readTimeOut, "utf-8", "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getUserWord(Long userId, Long wordId){
        try {

            /*Client client = Client.create();
            client.setConnectTimeout(2000);
            client.setReadTimeout(5000);

            WebResource webResource = client
                    .resource(WebServiceConfig.getUrl()+"userapi/word");
            MultivaluedMapImpl params = new MultivaluedMapImpl();
            params.add("userId", userId);
            params.add("wordId", wordId);
            ClientResponse response = webResource.queryParams(params).accept("application/json")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);
            System.out.println("Output from Server .... \n" + output);
            return output;*/
            HttpRequest hr = new HttpRequest(WebServiceConfig.getUrl()+"userapi/word");
            hr.addParam("userId", userId);
            hr.addParam("wordId", wordId);
            return hr.get(1000, readTimeOut, "utf-8", "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String addUserWord(Long userId, Long forgetId, Long wordId){
        try {

            /*Client client = Client.create();
            client.setConnectTimeout(2000);
            client.setReadTimeout(5000);

            WebResource webResource = client
                    .resource(WebServiceConfig.getUrl()+"userapi/addWord");
            MultivaluedMapImpl params = new MultivaluedMapImpl();
            params.add("userId", userId);
            params.add("forgetId", forgetId);
            params.add("wordId", wordId);
            ClientResponse response = webResource.queryParams(params).type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);
            System.out.println("Output from Server .... \n" + output);
            return output;*/
            HttpRequest hr = new HttpRequest(WebServiceConfig.getUrl()+"userapi/addWord");
            hr.addParam("userId", userId);
            hr.addParam("wordId", wordId);
            hr.addParam("forgetId", forgetId);
            return hr.post(1000, readTimeOut, "utf-8", "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String modifyUserWord(Long userWordId,Long forgetId){
        try {

            /*Client client = Client.create();
            client.setConnectTimeout(2000);
            client.setReadTimeout(5000);

            WebResource webResource = client.resource(WebServiceConfig.getUrl()+"userapi/modifyWord");
            MultivaluedMapImpl params = new MultivaluedMapImpl();
            params.add("userWordId", userWordId);
            params.add("forgetId", forgetId);
            ClientResponse response = webResource.queryParams(params).type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);
            System.out.println("Output from Server .... \n" + output);
            return output;*/
            HttpRequest hr = new HttpRequest(WebServiceConfig.getUrl()+"userapi/modifyWord");
            hr.addParam("userWordId", userWordId);
            hr.addParam("forgetId", forgetId);
            return hr.post(1000, readTimeOut, "utf-8", "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String delExpireUserWord(Long userId){
        try {

            /*Client client = Client.create();
            client.setConnectTimeout(2000);
            client.setReadTimeout(5000);

            WebResource webResource = client.resource(WebServiceConfig.getUrl()+"userapi/deleteExpireWord");
            MultivaluedMapImpl params = new MultivaluedMapImpl();
            params.add("userId", userId);
            ClientResponse response = webResource.queryParams(params).type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);
            System.out.println("Output from Server .... \n" + output);
            return output;*/
            HttpRequest hr = new HttpRequest(WebServiceConfig.getUrl()+"userapi/deleteExpireWord");
            hr.addParam("userId", userId);
            return hr.post(1000, readTimeOut, "utf-8", "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
