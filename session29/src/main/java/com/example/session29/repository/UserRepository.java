package com.example.session29.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.session29.model.User;
import com.example.session29.model.UserCityCount;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    // Find by a single field
    List<User> findByName(String name);

    // Find by multiple fields
    List<User> findByNameAndEmail(String name, String email);
    List<User> findByUsernameOrEmail(String name, String email);

    // Find with comparison operators
    List<User> findByAgeLessThan(int age);
    List<User> findByAgeGreaterThan(int age);
    //List<User> findByAgeBetween(int startAge, int endAge);

    List<User> findByNameIsNull();
    List<User> findByEmailIsNotNull();

    List<User> findByNameIn(List<String> names);
    List<User> findByAgeNotIn(List<Integer> ages);

    // Find using regex pattern matching
    List<User> findByEmailLike(String emailPattern);
    List<User> findByEmailContaining(String emailName);
    List<User> findByNameStartingWith(String prefix);

    // Find using sorting
    List<User> findByNameOrderById(String name);
    List<User> findByNameOrderByAgeDesc(String name);

    @Query("{ 'name' : ?0 }")
    List<User> findByNameUsingQuery(String name);

    @Query("{ 'age' : { $gt: ?0, $lt: ?1 } }")
    List<User> findByAgeBetween(int ageFrom, int ageTo);

    @Query(value = "{ 'name': ?0 }", fields = "{ 'name' : 1, 'city' : 1 }")
    List<User> findNameAndCityByName(String name);

    @Aggregation(pipeline = {
        "{ $match: { age: { $gt: ?0 } } }",
        "{ $group: { _id: '$city', total: { $sum: 1 } } }",
        "{ $project: { _id: 0, city: '$_id', userCount: '$total' } }"
    })
    List<UserCityCount> countUsersByCityAndAgeGreaterThan(int age);
}
