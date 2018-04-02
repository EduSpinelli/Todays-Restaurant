package com.restaurant.choice.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restaurant.choice.domain.model.User;
import com.restaurant.choice.domain.vo.Vote;
import com.restaurant.choice.repository.UserRepository;

@Component
public class UserVoteValidator {

  private final UserRepository userRepository;

  public static final String USER_NOT_FOUND = "User not found!";

  @Autowired
  public UserVoteValidator(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public String validateUserVote(Vote vote) {
    User user = userRepository.findByUsernameAndPassword(vote.getUsername(), vote.getPassword());

    if (user == null) {
      return USER_NOT_FOUND;
    } else if (user.getVote()) {
      return "O usuario não pode votar mais de uma vez.";
    }

    return "";
  }


}
