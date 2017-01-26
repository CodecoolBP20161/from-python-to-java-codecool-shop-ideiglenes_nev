package com.codecool.shop.controller;

import org.apache.http.client.utils.URIBuilder;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by jakab on 2017.01.25..
 */
public class AvatarController {

    private static final String APIURL = "http://localhost:60000";

    public static String getAvatar(Request req, Response res) throws IOException, URISyntaxException {

        String sessionID = req.session().id();
        String response = execute("/avatar?sessionid=", sessionID);

        return response;

    }

    private static String execute(String url, String sessionID) throws IOException, URISyntaxException {

        URI uri = new URIBuilder(APIURL + url + sessionID).build();
        return org.apache.http.client.fluent.Request.Get(uri).execute().returnContent().asString();
    }
}
