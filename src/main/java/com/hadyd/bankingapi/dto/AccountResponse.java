package com.hadyd.bankingapi.dto;

import com.hadyd.bankingapi.model.AccountType; //enum created earlier
import lombok.AllArgsConstructor; //Auto generates constructor with all parameters
import lombok.Data; //Auto generates getters and setters
import lombok.NoArgsConstructor; //Auto generates constructor with no parameters

import java.math.BigDecimal; //Exact decimal number type
import java.time.LocalDateTime; //Date and time

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    private Long id; //Account's unique identifier
    private String accountNumber; //Account number (String for if leading zeroes)
    private AccountType accountType; //CHECKING or SAVINGS
    private BigDecimal balance; //Amount of money in the account
    private LocalDateTime createdAt; //When account was created
    private UserResponse user; //Owner information
}
