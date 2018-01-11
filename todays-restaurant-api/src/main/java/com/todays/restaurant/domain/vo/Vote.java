package com.todays.restaurant.domain.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Vote {

  private String username;

  private String password;

  private String restaurantName;

  @JsonCreator
  private Vote(@JsonProperty("username") String username, @JsonProperty("password") String password,
      @JsonProperty("restaurantName") String restaurantName) {
    this.username = username;
    this.password = password;
    this.restaurantName = restaurantName;
  }


  public static Vote createNewVote(String nome, String password, String restaurant) {
    return new Vote(nome, password, restaurant);
  }



  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRestaurantName() {
    return restaurantName;
  }

  public void setRestaurantName(String restaurantName) {
    this.restaurantName = restaurantName;
  }

}
