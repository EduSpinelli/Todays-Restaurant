package com.restaurant.choice.domain.builder;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.restaurant.choice.domain.model.User;

public class UserBuilder {

  private Long id;

  private final String username;

  private final String password;

  private String firstName;

  private String lastName;

  private String email;

  private Boolean vote;

  private UserBuilder(String username, String password) {
    checkNotNull(username);
    checkNotNull(password);
    this.username = username;
    this.password = password;
  }

  public static UserBuilder create(String username, String password) {
    return new UserBuilder(username, password);
  }

  public UserBuilder withId(Long id) {
    checkNotNull(id);
    checkArgument(id > 0);
    this.id = id;
    return this;
  }

  public UserBuilder withFirstName(String firstName) {
    checkNotNull(firstName);
    this.firstName = firstName;
    return this;
  }

  public UserBuilder withLastName(String lastName) {
    checkNotNull(lastName);
    this.lastName = lastName;
    return this;
  }

  public UserBuilder withEmail(String email) {
    checkNotNull(email);
    this.email = email;
    return this;
  }

  public UserBuilder withVote(Boolean vote) {
    checkNotNull(vote);
    this.vote = vote;
    return this;
  }

  public User build() {

    return User.createByBuilder(this);

  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public Boolean getVote() {
    return vote;
  }

}
