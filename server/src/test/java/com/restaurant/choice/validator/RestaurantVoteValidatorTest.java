package com.restaurant.choice.validator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.restaurant.choice.domain.vo.Vote;
import com.restaurant.choice.repository.RestaurantRepository;
import com.restaurant.choice.repository.WinnerRepository;
import com.restaurant.choice.validator.RestauranteVoteValidator;

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
