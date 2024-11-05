package com.example.wow.model;

import java.time.LocalDateTime;
import java.util.Date;

import com.example.wow.component.ApplicationContextProvider;
import com.example.wow.service.CacheService;
import com.example.wow.service.SecurityService;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "user_jpa")
public class UserJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String name;
    private String email;

    @Transient
    private Integer age; // Not a column in the database

    @Version
    private int version;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Temporal(TemporalType.TIME)
    private Date createdAt;

    @PrePersist
    public void onPrePersist() {
        //createdAt = LocalDateTime.now();
    }

    @PostPersist
    public void onPostPersist() {
        System.out.println("User with ID " + id + " has been persisted.");
    }

    private LocalDateTime updatedAt;

    private Object decryptedPassword;

    private Object encryptedPassword;

    @PreUpdate
    public void onPreUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @PostUpdate
    public void onPostUpdate() {
        System.out.println("User with ID " + id + " has been updated.");
    }

    @PostUpdate
    public void cacheAfterUpdate() {
        CacheService cacheService = ApplicationContextProvider.getBean(CacheService.class);
        cacheService.cacheUser(this);
        System.out.println("User cached after load: " + id);
    }

    @PostLoad
    public void decryptPassword() {
        SecurityService securityService = ApplicationContextProvider.getBean(SecurityService.class);
        this.decryptedPassword = securityService.decrypt(encryptedPassword);
        System.out.println("Decrypted password for user: " + id);
    }
}
