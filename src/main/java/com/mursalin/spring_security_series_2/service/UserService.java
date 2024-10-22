package com.mursalin.spring_security_series_2.service;

import com.mursalin.spring_security_series_2.model.Users;
import com.mursalin.spring_security_series_2.repository.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepo repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserService(UserRepo repo) {
        this.repo = repo;
    }
    public void register(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
    }
}
