package com.AAACE.RUTidy.service.user;

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
        Optional<User> userByEmail = userRepository.findByEmail(userDTO.getEmail());
        if (!userByEmail.isEmpty()){
            User emailUser  = userByEmail.get();

            if(emailUser.getUserID() != userID) {
                return new LoginResponse("Email already in use", null);
            }
        }

        Optional<User> userByUsername = userRepository.findByUsername(userDTO.getUsername());
        if (!userByUsername.isEmpty()){
            User usernameUser  = userByUsername.get();

            if(usernameUser.getUserID() != userID) {
                return new LoginResponse("Username already in use", null);
            }
        }

        System.out.println(userDTO.getPassword());
        User user = userRepository.findByUserID(userID).get();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        if(userDTO.getPassword() != null) user.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        user.setUsername(userDTO.getUsername());
        userRepository.save(user);
        return new LoginResponse("Account Updated!", user);
    }


    public User getUser(int userID) {
        Optional<User> optionalUser = userRepository.findByUserID(userID);
        if(optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();
        return user;
    }
}
