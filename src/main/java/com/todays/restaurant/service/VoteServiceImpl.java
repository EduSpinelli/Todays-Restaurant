package com.todays.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todays.restaurant.domain.model.Restaurant;
import com.todays.restaurant.domain.model.User;
import com.todays.restaurant.domain.vo.Vote;
import com.todays.restaurant.repository.RestaurantRepository;
import com.todays.restaurant.repository.UserRepository;

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

    markVoteInUser(vote.getUserName(), vote.getPassword());

    addNewVotoToRestaurant(vote.getRestaurantName());

  }

  private void markVoteInUser(String userName, String password) {

    User user = userRepository.findByUserNameAndPassword(userName, password);

    user.vote();

    userRepository.save(user);

  }

  private synchronized void addNewVotoToRestaurant(String restaurantName) {

    Restaurant restaurant = restaurantRepository.findByName(restaurantName);
    restaurant.vote();
    restaurantRepository.save(restaurant);
  }

}
