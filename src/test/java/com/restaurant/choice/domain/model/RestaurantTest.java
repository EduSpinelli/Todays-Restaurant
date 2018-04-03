package com.restaurant.choice.domain.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.restaurant.choice.domain.builder.RestaurantBuilder;
import com.restaurant.choice.domain.model.Restaurant;


public class RestaurantTest {

  @Test
  public void createExistetRestaurant() {

    Restaurant restaurante = RestaurantBuilder.create("Test").withId(1L).withNumberVotes(2).build();

    assertEquals(Long.valueOf(1), restaurante.getId());
    assertEquals(String.valueOf("Test"), restaurante.getName());
    assertEquals(Integer.valueOf(2), restaurante.getNumberVotes());
    assertEquals(null, restaurante.getWinners());
  }

}
