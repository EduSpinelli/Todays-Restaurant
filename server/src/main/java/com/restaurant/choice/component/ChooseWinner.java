package com.restaurant.choice.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.restaurant.choice.domain.model.Restaurant;
import com.restaurant.choice.service.RestaurantService;
import com.restaurant.choice.service.UserService;
import com.restaurant.choice.service.WinnerService;

@Component
@EnableScheduling
public class ChooseWinner {

  private static final String TIME_ZONE = "America/Sao_Paulo";

  private final UserService userService;
  private final RestaurantService restaurantService;
  private final WinnerService winnerService;

  @Autowired
  public ChooseWinner(UserService userService, RestaurantService restaurantService,
      WinnerService winnerService) {

    this.userService = userService;
    this.restaurantService = restaurantService;
    this.winnerService = winnerService;

  }

  @Scheduled(cron = "0 55 11 * * *", zone = TIME_ZONE)
  public void verificaPorHora() {

    Restaurant restaurant = restaurantService.getMostVotedRestaurant();
    winnerService.addNewWinnerRestaurant(restaurant);

    userService.clearVoteUser();
    restaurantService.resetRestaurantVotes();
  }

}
