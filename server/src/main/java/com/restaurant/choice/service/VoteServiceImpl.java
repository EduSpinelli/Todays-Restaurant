package com.restaurant.choice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.choice.domain.model.Restaurant;
import com.restaurant.choice.domain.model.User;
import com.restaurant.choice.domain.vo.Vote;
import com.restaurant.choice.repository.RestaurantRepository;
import com.restaurant.choice.repository.UserRepository;

@Service
public class VoteServiceImpl implements VoteService {

  private final UserRepository userRepository;
  private final RestaurantRepository restaurantRepository;

  @Autowired
  public VoteServiceImpl(UserRepository userRepository, RestaurantRepository restaurantRepository) {
    this.userRepository = userRepository;
    this.restaurantRepository = restaurantRepository;
  }

  @Override
  public void vote(Vote vote) {

    markVoteInUser(vote.getUsername(), vote.getPassword());

    addNewVotoToRestaurant(vote.getRestaurantName());

  }

  private void markVoteInUser(String username, String password) {

    User user = userRepository.findByUsernameAndPassword(username, password);

    user.vote();

    userRepository.save(user);

  }

  private synchronized void addNewVotoToRestaurant(String restaurantName) {

    Restaurant restaurant = restaurantRepository.findByName(restaurantName);
    restaurant.vote();
    restaurantRepository.save(restaurant);
  }

}
