package com.example.session26.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@RedisHash(value = "student", timeToLive = 1000)
public class StudentCache implements Serializable {
    @Id
    private Integer id;
    private String name;
    private Double gpa;
}
