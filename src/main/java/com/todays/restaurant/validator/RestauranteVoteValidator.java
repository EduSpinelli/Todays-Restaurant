package com.todays.restaurant.validator;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.todays.restaurant.domain.model.Restaurant;
import com.todays.restaurant.domain.model.Winner;
import com.todays.restaurant.domain.vo.Vote;
import com.todays.restaurant.repository.RestaurantRepository;
import com.todays.restaurant.repository.WinnerRepository;

@Component
public class RestauranteVoteValidator {


  public static final String RESTAURAT_NOT_FOUND = "Restaurat not found!";

  private final RestaurantRepository restaurantRepository;
  private final WinnerRepository winnerRepository;


  @Autowired
  public RestauranteVoteValidator(RestaurantRepository restaurantRepository,
      WinnerRepository winnerRepository) {
    this.restaurantRepository = restaurantRepository;
    this.winnerRepository = winnerRepository;
  }

  public String validateRestaurantInWeek(Vote vote) {

    Restaurant restaurant = restaurantRepository.findByName(vote.getRestaurantName());

    List<Winner> winners =
        winnerRepository.findWinnersInLastWeekByDate(LocalDate.now().minusWeeks(1)).stream()
            .filter(x -> x.getRestaurant().getName().equals(vote.getRestaurantName()))
            .collect(Collectors.toList());

    if (restaurant == null) {
      return RESTAURAT_NOT_FOUND;
    } else if (!winners.isEmpty()) {
      return "Este esté restaurante foi visidado da ultima semana.";
    }

    return "";
  }


}
