package com.example.session29.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.session29.model.User;

@Service
public class TransactionService {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    @Transactional
    public void performTransactionalOperation(User user) {
        mongoTemplate.insert(user);

        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(user.getEmail()));
        Update update = new Update();
        update.set("active", true);
        mongoTemplate.updateFirst(query, update, User.class);
    }
}
