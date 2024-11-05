package com.example.session16.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.session16.model.Customer;
import com.example.session16.model.CustomerResource;
import com.example.session16.service.CustomerService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        try {
            Customer customer = customerService.getCustomer(id);
            return ResponseEntity.ok().body(customer);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Customer());
        }
    }

    @GetMapping("/repo/{id}")
    public ResponseEntity<CustomerResource> getCustomerByIdAlt(@PathVariable Long id) {
        try {
            Customer customer = customerService.getCustomer(id);
            CustomerResource resource = new CustomerResource(customer);
            resource.add(linkTo(methodOn(CustomerController.class)
                    .getCustomerByIdAlt(id)).withSelfRel());
            resource.add(linkTo(methodOn(CustomerController.class)
                    .getCustomerByIdAlt(id+1L)).withRel("next-customer"));
            return new ResponseEntity<>(resource, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity<Customer> getCustomerByName(@RequestParam String name) {
        return ResponseEntity.ok().body(customerService.getCustomerByName(name));
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        // HttpHeaders headers = new HttpHeaders();
        // headers.add("SignedIn", "False");
        return ResponseEntity.ok().header("SignedIn", "False").body(customerService.addCustomer(customer));
    }
}
