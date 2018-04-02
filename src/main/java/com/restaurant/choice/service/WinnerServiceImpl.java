package com.restaurant.choice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.choice.domain.model.Restaurant;
import com.restaurant.choice.domain.model.Winner;
import com.restaurant.choice.repository.WinnerRepository;

@Service
public class WinnerServiceImpl implements WinnerService {

  private final WinnerRepository winnerRepository;

  @Autowired
  public WinnerServiceImpl(WinnerRepository winnerRepository) {
    this.winnerRepository = winnerRepository;
  }

  @Override
  public void addNewWinnerRestaurant(Restaurant restaurant) {

    Winner winner = Winner.createNewWinner(restaurant);

    winnerRepository.save(winner);

  }

}
