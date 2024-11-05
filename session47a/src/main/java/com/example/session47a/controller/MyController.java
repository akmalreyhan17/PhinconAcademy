package com.example.session47a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.session47a.service.SoapClient;

import springsoap.client.GetCountryResponse;

@RestController
public class MyController {

    @Autowired
    SoapClient soapClient;

    @GetMapping("/getCountry")
    public GetCountryResponse getCountry(@RequestParam String countryName) {
        return soapClient.getCountry(countryName);
    }
}
