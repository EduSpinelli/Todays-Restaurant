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
  private UserSecurityRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserSecurity user = userRepository.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException(
          String.format("No user found with username '%s'.", username));
    } else {
      return JwtUserFactory.create(user);
    }
  }
}
