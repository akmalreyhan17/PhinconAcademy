package com.example.session16n.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@XmlRootElement(name = "Person")
public class Person {

    @XmlTransient
    private Integer id;

    @XmlElement(name = "Nama")
    private String name;

    // @XmlElementWrapper(name = "phone")
    // @XmlElement(name = "phone")
    // private List<String> phone;

    // @XmlElement(name = "address")
    // private List<Address> address;
}
