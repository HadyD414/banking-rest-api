package com.hadyd.bankingapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id; //User's ID
    private String email; //User's email
    private String firstName; //User's first name
    private String lastName; //User's last name
}
