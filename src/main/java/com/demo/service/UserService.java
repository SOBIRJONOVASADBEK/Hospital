package com.demo.service;

import com.demo.domain.Doktor;
import com.demo.domain.User;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repository.DoktorRepository;
import com.demo.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialBlob;
import java.math.BigDecimal;
import java.sql.SQLException;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DoktorRepository doktorRepository;

    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder,
                       DoktorRepository doktorRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.doktorRepository = doktorRepository;
    }


    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public  Boolean checkedUser(String userName) {
        return userRepository.existsByUserName(userName);
    }



}

 