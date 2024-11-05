package com.example.session7;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;

@Entity
public class Account {
    @Id
    private Long id;

    public Account(Long userId, String string) {
        //TODO Auto-generated constructor stub
    }

    public void debit(BigDecimal amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'debit'");
    }

    public void credit(BigDecimal amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'credit'");
    }

}
