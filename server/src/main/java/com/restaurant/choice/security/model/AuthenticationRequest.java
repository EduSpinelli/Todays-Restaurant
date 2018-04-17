package com.restaurant.choice.security.model;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable {

  private static final long serialVersionUID = -8445943548965154778L;

  private String username;
  private String password;

  public AuthenticationRequest() {
    super();
  }

  public AuthenticationRequest(String username, String password) {
    this.setUsername(username);
    this.setPassword(password);
  }

  public String getUsername() {
    return this.username;
  }

  private void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  private void setPassword(String password) {
    this.password = password;
  }
}
