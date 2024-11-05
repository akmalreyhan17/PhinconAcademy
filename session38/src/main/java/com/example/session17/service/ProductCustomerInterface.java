package com.example.session17.service;

import java.util.List;

import com.example.session17.model.Product;

public interface ProductCustomerInterface {
    Product getProduct(Long id);
    List<Product> sortProductAscending();
    List<Product> filterProductByPrice(Double price);
}
