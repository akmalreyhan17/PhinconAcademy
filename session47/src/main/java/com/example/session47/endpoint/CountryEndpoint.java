package com.example.session47.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.countries.Country;
import com.example.countries.GetCountryRequest;
import com.example.countries.GetCountryResponse;

@Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://example.com/countries";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        Country country = new Country();
        country.setName(request.getName());
        country.setCapital("Ndjamena");
        country.setPopulation(20500000);

        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(country);
        return response;
    }
}
