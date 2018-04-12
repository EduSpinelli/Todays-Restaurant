package com.restaurant.choice.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.restaurant.choice.security.model.UserSecurity;

public interface UserSecurityRepository extends JpaRepository<UserSecurity, Long> {
  UserSecurity findByUsername(String username);
}
