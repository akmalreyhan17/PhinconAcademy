package com.example.session17.service;

import org.springframework.stereotype.Service;

import com.example.session17.model.Merchant;
import com.example.session17.model.Product;

@Service
public class MerchantService implements ProductMerchantInterface{
    @Override
    public Product insertProduct(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertProduct'");
    }
    @Override
    public Product highlightProduct(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'highlightProduct'");
    }
}
