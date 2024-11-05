package com.example.wow.repository;

import java.util.List;
import java.time.LocalDate;

import org.hibernate.mapping.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.wow.model.User;
import com.example.wow.model.UserJPA;

@Repository
public interface RepositoryJPA extends JpaRepository<UserJPA, Integer> {

    List<User> findTop3ByAge();

    UserJPA findByEmail(String email);

    // @Query("SELECT u FROM user_jpa u WHERE u.name LIKE %:name%")
    // List<UserJPA> findByNameContaining(@Param("name") String name);

    @Query(value = "SELECT * FROM user WHERE email LIKE %:domain%", nativeQuery = true)
    List<UserJPA> findByEmailDomain(@Param("domain") String domain);

    // Field equality (=name, =email, !=age, etc.)
    List<User> findByName(String name);
    List<User> findByNameIsNot(String name);

    // Null equality (=null or !=null)
    List<User> findByNameIsNull();
    List<User> findByNameIsNotNull();

    // Boolean equality (=true or =false)
    List<User> findByActiveTrue();
    List<User> findByActiveFalse();

    // Similar to LIKE ‘value%’
    List<User> findByNameStartingWith(String prefix);

    // Similar to LIKE ‘%value’
    List<User> findByNameEndingWith(String suffix);

    // Similar to LIKE ‘%value%’
    List<User> findByNameContaining(String infix);

    // Used for custom pattern
    List<User> findByNameLike(String likePattern);

    List<User> findByAgeGreaterThan(Integer age);
    List<User> findByAgeGreaterThanEqual(Integer age);
    List<User> findByAgeBetween(Integer startAge, Integer endAge);

    List<User> findByAgeIn(List<Integer> ages);

    List<User> findByBirthDateAfter(LocalDate birthDate);
    List<User> findByBirthDateBefore(LocalDate birthDate);

    List<User> findByNameOrAge(String name, Integer age);
    List<User> findByNameOrAgeAndActive(String name, Integer age, Boolean active);

    // Ascending order
    List<User> findByNameOrderByName(String name);
    List<User> findByNameOrderByNameAsc(String name);

    // Descending order
    List<User> findByNameOrderByNameDesc(String name);
}
