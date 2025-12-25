package com.hadyd.bankingapi.controller;

import com.hadyd.bankingapi.dto.CreateAccountRequest;
import com.hadyd.bankingapi.dto.AccountResponse;
import com.hadyd.bankingapi.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//Makes this class a REST controller (for API requests, returns JSON)
@RestController
//Base URL for all endpoints in this controller
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;
    //Constructor
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    //Handles POST requests (creates new account)
    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody CreateAccountRequest request) {
        Long userId = 1L;
        //Call AccountService to create the account
        AccountResponse response = accountService.createAccount(userId, request);
        //Return HTTP 201 created status with body
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    //Handles GET requests (gets all user's accounts)
    @GetMapping
    public ResponseEntity<List<AccountResponse>> getUserAccounts() {
        Long userId = 1L;
        //Call AccountService to get all accounts linked to this user
        List<AccountResponse> accounts = accountService.getUserAccounts(userId);
        //Return HTTP 200 OK status with all accounts too
        return ResponseEntity.ok(accounts);
    }
    //Handles GET requests (get specific account by ID)
    @GetMapping("/{id}")
    //Call AccountService to get account by ID
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long id) {
        AccountResponse account = accountService.getAccountById(id);
        //Return HTTP 200 OK status with account too
        return ResponseEntity.ok(account);
    }
}
