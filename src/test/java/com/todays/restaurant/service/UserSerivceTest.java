package com.todays.restaurant.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.restaurant.choice.domain.model.User;
import com.restaurant.choice.repository.UserRepository;
import com.restaurant.choice.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserSerivceTest {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserServiceImpl userService;


  @Test
  public void clearVoteUser() {

    User dummyUser = User.createExistingUserWithVote(0L, "eduardo", "teste", "Eduardo", "Spnelli",
        "eduardo@edaurdo.com");
    User dummyUser2 = User.createExistingUserWithVote(1L, "eduardo1", "teste1", "Eduardo1",
        "Spnelli1", "eduardo@edaurdo.com1");
    User dummyUser3 = User.createExistingUserWithVote(2L, "eduardo2", "teste2", "Eduardo2",
        "Spnelli2", "eduardo@edaurdo.com2");

    List<User> dummyUsers = Arrays.asList(dummyUser, dummyUser2, dummyUser3);

    when(userRepository.findByVote(true)).thenReturn(dummyUsers);

    List<User> usersReturn = userService.clearVoteUser();

    assertTrue(usersReturn.stream().anyMatch(user -> !user.getVote()));

  }



}
