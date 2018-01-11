package com.todays.restaurant.domain.model;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import org.junit.Test;

import com.todays.restaurant.domain.builder.RestaurantBuilder;
import com.todays.restaurant.domain.model.Restaurant;
import com.todays.restaurant.domain.model.Winner;

public class WinnerTest {

  @Test
  public void createNewWinner() {
    Restaurant restaurant = RestaurantBuilder.create("Test").withId(1L).withNumberVotes(2).build();

    Winner winner = Winner.createNewWinner(restaurant);

    assertEquals(null, winner.getId());
    assertEquals(LocalDate.now(), winner.getVoteDate());
    assertEquals(Long.valueOf(1), winner.getRestaurant().getId());
  }

}
