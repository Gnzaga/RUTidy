package com.AAACE.RUTidy.Users;

import com.AAACE.RUTidy.Controllers.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;


@Service
public class UserImplementation {
    
    @Autowired
    private UserRepository userRepository;
    
    //TODO: Implement password hashing
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addUser(UserDTO userDTO) {

        User user = new User(
            userDTO.getName(),
            userDTO.getEmail(),
            this.passwordEncoder.encode(userDTO.getPassword()),
            userDTO.getUsername()
        );


        userRepository.save(user);
        return user.getName();
    }

    UserDTO userDTO;

  
    public LoginResponse login(LoginDTO loginDTO) {
        Optional<User> optionalUser = userRepository.findByEmail(loginDTO.getEmail());
        
        if (optionalUser.isEmpty()) {
            return new LoginResponse("User not found", null);
        }
        User user = optionalUser.get();
        if (this.passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return new LoginResponse("Login successful", user);
        }
        return new LoginResponse("Incorrect password", null);
    }
}
