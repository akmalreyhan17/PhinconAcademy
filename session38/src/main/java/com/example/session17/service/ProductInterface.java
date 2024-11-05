package com.example.session17.service;

import java.util.List;

import com.example.session17.model.Product;

public interface ProductInterface {
    Product getProduct(Long id);
    Product insertProduct(Product product);
    Product highlightProduct(Long id);
    List<Product> sortProductAscending();
    List<Product> filterProductByPrice(Double price);
}
