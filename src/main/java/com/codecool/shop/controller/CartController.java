package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import org.apache.http.client.utils.URIBuilder;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * Created by jakab on 2016.11.17..
 */
public class CartController {

    private static final String SERVICE_URL = "http://localhost:60001";
    private static final String APIKEY = "c989ce20ab8daa4876982f701a4eea51";
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

        String response = execute("/api/", "/addproduct");

        GsonBuilder builder = new GsonBuilder();

        return response;
    }

    private static String execute(String url, String url2) throws IOException, URISyntaxException {
        URI uri = new URIBuilder(SERVICE_URL + url + APIKEY + url2).build();
        return org.apache.http.client.fluent.Request.Get(uri).execute().returnContent().asString();
    }

}
