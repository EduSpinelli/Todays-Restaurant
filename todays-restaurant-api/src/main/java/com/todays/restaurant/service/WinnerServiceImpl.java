package com.todays.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todays.restaurant.domain.model.Restaurant;
import com.todays.restaurant.domain.model.Winner;
import com.todays.restaurant.repository.WinnerRepository;

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
