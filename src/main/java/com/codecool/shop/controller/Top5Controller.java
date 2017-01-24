package com.codecool.shop.controller;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import spark.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * Created by jakab on 2017.01.24..
 */
public class Top5Controller {

    private static final String SERVICE_URL = "http://localhost:60001";
    private static final String APIKEY = "c989ce20ab8daa4876982f701a4eea51";
    private static final String TESTAPIKEY = "negy";

//    public Boolean isRunning() throws URISyntaxException, IOException {
//
//        Boolean running = execute("/status").equalsIgnoreCase("ok");
//
//        if(running){
//        } else {
//        }
//
//        return running;
//    }

    public static String getTop5(spark.Request req, Response res) throws IOException, URISyntaxException {

        String response = execute("/api/", "/gettop5");

        return response;
    }

    private static String execute(String url, String url2) throws IOException, URISyntaxException {
        URI uri = new URIBuilder(SERVICE_URL + url + TESTAPIKEY + url2).build();
        return Request.Get(uri).execute().returnContent().asString();
    }

}
