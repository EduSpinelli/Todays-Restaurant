package com.todays.restaurant.validator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.todays.restaurant.domain.vo.Vote;
import com.todays.restaurant.repository.RestaurantRepository;
import com.todays.restaurant.repository.WinnerRepository;
import com.todays.restaurant.validator.RestauranteVoteValidator;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantVoteValidatorTest {


  @Mock
  private RestaurantRepository restaurantRepository;

  @Mock
  private WinnerRepository winnerRepository;

  @InjectMocks
  private RestauranteVoteValidator validator;

  @Test
  public void validationUserVoteTeste() {

    Vote vote = Vote.createNewVote("Alex", "alex", "Palmer");

    String resultMsg = validator.validateRestaurantInWeek(vote);

    assertEquals(RestauranteVoteValidator.RESTAURAT_NOT_FOUND, resultMsg);
  }

}
