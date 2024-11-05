package com.example.session33a.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;

import com.example.session33a.model.Product;

@RestController
@RequestMapping("/client")
public class RestProductController {

    @Autowired
    RestTemplate restTemplate;

    private String productUrl = "http://localhost:8080/products";

    private String productUrlId(String id) {
        return productUrl + "/" + id;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        try {
            return restTemplate.getForEntity(productUrlId(id), Product.class);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all-product")
    public ResponseEntity<String> getAllProduct() {

        String username = "user";
        String password = "password";
        // String token = "JWT";
        // String auth = username + ":" + password;

        // byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());

        // String token = "JWT"; //put JWT token here
        // String authHeader = "Bearer " + token;

        HttpHeaders headers = new HttpHeaders();
        // headers.set("Authorization", authHeader);

        headers.setBasicAuth(username, password);
        // headers.setBearerAuth(token);

        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate// .getForEntity(productUrl, String.class);
                .exchange(productUrl, HttpMethod.GET, entity, String.class);
    }

    @GetMapping("/all-product-name")
    public ResponseEntity<List<String>> getAllProductName() {
        ResponseEntity<Product[]> products = restTemplate
                .getForEntity(productUrl, Product[].class);

        List<String> names = new ArrayList<>();
        for (int i = 0; i < products.getBody().length; i++) {
            names.add(products.getBody()[i].getName());
        }
        return ResponseEntity.ok(names);
    }

    @PostMapping("/add-product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return restTemplate.postForEntity(productUrl, product, Product.class);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);

        return restTemplate
                .exchange(productUrlId(id), HttpMethod.PUT, entity, Product.class);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id) {
        try {
            restTemplate.delete(productUrlId(id));
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/get-allowed")
    public ResponseEntity<?> getAllowed() {

        Set<HttpMethod> methods = restTemplate.optionsForAllow(productUrl);

        if (methods.contains(HttpMethod.GET)) {
            return restTemplate.getForEntity(productUrl, Product.class);
        }

        ResponseEntity<Product> response = restTemplate.execute(
                productUrl,
                HttpMethod.POST,
                new RequestCallback() {

                    @Override
                    public void doWithRequest(ClientHttpRequest request) throws IOException {
                        // TODO Auto-generated method stub
                        throw new UnsupportedOperationException("Unimplemented method 'doWithRequest'");
                    }
                    
                },
                new ResponseExtractor<ResponseEntity<Product>>() {

                    @Override
                    public ResponseEntity<Product> extractData(ClientHttpResponse response) throws IOException {
                        // TODO Auto-generated method stub
                        throw new UnsupportedOperationException("Unimplemented method 'extractData'");
                    }
                    
                });

        return ResponseEntity.ok().build();
    }
}
