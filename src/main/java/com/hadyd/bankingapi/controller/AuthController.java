package com.hadyd.bankingapi.controller;

import com.hadyd.bankingapi.dto.RegisterRequest;
import com.hadyd.bankingapi.dto.UserResponse;
import com.hadyd.bankingapi.service.UserService;
//Import HttpStatus (Used for codes)
import org.springframework.http.HttpStatus;
//Import ResponseEntity (allows wrapping status code and body)
import org.springframework.http.ResponseEntity;
//Import Spring web annotations for REST endpoints
import org.springframework.web.bind.annotation.*;
//Class is a REST controller (handles API requests, and returns JSON)
@RestController
//Sets the base URL for all endpoints in this controller to /api/auth
@RequestMapping("/api/auth")
public class AuthController {
    //Service to handle user registration
    private final UserService userService;
    //Constructor
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    //Handles POST requests to /api/auth/register (creates new user)
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
        //Call UserService to register user
        UserResponse response = userService.registerUser(request);
        //Return HTTP 201 Created status with the UserResponse using body
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
