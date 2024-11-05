package com.example.session16.service;

import org.springframework.stereotype.Service;

import com.example.session16.model.Customer;

@Service
public interface CustomerService {
    Customer getCustomer(Long id);
    Customer getCustomerByName(String name);
    Customer addCustomer(Customer customer);
}
