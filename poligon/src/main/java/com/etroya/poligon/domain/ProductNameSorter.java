package com.etroya.poligon.domain;

import com.etroya.poligon.domain.data.Product;

import java.util.Comparator;

public class ProductNameSorter implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p1.getName().compareTo(p2.getName());
    }
}
