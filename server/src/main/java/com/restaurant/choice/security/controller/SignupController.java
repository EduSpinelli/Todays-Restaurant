package com.restaurant.choice.security.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.restaurant.choice.security.model.Authority;
import com.restaurant.choice.security.model.AuthorityName;
import com.restaurant.choice.security.model.UserSecurity;
import com.restaurant.choice.security.repository.UserSecurityRepository;

@RestController
public class SignupController {
  
  private UserSecurityRepository userSecurityRepository;

  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  public SignupController(UserSecurityRepository userSecurityRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userSecurityRepository = userSecurityRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }
  
  @RequestMapping(value = "${route.authentication.signup}", method = RequestMethod.POST)
  public void signUp(@RequestBody UserSecurity user) {
      List<Authority> list = new ArrayList<>();
      list.add(new Authority(2L, AuthorityName.ROLE_ADMIN));
      UserSecurity newUser =
          new UserSecurity(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()),
              user.getFirstname(), user.getLastname(), user.getEmail(), true, list);

      userSecurityRepository.save(newUser);
  }


}
