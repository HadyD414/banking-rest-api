package com.hadyd.bankingapi.repository;

import com.hadyd.bankingapi.model.Account; //Account entity
import org.springframework.data.jpa.repository.JpaRepository; //Spring's repository interface
import java.util.List; //Returning multiple accounts
import java.util.Optional; //Returning single accounts that might not exist

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber); //Find account by its account number

    List<Account> findByUserId(Long userId); //Get all accounts belonging to a specific user
}
