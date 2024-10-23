package com.mursalin.spring_security_series_2.service;

import com.mursalin.spring_security_series_2.model.UserPrinciples;
import com.mursalin.spring_security_series_2.model.Users;
import com.mursalin.spring_security_series_2.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

    private UserRepo repo;

    public MyUserDetailsService(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username);

        if(user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrinciples(user);
    }
}
