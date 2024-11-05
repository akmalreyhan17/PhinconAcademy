package com.example.session47a.service;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

@Component
public class CustomHeaderInterception implements ClientInterceptor {

    @Override
    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
        SoapMessage soapMessage = (SoapMessage) messageContext.getRequest();
        SoapHeader header = soapMessage.getSoapHeader();

        // Add custom authentication token to SOAP header
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element auth = doc.createElement("Authentication");
            auth.setTextContent("Bearer your-auth-token-here");

            QName qname = new QName("http://example.com/auth", "AuthHeader");
            header.addHeaderElement(qname).setText("Bearer your-auth-token-here");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return true; // Continue processing the message
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleResponse'");
    }

    @Override
    public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handleFault'");
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Exception ex) throws WebServiceClientException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'afterCompletion'");
    }

}
