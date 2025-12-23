package com.hadyd.bankingapi.dto;

import lombok.Data;

@Data //Auto generates getters and setters
public class RegisterRequest {

    private String email; //User's email
    private String password; //User's password
    private String firstName; //User's first name
    private String lastName; //User's last name
}
