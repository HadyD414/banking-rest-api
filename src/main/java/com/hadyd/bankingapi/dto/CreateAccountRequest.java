package com.hadyd.bankingapi.dto;

import com.hadyd.bankingapi.model.AccountType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountRequest {

    private AccountType accountType; //enum (CHECKING or SAVINGS)
    private BigDecimal initialDeposit; //Initial deposit amount
}
