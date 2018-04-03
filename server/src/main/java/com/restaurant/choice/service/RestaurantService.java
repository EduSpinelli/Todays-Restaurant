package com.restaurant.choice.service;

import java.util.List;

import com.restaurant.choice.domain.model.Restaurant;

public interface RestaurantService {

  Restaurant getMostVotedRestaurant();

  List<Restaurant> resetRestaurantVotes();

}
