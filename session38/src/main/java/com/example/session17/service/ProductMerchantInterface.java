package com.example.session17.service;

import com.example.session17.model.Product;

public interface ProductMerchantInterface {
    Product insertProduct(Product product);
    Product highlightProduct(Long id);
}
