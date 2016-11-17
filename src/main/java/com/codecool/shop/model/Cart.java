package com.codecool.shop.model;

import java.util.ArrayList;

/**
 * Created by jakab on 2016.11.17..
 */
public class Cart extends AbstractCart{
    public void add(Product product){
        lineItems.add(product);
    }

    public ArrayList<Product> getLineItems(){
        return lineItems;
    }
}
