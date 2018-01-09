package com.todays.restaurant.domain.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Vote {

  private String userName;

  private String password;

  private String restaurantName;

  @JsonCreator
  private Vote(@JsonProperty("userName") String userName, @JsonProperty("password") String password,
      @JsonProperty("restaurantName") String restaurantName) {
    this.userName = userName;
    this.password = password;
    this.restaurantName = restaurantName;
  }


  public static Vote createNewVote(String nome, String password, String restaurant) {
    return new Vote(nome, password, restaurant);
  }



  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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
