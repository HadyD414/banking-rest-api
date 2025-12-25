package com.hadyd.bankingapi.service;

import com.hadyd.bankingapi.dto.AccountResponse;
import com.hadyd.bankingapi.dto.CreateAccountRequest;
import com.hadyd.bankingapi.dto.UserResponse;
import com.hadyd.bankingapi.model.Account;
import com.hadyd.bankingapi.model.User;
import com.hadyd.bankingapi.repository.AccountRepository;
import com.hadyd.bankingapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List; //Allows the return of multiple accounts
import java.util.Random; //Helps generate random account numbers
import java.util.stream.Collectors; //To convert lists

@Service
public class AccountService {
    //Access accounts table
    private final AccountRepository accountRepository;
    //Access users table
    private final UserRepository userRepository;

    //Constructor
    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    //Method to create new account
    public AccountResponse createAccount(Long userId, CreateAccountRequest request) {
        User user = userRepository.findById(userId) //Find user by ID, error if not found
                .orElseThrow(() -> new RuntimeException("User not found"));
        //Create new empty account object
        Account account = new Account();
        //Generate and set a unique account number
        account.setAccountNumber(generateAccountNumber());
        //Set account type based on request (CHECKING or SAVINGS)
        account.setAccountType(request.getAccountType());
        //Set balance to initial deposit, if not provided set to $0.00
        account.setBalance(request.getInitialDeposit() != null ?
                           request.getInitialDeposit() : BigDecimal.ZERO);
        //Link account to user
        account.setUser(user);
        //Save account to database and get back saved version
        Account savedAccount = accountRepository.save(account);
        //Convert account entity to DTO and return it
        return convertToResponse(savedAccount);
    }

    //Private method to help generate random 10 digit account number
    private String generateAccountNumber() {
        //Create new random number generator
        Random random = new Random();
        //Generate random number between 1000000000 and 9999999999
        long number = 1000000000L+ random.nextLong(9000000000L);
        //Convert number to a string then return
        return String.valueOf(number);
    }

    //Private method to convert Account entity to AccountResponse DTO
    private AccountResponse convertToResponse(Account account) {
        //Create UserResponse DTO for account owner (no password)
        UserResponse userResponse = new UserResponse(
                account.getUser().getId(), //Owner's ID
                account.getUser().getEmail(), //Owner's email
                account.getUser().getFirstName(), //Owner's first name
                account.getUser().getLastName() //Owner's last name
        );
        //Create and return AccountResponse DTO with account info
        return new AccountResponse(
                account.getId(), //Account ID
                account.getAccountNumber(), //Account number
                account.getAccountType(), //CHECKING or SAVINGS
                account.getBalance(), //Current balance
                account.getCreatedAt(), //When account was created
                userResponse //Owner info
        );
    }

    //Method to get all accounts for user specified
    public List<AccountResponse> getUserAccounts(Long userId) {
        //Get all accounts from database where user_id matches
        List<Account> accounts = accountRepository.findByUserId(userId);

        //Convert each Account entity to AccountResponse DTO and return as a list
        return accounts.stream() //Stream through the list
                .map(this::convertToResponse) //Convert each Account to AccountResponse
                .collect(Collectors.toList()); //Collect results into a List
    }

    //Method to find specific account by its ID
    public AccountResponse getAccountById(Long accountId) {
        //Find account by ID, error if not found
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        //Convert to DTO and return it
        return convertToResponse(account);
    }
}
