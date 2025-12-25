package com.hadyd.bankingapi.service;
//Import RegisterRequest DTO (for when user registers)
import com.hadyd.bankingapi.dto.RegisterRequest;
//Import UserResponse DTO (to send user's data back safely)
import com.hadyd.bankingapi.dto.UserResponse;
//Import User entity (users table in database)
import com.hadyd.bankingapi.model.User;
//Import UserRepository (access users table in database)
import com.hadyd.bankingapi.repository.UserRepository;
//Import BCryptPasswordEncoder (hash passwords for security)
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//Import Service (tells Spring this is a service class)
import org.springframework.stereotype.Service;

//Makes class a service class
@Service
public class UserService {
    //Repositoru to access database (can't be changed)
    private final UserRepository userRepository;
    //Hashes passwords (can't be changed)
    private final BCryptPasswordEncoder passwordEncoder;
    //Constructor to provide UserRepository automatically
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }
    //Method to register new user (takes RegisterRequest and returns UserResponse)
    public UserResponse registerUser(RegisterRequest request) {
        //Check if a user with this email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists"); //Error for if email already exists
        }
        //Create new empty User object
        User user = new User();
        //Set email from the request
        user.setEmail(request.getEmail());
        //Hash password and set it
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        //Set first name from request
        user.setFirstName(request.getFirstName());
        //Set last name from request
        user.setLastName(request.getLastName());

        //Save user to database and get back saved version
        User savedUser = userRepository.save(user);

        //Create a UserResponse DTO (no password)
        return new UserResponse(
                savedUser.getId(), //User's ID
                savedUser.getEmail(), //User's email
                savedUser.getFirstName(), //User's first name
                savedUser.getLastName() //User's last name
        );
    }
    //Method to find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email) //Find user by email, or throw error if not found
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
