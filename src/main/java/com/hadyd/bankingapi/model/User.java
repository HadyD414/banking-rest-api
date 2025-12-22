package com.hadyd.bankingapi.model;

import jakarta.persistence.*; //JPA library for database (Entity, ID, etc.)
import lombok.AllArgsConstructor; //Auto generates constructor with all parameters
import lombok.Data; //Auto generates getters/setters
import lombok.NoArgsConstructor; //Auto generates a constructor with no parameters
import java.time.LocalDateTime; //Handles dates and times

@Entity //Class represents database table
@Table(name = "users") //Table in PostgreSQL has to be called "users"
@Data //Automatically creates getter and setter methods
@NoArgsConstructor //Creates constructor with no parameters
@AllArgsConstructor //Creates constructor with all parameters new User(id, email, password, firstName, lastName, createdAt)


public class User {

    @Id //Identifies uniquely each row in the table
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Database generates ID automatically
    private Long id; //Stores ID

    @Column(unique = true, nullable = false) //How this column works, no two users can have the same email, and can't be empty/null
    private String email; //Stores email

    @Column(nullable = false) //Password is required (can't be empty)
    private String password; //Stores password

    @Column(nullable = false) //First name is required
    private String firstName; //Stores first name

    @Column(nullable = false) //Last name is required
    private String lastName; //Stores last name

    @Column(nullable = false, updatable = false) //Creation time required, can't be updated once set
    private LocalDateTime createdAt; //Stores date and time of account creation

    @PrePersist //Run this right before saving to database
    protected void initializeTimestamp() {
        createdAt = LocalDateTime.now(); //Sets createdAt to current date/time
    }
}
