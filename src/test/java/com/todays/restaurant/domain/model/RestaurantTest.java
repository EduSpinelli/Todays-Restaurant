package com.todays.restaurant.domain.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.todays.restaurant.domain.builder.RestaurantBuilder;
import com.todays.restaurant.domain.model.Restaurant;


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
