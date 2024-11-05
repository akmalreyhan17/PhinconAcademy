package com.example.session16.model;

import org.springframework.hateoas.RepresentationModel;

public class CustomerResource extends RepresentationModel<CustomerResource> {
    private final Customer customer;

    public CustomerResource(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}
