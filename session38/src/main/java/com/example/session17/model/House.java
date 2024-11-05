package com.example.session17.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class House {
    Integer id;
    String address;
    String color;
    String owner;

    public String replaceAddress(String address, String replacement) {
        return this.address.replace(address, replacement);
    }


}
