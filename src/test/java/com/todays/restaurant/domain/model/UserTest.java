package com.todays.restaurant.domain.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.todays.restaurant.domain.model.User;

public class UserTest {

  @Test
  public void createExistetUser() {
    User user = User.createExistingUserWithVote(99999L, "Eduardo", "Eduardo", "Eduardo", "Spinelli",
        "eduardospinelli.dev@gmail.com");

    assertEquals(Long.valueOf(99999), user.getId());
    assertEquals(String.valueOf("Eduardo"), user.getUserName());
    assertEquals(String.valueOf("Eduardo"), user.getPassword());
    assertEquals(String.valueOf("Eduardo"), user.getFirstName());
    assertEquals(String.valueOf("Spinelli"), user.getLastName());
    assertEquals(String.valueOf("eduardospinelli.dev@gmail.com"), user.getEmail());
    assertEquals(Boolean.valueOf(true), user.getVote());

  }

}
