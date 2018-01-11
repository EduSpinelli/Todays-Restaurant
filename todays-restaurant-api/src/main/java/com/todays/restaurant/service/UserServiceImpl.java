package com.todays.restaurant.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todays.restaurant.domain.model.User;
import com.todays.restaurant.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<User> clearVoteUser() {

    List<User> users = userRepository.findByVote(true);

    users.forEach(User::clearVote);

    userRepository.save(users);

    return users;

  }

}
