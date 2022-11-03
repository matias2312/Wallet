package com.alkemy.wallet.controller;

import com.alkemy.wallet.email.ValidatorEmail;
import com.alkemy.wallet.model.entity.User;
import com.alkemy.wallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ValidatorEmail validatorEmail;

    @PostMapping("/auth/register")
    public ResponseEntity<Object> register(@RequestParam User user){

        if (user.getFirstName().isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (user.getLastName().isEmpty() ) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (user.getEmail().isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if (user.getPassword().isEmpty()) {
            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
        }
        if(userRepository.findByEmail(user.getEmail()) != null){
            return new ResponseEntity<>("Email already in use", HttpStatus.FORBIDDEN);
        }
        if (!validatorEmail.test(user.getEmail())) {
            return new ResponseEntity<>("invalid mail", HttpStatus.FORBIDDEN);
        }
        User newUser = new User(user.getFirstName(),user.getLastName(),user.getEmail(),passwordEncoder.encode(user.getPassword()));
        userRepository.save(newUser);

        return new ResponseEntity<>("User generated",HttpStatus.CREATED);
    }

}