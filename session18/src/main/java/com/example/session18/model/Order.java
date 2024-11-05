package com.example.session18.model;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Order {
    private User accountHolder;
    private User customer;
}
