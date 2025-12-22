package com.hadyd.bankingapi.model;

import jakarta.persistence.*; //Library for JPA
import lombok.AllArgsConstructor; //Auto generates constructor with all parameters
import lombok.Data; //Auto generates getter and setter methods
import lombok.NoArgsConstructor; //Auto generates constructor with no parameters
import java.math.BigDecimal; //Data type for exact decimal numbers (not double)
import java.time.LocalDateTime; //Data type for date and time

@Entity //Class represents a database table
@Table(name = "accounts") //Name for database table "accounts"
@Data //Auto generates getter and setter methods
@NoArgsConstructor //Auto generates public Account() { }
@AllArgsConstructor //Auto generates constructor with all parameters


public class Account {

    @Id //Unique identifier
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto increment value
    private Long id; //Stores ID

    @Column(unique = true, nullable = false) //Two accounts can't have the same account number, field is required
    private String accountNumber; //Stores account Number (String for if account number has leading zeros)
    //Account type (Checking or Savings)
    @Enumerated(EnumType.STRING)  //Tells JPA how to store the enum with the actual text in the database
    @Column(nullable = false) //Every account must have a type
    private AccountType accountType; //Enum with values (CHECKING, SAVINGS)

    @Column(nullable = false) //Balance is required
    private BigDecimal balance; //Prevents rounding errors

    @ManyToOne //Many accounts can belong to one user
    @JoinColumn(name = "user_id", nullable = false) //Column in "accounts" will be named "user_id", every account must belong to a user
    private User user; //Stores user in the actual User object

    @Column(nullable = false, updatable = false) //Must be tracked, and can't be updated
    private LocalDateTime createdAt; //Stores date and time

    @PrePersist //Automatic TimeStamp Initialization
    protected void initializeTimestamp() { //Only this class and subclasses can call this method
        createdAt = LocalDateTime.now(); //Set createdAt to current date/time
        if (balance == null) { //If balance wasn't set, default to zero
            balance = BigDecimal.ZERO; //New accounts start with $0.00
        }
    }
}
