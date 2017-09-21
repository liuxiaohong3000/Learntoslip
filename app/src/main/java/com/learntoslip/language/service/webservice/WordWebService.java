package com.learntoslip.language.service.webservice;

import com.learntoslip.language.config.WebServiceConfig;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MediaType;

/**
 * Created by Administrator on 2017/6/2.
 */
public class WordWebService {
    public static String getTypes(){
        try {

            Client client = Client.create();
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

            String output = response.getEntity(String.class);
            System.out.println("Output from Server .... \n" + output);
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String listWord(long typeId,int pageNum){
        try {

            Client client = Client.create();
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

            String output = response.getEntity(String.class);
            System.out.println("Output from Server .... \n" + output);
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getWord(long id){
        try {

            Client client = Client.create();
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
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getForgets(){
        try {

            Client client = Client.create();
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
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getUserWords(Long userId, Long wordType, Integer pageNum){
        try {

            Client client = Client.create();
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
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getUserWord(Long userId, Long wordId){
        try {

            Client client = Client.create();
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
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String addUserWord(Long userId, Long forgetId, Long wordId){
        try {

            Client client = Client.create();
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
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String modifyUserWord(Long userWordId,Long forgetId){
        try {

            Client client = Client.create();
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
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String delExpireUserWord(Long userId){
        try {

            Client client = Client.create();
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
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
