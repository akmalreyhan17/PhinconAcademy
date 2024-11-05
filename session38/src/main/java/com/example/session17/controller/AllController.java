package com.example.session17.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.session17.model.Customer;
import com.example.session17.model.Merchant;
import com.example.session17.service.CustomerService;
import com.example.session17.service.MerchantService;

@RestController
public class AllController {

    private final CustomerService customerService;
    private final MerchantService merchantService;

    @Autowired
    public AllController(CustomerService customerService, MerchantService merchantService) {
        this.customerService = customerService;
        this.merchantService = merchantService;
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok().body(customer);
    }

    @GetMapping("/merchant/{id}")
    public ResponseEntity<Merchant> getMerchantById(@PathVariable Long id) {
        Merchant merchant = merchantService.getMerchantById(id);
        return ResponseEntity.ok().body(merchant);
    }
}


