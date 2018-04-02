package com.todays.restaurant.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.restaurant.choice.domain.model.User;
import com.restaurant.choice.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private UserRepository userRepository;

  @Test
  public void whenFindByUsernameAndPassword_thenReturnUser() {
    User grayson =
        User.createNew("grayson", "filhocinza", "Grayson", "squirrel", "grayson@squirrel.com");
    entityManager.persist(grayson);
    entityManager.flush();

    User found =
        userRepository.findByUsernameAndPassword(grayson.getUsername(), grayson.getPassword());


    assertEquals(found.getUsername(), grayson.getUsername());
  }


  @Test
  public void whenFindByVote_thenReturnAListOfUsers() {

    User grayson =
        User.createNew("grayson", "filhocinza", "Grayson", "squirrel", "grayson@squirrel.com");

    grayson.vote();

    entityManager.persist(grayson);
    entityManager.flush();

    List<User> usersFound = userRepository.findByVote(true);

    assertTrue(
        usersFound.stream().anyMatch(user -> grayson.getUsername().equals(user.getUsername())));
  }

}
