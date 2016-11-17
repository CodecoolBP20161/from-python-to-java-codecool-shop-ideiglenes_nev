package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

/**
 * Created by jakab on 2016.11.17..
 */
public class CartController {
    public static ModelAndView addToCart(Request req, Response res) {

//        String link = req.params();
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
}
