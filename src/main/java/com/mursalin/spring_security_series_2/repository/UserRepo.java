package com.mursalin.spring_security_series_2.repository;

import com.mursalin.spring_security_series_2.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
     Users findByUsername(String username);
}
