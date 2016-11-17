package com.codecool.shop.model;

import java.util.ArrayList;

/**
 * Created by jakab on 2016.11.17..
 */
public abstract class AbstractCart implements ShoppingCart {
    ArrayList<String> lineItems;

    @Override
    public abstract void add(Product product);
}
