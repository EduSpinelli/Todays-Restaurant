package com.restaurant.choice.security.controller;


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.restaurant.choice.security.configuration.JwtSettings;
import com.restaurant.choice.security.jwt.JwtTokenUtil;
import com.restaurant.choice.security.jwt.JwtUser;

@RestController
public class UserRestController {

  @Autowired
  private JwtSettings jwtSettings;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  @Qualifier("jwtUserDetailsService")
  private UserDetailsService userDetailsService;

  @RequestMapping(value = "user", method = RequestMethod.GET)
  public JwtUser getAuthenticatedUser(HttpServletRequest request) {
    String token = request.getHeader(jwtSettings.getHeader()).substring(7);
    String username = jwtTokenUtil.getUsernameFromToken(token);
    return (JwtUser) userDetailsService.loadUserByUsername(username);
  }

}
