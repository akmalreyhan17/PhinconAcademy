package com.example.session17.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.session17.model.Customer;
import com.example.session17.model.Product;
import com.example.session17.repository.ProductRepository;

@Service
public class CustomerService implements ProductCustomerInterface {

    @Autowired ProductRepository productRepository;

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> sortProductAscending() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sortProductAscending'");
    }

    @Override
    public List<Product> filterProductByPrice(Double price) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filterProductByPrice'");
    }
}
