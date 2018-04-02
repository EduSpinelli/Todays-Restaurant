package com.todays.restaurant.validator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.restaurant.choice.domain.vo.Vote;
import com.restaurant.choice.repository.UserRepository;
import com.restaurant.choice.validator.UserVoteValidator;

@RunWith(MockitoJUnitRunner.class)
public class UserVoteValidatorTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserVoteValidator validator;

  @Test
  public void validationUserVoteTeste() {

    Vote vote = Vote.createNewVote("Alex", "alex", "Palmer");

    String resultMsg = validator.validateUserVote(vote);

    assertEquals(UserVoteValidator.USER_NOT_FOUND, resultMsg);
  }

}
