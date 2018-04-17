package com.restaurant.choice.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import org.junit.Test;

import com.restaurant.choice.domain.builder.RestaurantBuilder;
import com.restaurant.choice.domain.model.Restaurant;
import com.restaurant.choice.domain.model.Winner;

public class WinnerTest {

  @Test
  public void createNewWinner() {
    Restaurant restaurant = RestaurantBuilder.create("Test").withId(1L).withNumberVotes(2).build();

    Winner winner = Winner.createNewWinner(restaurant);

    assertNull(winner.getId());
    assertEquals(LocalDate.now(), winner.getVoteDate());
    assertEquals(Long.valueOf(1), winner.getRestaurant().getId());
  }

}
