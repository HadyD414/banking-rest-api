package com.hadyd.bankingapi.dto;

import lombok.Data;

@Data //Auto generates setters and getters
public class LoginRequest {

    private String email; //User's email
    private String password; //User's password
}
