package com.example.session29.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.session29.model.User;
import com.example.session29.model.UserCityCount;
import com.example.session29.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void insertUser(User user) {
        mongoTemplate.insert(user, "user");
    }

    public void insertSaveUser(User user) {
        mongoTemplate.save(user);
    }

    public void insertUpdateUser(User user, String id) {
        User userOld = mongoTemplate.findById(id, User.class);
        userOld.setFirstName(user.getFirstName());
        userOld.setLastName(user.getLastName());
        mongoTemplate.save(userOld);
    }

    public void updateUserByEmail(User user, String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        Update update = new Update();
        update.set("firstName", user.getFirstName());
        update.set("lastName", user.getLastName());

        mongoTemplate.updateFirst(query, update, User.class);
    }

    public void updateAllUserActivity() {
        Query query = new Query();
        query.addCriteria(Criteria.where("active").is(true));
        Update update = new Update();
        update.set("active", false);

        mongoTemplate.updateMulti(query, update, User.class);
    }

    public void upsertUser(User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(user.getUsername()));
        Update update = new Update();
        update.set("name", user.getFirstName());
        update.set("email", user.getEmail());

        mongoTemplate.upsert(query, update, User.class);
    }

    public void deleteUserById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, User.class);
    }

    public void any() {
        // List<User> users = mongoTemplate.findAll(User.class);

        String name = "jj";

        // Query query = new Query();
        // query.addCriteria(Criteria.where("name").is(name));
        // User userOne = mongoTemplate.findOne(query, User.class);

        // System.out.println(userOne);

        // name = "Alice"
        Criteria.where("name").is("Alice");

        // 18 < age < 30
        Criteria.where("age").gte(18).lte(30);

        // city = "New York" and age > 25
        Criteria.where("city").is("New York").and("age").gt(25);

        // matching with array
        Criteria.where("status").in(List.of("ACTIVE", "PENDING"));

        // matching with regex
        Criteria.where("name").regex("^A");

        // using andOperator/ orOperator
        Criteria cityCriteria = Criteria.where("city").is("New York");
        Criteria ageCriteria = Criteria.where("age").gt(25);
        Criteria combinedCriteria = new Criteria().andOperator(cityCriteria, ageCriteria);

        Criteria cityCriteria1 = Criteria.where("city").is("New York");
        Criteria cityCriteria2 = Criteria.where("city").is("Los Angeles");
        Criteria combinedCityCriteria = new Criteria().orOperator(cityCriteria1, cityCriteria2);

        System.out.println(combinedCriteria);
        System.out.println(combinedCityCriteria);

        // Query query = new Query();
        // query.addCriteria(Criteria.where("address.street").is("Main Street"));
        // List<User> users = mongoTemplate.find(query, User.class);

        // System.out.println(users);

        // Query query = new Query();
        // query.addCriteria(Criteria.where("orders").elemMatch(Criteria.where("quantity").gt(5)));
        // List<User> users = mongoTemplate.find(query, User.class);

        // System.out.println(users);

        // Query query = new Query();
        // query.addCriteria(Criteria.where("age").gt(18));
        // query.fields().include("name").include("email");
        // query.fields().exclude("password");

        // int page = 2;
        // int size = 10;
        // Query query = new Query();
        // query.addCriteria(Criteria.where("age").gt(18));
        // query.skip((page - 1) * size).limit(size);

        Query query = new Query();
        query.addCriteria(Criteria.where("age").gt(18));
        query.with(Sort.by(Sort.Direction.ASC, "name"));
    }

    public List<UserCityCount> getAdultUserCountByCity(int age) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("age").gt(age)),
                Aggregation.group("city").count().as("userCount"),
                Aggregation.project("userCount").and("city").previousOperation());

        AggregationResults<UserCityCount> result = mongoTemplate.aggregate(
                aggregation,
                "users",
                UserCityCount.class);

        return result.getMappedResults();
    }
}
