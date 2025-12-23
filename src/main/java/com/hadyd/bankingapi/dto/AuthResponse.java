package com.hadyd.bankingapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String token; //JWT authentication token
    private UserResponse user; //Information about who logged in
}
