package com.hadyd.bankingapi.repository;

import com.hadyd.bankingapi.model.User; //User entity
import org.springframework.data.jpa.repository.JpaRepository; //Repository interface
import java.util.Optional; //Might be null

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); //Spring reads method name and automatically generates SQL
}
