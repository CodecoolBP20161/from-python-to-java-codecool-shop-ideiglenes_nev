package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

/**
 * Created by jakab on 2016.11.17..
 */
public class CartController {
    public static ModelAndView addToCart(Request req, Response res) {

        int productId = Integer.parseInt(req.params(":product_id"));
        ProductDao productDataStore = ProductDaoMem.getInstance();
        Product product = productDataStore.find(productId);

//        if (req.session().isNew()){
//            Cart cart = new Cart();
//
//        } else {
//
//        }

        System.out.println(productId);
        res.redirect("/");
        return null;
    }
}
