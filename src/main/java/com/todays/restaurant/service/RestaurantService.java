package com.todays.restaurant.service;

import java.util.List;

import com.todays.restaurant.domain.model.Restaurant;

public interface RestaurantService {

  Restaurant getMostVotedRestaurant();

  List<Restaurant> resetRestaurantVotes();

}
