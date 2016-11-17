package com.codecool.shop.controller;

import com.codecool.shop.model.Cart;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

/**
 * Created by jakab on 2016.11.17..
 */
public class CartController {
    public static ModelAndView addToCart(Request req, Response res) {

        Cart cart = new Cart();
        cart.add(req.id);
        System.out.println("mukodik");
        res.redirect("/");
        return null;
    }
}
