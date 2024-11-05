package com.example.wow.model;

import java.util.Date;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class UserDetails {
    private Date birthDate;
    private String sex;
    private String address;
}
