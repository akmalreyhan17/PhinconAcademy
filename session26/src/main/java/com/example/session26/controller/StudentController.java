package com.example.session26.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.session26.model.Student;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class StudentController {
    // @Operation(summary = "Retrieve a user by ID", description = "Provides user
    // details for the given ID")
    // @GetMapping("/{id}")
    // public ResponseEntity<Student> getUserById(@PathVariable Long id) {
    // // Implementation
    // return ResponseEntity.ok(new Student());
    // }

    // public ResponseEntity<Student> getUserById(
    //         @Parameter(description = "ID of the user to retrieve", required = true) 
    //         @PathVariable Long id) {
    //     // Implementation
    //     return ResponseEntity.ok(new Student());
    // }

    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful retrieval of user"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<Student> getUserById(@PathVariable Long id) {
        // Implementation
        return ResponseEntity.ok(new Student());
    }

}
