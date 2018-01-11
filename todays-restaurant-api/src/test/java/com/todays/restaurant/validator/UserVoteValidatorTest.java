package com.todays.restaurant.validator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.todays.restaurant.domain.vo.Vote;
import com.todays.restaurant.repository.UserRepository;
import com.todays.restaurant.validator.UserVoteValidator;

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
