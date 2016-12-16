package com.codecool.shop.model;

/**
 * Created by jakab on 2016.11.17..
 */
public abstract class AbstractCart{
    abstract void add(Product product);
    abstract int getTotalQuantity();
    abstract void removeProduct(Product product);
}
