package com.example.session29.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.*;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    private int age;

    @Indexed
    private String city;

    @Indexed(unique = true)
    private String email;

    @Indexed(expireAfterSeconds = 1800)
    private String eventSession;

    @Transient  
    private String temp;

    @CreatedDate  
    private LocalDateTime createdAt;

    @LastModifiedDate  
    private LocalDateTime updatedAt;

    private String username;
}
