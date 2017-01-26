package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import spark.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by jakab on 2017.01.24..
 */
public class Top5Controller {

    private static final String SERVICE_URL = "http://localhost:60001";
    private static final String APIKEY = "e24ebc9b09a0178c51ce3825c11956f3";
    private static final String TESTAPIKEY = "negy";

    static private ProductDao productDao = ProductDaoMem.getInstance();

    public static List<Product> getTop5(spark.Request req, Response res) throws IOException, URISyntaxException, SQLException {

        String response = execute("/api/", "/gettop5");

        Product product;
        List<Product> top5Product = new ArrayList<>();
        JSONArray array = new JSONArray(response);
        for (int i = 0; i < array.length(); i++) {
            product = productDao.find(array.getJSONObject(i).getInt("productID"));
            top5Product.add(product);
        }
        return top5Product;
    }


    private static String execute(String url, String url2) throws IOException, URISyntaxException {
        URI uri = new URIBuilder(SERVICE_URL + url + APIKEY + url2).build();
        return Request.Get(uri).execute().returnContent().asString();
    }
}
