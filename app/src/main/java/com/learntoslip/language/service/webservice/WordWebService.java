package com.learntoslip.language.service.webservice;

import com.learntoslip.language.config.WebServiceConfig;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Created by Administrator on 2017/6/2.
 */
public class WordWebService {
    public static String listWord(int pageNum){
        try {

            Client client = Client.create();
            client.setConnectTimeout(2000);
            client.setReadTimeout(5000);

            WebResource webResource = client
                    .resource(WebServiceConfig.getUrl()+"words");
            MultivaluedMapImpl params = new MultivaluedMapImpl();
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
                    .resource(WebServiceConfig.getUrl()+"words/"+id);
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
}
