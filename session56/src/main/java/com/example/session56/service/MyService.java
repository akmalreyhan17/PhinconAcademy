package com.example.session56.service;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;

@Service
public class MyService {
    
    @PersistenceContext
    private EntityManager entityManager;

    public void performOperation() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            // Perform database operations
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }
}
