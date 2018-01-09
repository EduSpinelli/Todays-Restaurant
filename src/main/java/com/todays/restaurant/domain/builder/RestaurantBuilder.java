package com.todays.restaurant.domain.builder;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import java.util.List;

import com.todays.restaurant.domain.model.Restaurant;
import com.todays.restaurant.domain.model.Winner;

public class RestaurantBuilder {

  private Long id;

  private final String name;

  private Integer numberVotes;

  private List<Winner> winners;

  private RestaurantBuilder(String name) {
    checkNotNull(name);
    this.name = name;
  }

  public static RestaurantBuilder create(String name) {
    return new RestaurantBuilder(name);
  }

  public RestaurantBuilder withId(Long id) {
    checkNotNull(id);
    checkArgument(id > 0);
    this.id = id;
    return this;
  }

  public RestaurantBuilder withNumberVotes(Integer numberVotes) {
    checkNotNull(numberVotes);
    this.numberVotes = numberVotes;
    return this;
  }

  public RestaurantBuilder withWinners(List<Winner> winners) {
    checkNotNull(winners);
    this.winners = winners;
    return this;
  }

  public Restaurant build() {
    return Restaurant.createByBuilder(this);
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Integer getNumberVotes() {
    return numberVotes;
  }

  public List<Winner> getWinners() {
    return winners;
  }

}
