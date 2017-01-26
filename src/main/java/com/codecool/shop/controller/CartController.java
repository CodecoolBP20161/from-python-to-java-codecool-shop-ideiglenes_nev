package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by jakab on 2016.11.17..
 */
public class CartController {

    private static final String SERVICE_URL = "http://localhost:60001";
    private static final String APIKEY = "6343f3453e774cb3d7f5c5bf56049ece";
    private static final String TESTAPIKEY = "negy";

    public static ModelAndView addToCart(Request req, Response res) throws SQLException {

        int productId = Integer.parseInt(req.params(":product_id"));
        ProductDao productDataStore = ProductDaoMem.getInstance();
        Product newProduct = productDataStore.find(productId);

        Cart cart;

        if (req.session().attribute("cart") == null) {
            cart = new Cart();
            cart.add(newProduct);
            req.session().attribute("cart", cart);
        } else {
            cart = req.session().attribute("cart");
            cart.add(newProduct);
        }

        String lastURL = req.session().attribute("lastURL");

        res.redirect(lastURL);
        return null;
    }

    public static String postTop5(spark.Request req, Response res) throws IOException, URISyntaxException {
        int productId = Integer.parseInt(req.params(":product_id"));
        System.out.println(productId + "122342353464585675675657");
        HashMap addToDB = new HashMap();
        addToDB.put("ID", productId);
        addToDB.put("quantity", 1);
        addToDB.put("API Key", APIKEY);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String response = execute("/api/", "/addproduct", gson.toJson(addToDB));

        String lastURL = req.session().attribute("lastURL");
        res.redirect(lastURL);
        return response;

    }

    private static String execute(String url, String url2, String jsonData) throws IOException, URISyntaxException {
        URI uri = new URIBuilder(SERVICE_URL + url + APIKEY + url2).build();
        return org.apache.http.client.fluent.Request.Post(uri).bodyString(jsonData, ContentType.APPLICATION_JSON).execute().returnContent().asString();
    }

}
