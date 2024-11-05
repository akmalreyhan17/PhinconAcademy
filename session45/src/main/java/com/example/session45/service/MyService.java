package com.example.session45.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.session45.advice.AdminOnly;
import com.example.session45.advice.CurrentUser;
import com.example.session45.advice.RoleRequired;
import com.example.session45.model.Data;
import com.example.session45.model.Order;
import com.example.session45.model.User;
import com.example.session45.repository.MyRepository;

import jakarta.annotation.security.RolesAllowed;

@Service
public class MyService {

    @Autowired
    MyRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    // @Secured("ROLE_ADMIN")
    // public void deleteAllUsers() {
    // // Only users with the 'ROLE_ADMIN' can execute this method
    // }

    // @Secured({ "ROLE_ADMIN", "ROLE_MANAGER" })
    // public void createReport() {
    // // Both admins and managers can access this method
    // }

    @PreAuthorize("hasRole('USER')")
    public List<Order> getOrdersForUser(Long userId) {
        // Only users with the 'USER' role can access this method
        return List.of(new Order());
    }

    @PreAuthorize("hasRole('ADMIN') and #order.userId == authentication.principal.id")
    public void updateOrder(Order order) {
        // Only admins can update orders, and only if they own the order
    }

    // @PostAuthorize("returnObject.userId == authentication.principal.id")
    // public Order getOrderById(Long orderId) {
    // // Users can only retrieve orders that they own
    // return orderRepository.findById(orderId);
    // }

    @PreFilter("filterObject.owner == authentication.principal.username")
    public void processOrders(List<Order> orders) {
        // Processes only orders where the authenticated user is the owner
    }

    // @PostFilter("filterObject.owner == authentication.principal.username")
    // public List<Order> getAllOrders() {
    // // Returns only the orders owned by the authenticated user
    // return orderRepository.findAll();
    // }

    @RolesAllowed("ROLE_ADMIN")
    public void manageUsers() {
        // Only users with the 'ROLE_ADMIN' role can manage users
    }

    @RolesAllowed({ "ROLE_ADMIN", "ROLE_MANAGER" })
    public void viewReports() {
        // Both admins and managers can view reports
    }

    @PreAuthorize("#user.id == authentication.principal.id")
    @PreFilter("filterObject.ownerId == authentication.principal.id")
    public void updateUserWithFilteredData(User user, List<Data> dataList) {
        // Logic for updating the user with filtered data
    }

    @AdminOnly
    public void deleteAllUsers() {
        // This method can only be accessed by users with the 'ROLE_ADMIN'
    }

    @RoleRequired(role = "ROLE_MANAGER")
    public void createReport() {
        // Only users with 'ROLE_MANAGER' can create reports
    }

    @RoleRequired(role = "ROLE_ADMIN")
    public void deleteReport() {
        // Only users with 'ROLE_ADMIN' can delete reports
    }

    @CurrentUser
    public User getUserProfile(Long userId) {
        // Only allows access if the userId matches the current authenticated user's ID
        return userRepository.findById(userId);
    }

    public String getCsrfTokenFromCookie() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://example.com/initial-request"; // Replace with the initial URL

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String csrfToken = response.getHeaders().get("Set-Cookie").stream()
                .filter(cookie -> cookie.startsWith("XSRF-TOKEN="))
                .map(cookie -> cookie.split("=")[1].split(";")[0])
                .findFirst()
                .orElseThrow(() -> new RuntimeException("CSRF Token not found"));

        return csrfToken;
    }

    public String getCsrfTokenFromHeader() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://example.com/initial-request"; // Replace with the initial URL
    
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String csrfToken = response.getHeaders().getFirst("X-CSRF-TOKEN");
    
        if (csrfToken == null) {
            throw new RuntimeException("CSRF Token not found in headers");
        }
    
        return csrfToken;
    }

    public String getCsrfTokenFromForm() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://example.com/form-page"; // Replace with the form URL
    
        String responseBody = restTemplate.getForObject(url, String.class);
        Document doc = Jsoup.parse(responseBody);
        Element csrfTokenField = doc.selectFirst("input[name=_csrf]"); // Adjust name attribute as needed
    
        if (csrfTokenField == null) {
            throw new RuntimeException("CSRF Token not found in form");
        }
    
        return csrfTokenField.attr("value");
    }

    public void sendRequest() {
        // Retrieved CSRF token 
        String csrfToken = "retrieved_csrf_token_value";

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-CSRF-TOKEN", csrfToken); // Set CSRF token header
        headers.set("Content-Type", "application/json");
        
        String url = "https://example.com/api/secure";
        String requestBody = "{\"data\":\"value\"}";

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        System.out.println("Response: " + response.getBody());
    }

}
