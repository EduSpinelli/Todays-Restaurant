package com.restaurant.choice.validator;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.restaurant.choice.domain.vo.Vote;
import com.restaurant.choice.validator.Helper.Result;

@Component
public class VoteValidator {

  private final UserVoteValidator userVoteValidator;
  private final RestauranteVoteValidator restauranteVoteValidator;

  @Autowired
  public VoteValidator(UserVoteValidator userVoteValidator,
      RestauranteVoteValidator restauranteVoteValidator) {
    this.userVoteValidator = userVoteValidator;
    this.restauranteVoteValidator = restauranteVoteValidator;
  }

  public Result validate(Vote vote, Errors errors) {

    Result result = new Result();

    if (errors.hasErrors()) {

      result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage())
          .collect(Collectors.joining(",")));

      return result;

    }

    String message = userVoteValidator.validateUserVote(vote);
    if (!message.isEmpty())
      result.setMsg(message);

    message = restauranteVoteValidator.validateRestaurantInWeek(vote);
    if (!message.isEmpty())
      result.setMsg(message);

    return result;


  }

}
