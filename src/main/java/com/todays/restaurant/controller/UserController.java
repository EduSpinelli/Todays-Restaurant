package com.todays.restaurant.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.todays.restaurant.domain.model.User;
import com.todays.restaurant.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

  private UserRepository applicationUserRepository;
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public UserController(UserRepository applicationUserRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.applicationUserRepository = applicationUserRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @PostMapping("/sign-up")
  public void signUp(@RequestBody User user) {
    User newUser =
        User.createNew(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()),
            "Abacaxi", "Com Canela", "abacaxi@canela.com");
    applicationUserRepository.save(newUser);
  }
}
