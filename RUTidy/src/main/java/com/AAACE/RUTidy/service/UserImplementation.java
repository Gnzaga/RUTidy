package com.AAACE.RUTidy.service;

import com.AAACE.RUTidy.dto.LoginDTO;
import com.AAACE.RUTidy.dto.LoginResponse;
import com.AAACE.RUTidy.dto.UserDTO;
import com.AAACE.RUTidy.model.User;
import com.AAACE.RUTidy.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;


@Service
public class UserImplementation implements UserService{
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//TODO: There is little distinction betweeen the handling of emails and usernames. This should be fixed. or at least commented on
    public LoginResponse addUser(UserDTO userDTO) {

        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            return new LoginResponse("Email already in use", null);
        }
        if(userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            return new LoginResponse("Username already in use", null);
        }

        User user = new User(
            userDTO.getName(),
            userDTO.getEmail(),
            this.passwordEncoder.encode(userDTO.getPassword()),
            userDTO.getUsername()
        );

        System.out.println(user.getPassword());
        userRepository.save(user);
        return new LoginResponse("Account Created!", user);
    }

    UserDTO userDTO;

  
    public LoginResponse login(LoginDTO loginDTO) {
        Optional<User> optionalUser = userRepository.findByEmail(loginDTO.getEmail());
        System.out.println("Login username: " + loginDTO.getEmail());
        System.out.println("Login PW: " + loginDTO.getPassword());
        
        if(optionalUser.isEmpty()) {
            optionalUser = userRepository.findByUsername(loginDTO.getEmail());
        }

        if (optionalUser.isEmpty()) {

            return new LoginResponse("User not found", null);
        }
        User user = optionalUser.get();
        if (this.passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {

            return new LoginResponse("Login successful", user);

        }
        return new LoginResponse("Incorrect password", null);
    }

    public LoginResponse updateUser(UserDTO userDTO, int userID) {
        if(userRepository.findByEmail(userDTO.getEmail()).get().getUserID() != userID) {
            return new LoginResponse("Email already in use", null);
        }
        if(userRepository.findByUsername(userDTO.getUsername()).get().getUserID() != userID) {
            return new LoginResponse("Username already in use", null);
        }

        User user = new User(
            userDTO.getName(),
            userDTO.getEmail(),
            this.passwordEncoder.encode(userDTO.getPassword()),
            userDTO.getUsername()
        );

        userRepository.save(user);
        return new LoginResponse("Account Updated!", user);
    }
}
