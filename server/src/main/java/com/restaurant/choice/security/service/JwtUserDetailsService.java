package com.restaurant.choice.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.restaurant.choice.security.jwt.JwtUserFactory;
import com.restaurant.choice.security.model.UserSecurity;
import com.restaurant.choice.security.repository.UserSecurityRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

  @Autowired
  private UserSecurityRepository userSecurityRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
    UserSecurity user = userSecurityRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(
            String.format("No user found with username '%s'.", username)));
   
    return JwtUserFactory.create(user);
  }
}
