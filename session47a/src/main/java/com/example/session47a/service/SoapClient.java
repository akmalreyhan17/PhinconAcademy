package com.example.session47a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import springsoap.client.GetCountryRequest;
import springsoap.client.GetCountryResponse;

@Component
public class SoapClient extends WebServiceGatewaySupport {

    @Autowired
    CustomHeaderInterception interception;

    public GetCountryResponse getCountry(String countryName) {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(countryName);

        getWebServiceTemplate().setInterceptors(new ClientInterceptor[]{interception});

        GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws", request);

        return response;
    }

}
