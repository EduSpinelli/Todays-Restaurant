package com.restaurant.choice.security.jwt;


import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.restaurant.choice.security.model.Authority;
import com.restaurant.choice.security.model.UserSecurity;

public final class JwtUserFactory {

  private JwtUserFactory() {}

  public static JwtUser create(UserSecurity user) {
    return new JwtUser(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname(),
        user.getEmail(), user.getPassword(), mapToGrantedAuthorities(user.getAuthorities()),
        user.getEnabled(), user.getLastPasswordResetDate());
  }

  private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
    return authorities.stream()
        .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
        .collect(Collectors.toList());
  }
}
