package com.example.session18.model;

import com.example.session18.validation.ValidPassword;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Account {
    @NotNull
    private String username;
    @ValidPassword
    private String password;
}
